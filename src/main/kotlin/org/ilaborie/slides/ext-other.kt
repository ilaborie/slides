package org.ilaborie.slides

import org.ilaborie.logger.Logger
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.ContentType.MARKDOWN
import org.ilaborie.slides.content.*
import java.io.File
import java.nio.charset.Charset

val logger = Logger("PresExt")

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

fun Presentation.writeMarkdownTo(folder: File, key: String = "index", charset: Charset = Charsets.UTF_8) {
    val file = folder.resolve("$key.md")
    logger.debug { "Write '${this.title}' to $file" }
    file.writeText(renderAsMarkdown(), charset)
}


// Externals
private fun Presentation.getExternals() = slides
        .flatMap { slides -> slides.toList().map { slides to it } }
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
fun Presentation.defaultContent(slides: Slides, slide: Slide): Content {
    val contentType = slide.contentType().toFileExtension()
    val resource = (listOf(this.id) +
            when (slides) {
                is Group ->
                    listOf("${this(slides).format("00")}_${slides.title.normalize()}",
                           "${slides(slide).format("00")}_${slide.id()}$contentType")
                else     ->
                    listOf("${this(slide).format("00")}_${slide.id()}$contentType")
            }).joinToString(prefix = "/", separator = "/")


    logger.trace { "Default content for $resource" }

    return when (slide.contentType()) {
        HTML     -> ExternalHtmlContent(ExternalResource(resource))
        MARKDOWN -> ExternalMarkdownContent(ExternalResource(resource))
        else     -> TODO()
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

