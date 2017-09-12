package org.ilaborie.slides

import org.ilaborie.logger.Level
import org.ilaborie.logger.Logger
import org.ilaborie.slides.ContentType.*
import org.ilaborie.slides.content.*
import java.io.File
import java.nio.charset.Charset

val logger = Logger("PresExt").apply {
    level = Level.DEBUG
}

fun <T> safe(dangerous: () -> T): T =
        try {
            dangerous()
        } catch (e: Throwable) {
            logger.error(e) { "Oops" }
            throw RuntimeException(e)
        }

fun <T> catchWithDefault(default: T, dangerous: () -> T): T =
        try {
            dangerous()
        } catch (e: Throwable) {
            logger.warn(e) { "Use default" }
            default
        }

fun Presentation.writeHtmlTo(folder: File, key: String = "index", charset: Charset = Charsets.UTF_8) {
    val file = folder.resolve("$key.html")
    logger.debug { "Write '${this.title}' to $file" }
    file.writeText(renderAsHtml(key), charset)
}

fun Presentation.writePdfTo(from: File, to: File): Int {
    val file = from.absolutePath
    return ProcessBuilder("node", "src/main/typescript/html-to-pdf.js", "file://$file", to.absolutePath)
            .inheritIO()
            .start()
            .waitFor()
}

fun Presentation.writeMarkdownTo(folder: File, key: String = "index", charset: Charset = Charsets.UTF_8) {
    val file = folder.resolve("$key.md")
    logger.debug { "Write '${this.title}' to $file" }
    file.writeText(renderAsMarkdown(), charset)
}


// Externals
private fun Presentation.getExternals() = slides
        .flatMap { slides -> slides.toList().map { slides to it } }
        .filterNot { (_, slide) -> slide.contentType() == INTERNAL }
        .flatMap { (parent, slide) ->
            val content = slide.content { this.defaultContent(parent, slide) }
            content.toExternal()
        }

fun Presentation.hasMissingExternals(): Boolean {
    val externals = this.getExternals()
    val result = externals.any { !it.exists() }
    logger.trace { "'${this.title}' hasMissingExternals? $result" }
    return result
}

// Default content
fun Presentation.defaultContent(parent: Slides, slide: Slide): Content =
        when (slide) {
            is RoadMapSlide -> OrderedList(
                    slides.filterIsInstance<Group>()
                            .filterNot { it.skipPart }
                            .map { it.title }
                            .map { it.raw() })
            else            -> {
                val contentType = slide.contentType().toFileExtension()
                val resource = (listOf(this.id) +
                        when (parent) {
                            is Group ->
                                listOf("${this(parent).format("00")}_${parent.id}",
                                       "${parent(slide).format("00")}_${slide.id()}$contentType")
                            else     ->
                                listOf("${this(slide).format("00")}_${slide.id()}$contentType")
                        }).joinToString(prefix = "/", separator = "/")

                logger.trace { "Default content for $resource" }

                when (slide.contentType()) {
                    HTML     -> ExternalHtmlContent(ExternalResource(resource))
                    MARKDOWN -> ExternalMarkdownContent(ExternalResource(resource))
                    else     -> TODO()
                }
            }
        }


fun Presentation.generateMissingExternals(folder: File = File("")): List<External> {
    val result = this.getExternals().filterNot { it.exists() }

    result.forEach { external ->
        logger.info { "Create $external" }
        external.create(folder)
    }
    return result
}

