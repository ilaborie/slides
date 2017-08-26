package org.ilaborie.slides

import org.ilaborie.slides.content.renderAsHtml
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

