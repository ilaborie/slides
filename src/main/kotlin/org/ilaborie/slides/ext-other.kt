package org.ilaborie.slides

import org.ilaborie.slides.content.External
import org.ilaborie.slides.content.renderAsHtml
import org.ilaborie.slides.content.renderAsMarkdown
import org.ilaborie.slides.content.toExternal
import java.io.File
import java.nio.charset.Charset


fun <T> catchWithDefault(default: T, dangerous: () -> T): T =
        try {
            dangerous()
        } catch (e: Throwable) {
            // e.printStackTrace()
            default
        }

fun Presentation.writeHtmlTo(folder: File, key: String = "index", charset: Charset = Charsets.UTF_8) =
        folder.resolve("$key.html")
                .writeText(renderAsHtml(key), charset)

fun Presentation.writeMarkdownTo(folder: File, key: String = "index", charset: Charset = Charsets.UTF_8) =
        folder.resolve("$key.md")
                .writeText(renderAsMarkdown(), charset)


fun Presentation.hasMissingExternals(): Boolean =
        slides.flatMap { it.toList() }
                .flatMap { it.content().toExternal() }
                .any { !it.exists() }


//
fun Presentation.generateMissingExternals(folder: File = File("")): List<External> {
    val externals = slides
            .flatMap { it.toList() }
            .flatMap { it.content().toExternal() }

    val result = externals.filterNot { it.exists() }

    result.forEach { it.create(folder) }

    return result
}

