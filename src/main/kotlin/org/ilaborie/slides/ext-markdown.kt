package org.ilaborie.slides

fun Slides.renderAsMarkdown(): String =
        toList().joinToString(separator = "\n\n") { it.renderAsMarkdown() }


fun Slide.renderAsMarkdown(): String = when (this) {
    is BasicSlide     -> {
        CompositeContent(
                if (title != null) Title(title, 3) else EmptyContent,
                content)
                .renderAsMarkdown()
    }
    is MainTitleSlide ->
        content().renderAsMarkdown()
    is PartTitleSlide ->
        content().renderAsMarkdown()
}


fun Content.renderAsMarkdown(): String = when (this) {
    EmptyContent            -> ""
    is RawContent           -> content
    is HtmlContent          -> html
    is SvgContent           -> svg
    is MarkdownContent      -> markdown
    is ExternalHtmlContent  -> htmlContent.renderAsMarkdown()
    is ExternalSvgContent   -> svgContent.renderAsMarkdown()
    is ExternalImageContent -> "![$alt](${externalImage.link()}${if (title != null) " \"$title\"" else ""})"
    is ExternalCodeContent  -> code.renderAsMarkdown()
    is CompositeContent     ->
        contents.joinToString(separator = "\n") { it.renderAsMarkdown() }
    is Code                 -> when (language) {
        Language.None -> "`$code`"
        else          ->
            """|```$language
               |$code
               |```""".trimMargin()
    }

    is Title                ->
        when (level) {
            1    -> {
                val title = title.renderAsMarkdown()
                """$title
                  |${"=" * title.length}""".trimMargin()
            }
            2    -> {
                val title = title.renderAsMarkdown()
                """$title
                  |${"-" * title.length}""".trimMargin()
            }
            else -> "${"#" * level} ${title.renderAsMarkdown()}\n"
        }
    is Link                 -> "[$text]($link)"
    is StyleEditable        ->
        // Edit not supported
        """|```CSS
           |$initialCss
           |```""".trimMargin()
    is EditableZone         ->
        // treated normally
        content.renderAsMarkdown()
    is Definitions          -> map
            .toList()
            .joinToString(separator = "\n") { (key, content) -> "$key: ${content.renderAsMarkdown()}" }
}
