package org.ilaborie.slides.content

import org.ilaborie.slides.*

fun Presentation.renderAsMarkdown(): String =
        slides.flatMap { slides -> slides.toList().map { slides to it } }
                .joinToString(separator = "\n\n") { (slides, slide) ->
                    slide.renderAsMarkdown { this.defaultContent(slides, slide) }
                }

fun Slide.renderAsMarkdown(defaultContent: () -> Content): String =
        this.content(defaultContent).renderAsMarkdown()

fun Content.renderAsMarkdown(): String = when (this) {
    EmptyContent               -> ""
    is RawContent              -> content
    is HtmlContent             -> html // TODO see https://github.com/vsch/flexmark-java/wiki/Extensions#html-to-markdown
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
    is Link                    -> "[${content.renderAsMarkdown()}]($link)"
    is Definitions             -> map.toList().joinToString(separator = "\n") { (key, content) ->
        "$key\n: ${content.renderAsMarkdown()}"
    }
    is OrderedList             -> contents
            .mapIndexed { index, content -> "$index. ${content.renderAsMarkdown()}" }
            .joinToString(separator = "\n")
    is UnorderedList           -> contents
            .map { "* ${it.renderAsMarkdown()}" }
            .joinToString(separator = "\n")
    is Paragraph               -> content.renderAsMarkdown()
    is Quote                   -> content.renderAsMarkdown().indent("> ")
    is Strong                  -> "**${content.renderAsMarkdown()}**"
    is Emphasis                -> "*${content.renderAsMarkdown()}*"
    is Figure                  -> "![${title.renderAsMarkdown()}](${externalImage.link()} \"$title\"})" // TODO copyright
    is Block                   -> content.renderAsMarkdown()
    is StyleEditable           -> "```CSS\n$initialCss\n```" // Edit not supported
    is EditableZone            -> content.renderAsMarkdown() // treated normally
    is CssCompatibility        -> {
        val columns = table.columns()
        columns.joinToString(separator = "|", prefix = "feature|") +
                columns.map { "---" }.joinToString(separator = "|", prefix = "feature|") +
                table.rows()
                        .map { row -> row to columns.map { column -> table.get(row, column) } }
                        .joinToString(separator = "\n") { (row, values) ->
                            values.joinToString(separator = "|", prefix = "$row|")
                        }
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