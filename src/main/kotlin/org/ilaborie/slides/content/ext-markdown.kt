package org.ilaborie.slides.content

import org.ilaborie.slides.Slide
import org.ilaborie.slides.Slides
import org.ilaborie.slides.times
import org.ilaborie.slides.underline

fun Slides.renderAsMarkdown(): String =
        toList().joinToString(separator = "\n\n") { it.renderAsMarkdown() }

fun Slide.renderAsMarkdown(): String = this.content().renderAsMarkdown()

fun Content.renderAsMarkdown(): String = when (this) {
    EmptyContent               -> ""
    is RawContent              -> content
    is HtmlContent             -> html
    is SvgContent              -> svg
    is MarkdownContent         -> markdown
    is ExternalHtmlContent     -> htmlContent.renderAsMarkdown()
    is ExternalMarkdownContent -> markdownContent.renderAsMarkdown()
    is ExternalSvgContent      -> svgContent.renderAsMarkdown()
    is ExternalImageContent    -> "![$alt](${externalImage.link()}${if (title != null) " \"$title\"" else ""})"
    is ExternalCodeContent     -> code.renderAsMarkdown()
    is CompositeContent        -> contents.joinToString(separator = "\n") { it.renderAsMarkdown() }
    is Code                    -> this.renderAsMarkdown()
    is Title                   -> this.renderAsMarkdown()
    is Link                    -> "[$text]($link)"
    is StyleEditable           -> "```CSS\n$initialCss\n```" // Edit not supported
    is EditableZone            -> content.renderAsMarkdown() // treated normally
    is Definitions             -> map.toList().joinToString(separator = "\n") { (key, content) ->
        "$key: ${content.renderAsMarkdown()}"
    }
}

fun Code.renderAsMarkdown() = when (language) {
    Language.None -> "`$code`"
    else          -> "```$language\n$code\n```"
}

fun Title.renderAsMarkdown() = when (level) {
    1    -> title.renderAsMarkdown().underline('=')
    2    -> title.renderAsMarkdown().underline('-')
    else -> "${'#' * level} ${title.renderAsMarkdown()} ${'#' * level}\n"
}