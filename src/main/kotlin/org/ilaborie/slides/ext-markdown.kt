package org.ilaborie.slides

fun Slides.renderAsMarkdown(): String =
        toList().joinToString(separator = "\n\n") { it.renderAsMarkdown() }


fun Slide.renderAsMarkdown(): String = when (this) {
    is BasicSlide     -> {
        val title = title?.h3() ?: EmptyContent
        (title + content).renderAsMarkdown()
    }
    is MainTitleSlide ->
        content().renderAsMarkdown()
    is PartTitleSlide ->
        content().renderAsMarkdown()
}


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
    is CompositeContent        ->
        contents.joinToString(separator = "\n") { it.renderAsMarkdown() }
    is Code                    ->
        when (language) {
            Language.None -> "`$code`"
            else          ->
                """|```$language
                   |$code
                   |```""".trimMargin()
        }

    is Title                   ->
        when (level) {
            1    -> title.renderAsMarkdown().underline('=')
            2    -> title.renderAsMarkdown().underline('-')
            else -> "${'#' * level} ${title.renderAsMarkdown()} ${'#' * level}\n"
        }
    is Link                    -> "[$text]($link)"
    is StyleEditable           ->
        // Edit not supported
        """|```CSS
           |$initialCss
           |```""".trimMargin()
    is EditableZone            ->
        // treated normally
        content.renderAsMarkdown()
    is Definitions             -> map
            .toList()
            .joinToString(separator = "\n") { (key, content) -> "$key: ${content.renderAsMarkdown()}" }
}


