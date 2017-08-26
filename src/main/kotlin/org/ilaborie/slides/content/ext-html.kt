package org.ilaborie.slides.content

import org.ilaborie.slides.Presentation
import org.ilaborie.slides.Slide

fun Presentation.renderAsHtml(key: String): String {
    val slides = toList()
    fun previous(index: Int): String? = if (index != 0) slides[index - 1].id() else null
    fun next(index: Int): String? = if (index < (slides.size - 2)) slides[index + 1].id() else null

    //const nav = slides.map((slide, index) => `<a href="#${slide.id}" title="${slide.id}">${index}</a>`);
    val nav = slides.mapIndexed { index, slide ->
        """<a href="#${slide.id()}" title="${slide.title()?.renderAsString() ?: slide.id()}">$index</a>"""
    }

    val body = slides
            .mapIndexed { index, slide -> slide.renderAsHtml(previousId = previous(index), nextId = next(index)) }

    return """
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>$title</title>
    <link rel="stylesheet" href="$key.css">
</head>
<body class="$key">
    <div class="slides-nav">
        <nav>
            ${nav.joinToString(separator = "\n") { it.padStart(12) }}
        </nav>
    </div>
    <main>
    ${body.joinToString(separator = "\n") { it.padStart(4) }}
    </main>
</body>
</html>
"""
}

fun Slide.renderAsHtml(previousId: String?, nextId: String?): String {
    val prevFun = if (previousId != null) """<a href="#$previousId" class="previous"></a>""" else ""
    val nextFun = if (nextId != null) """<a href="#$nextId" class="next"></a>""" else ""
    return """
|<!-- Slide -->
|<section id="${id()}" class="${styleClass().joinToString(" ")}">
|  ${content().renderAsHtml()}
|  $prevFun $nextFun
|</section>
""".trimMargin()
}


fun Content.renderAsHtml(): String = when (this) {
    EmptyContent               -> ""
    is RawContent              -> content
    is HtmlContent             -> html
    is SvgContent              -> svg
    is MarkdownContent         -> """<div class="todo">$markdown</div>"""
    is ExternalHtmlContent     -> htmlContent.renderAsHtml()
    is ExternalMarkdownContent -> markdownContent.renderAsHtml()
    is ExternalSvgContent      -> svgContent.renderAsHtml()
    is ExternalImageContent    -> """<img alt="$alt" src="${externalImage.link()}"${if (title != null) " title=\"$title\"" else ""}>"""
    is ExternalCodeContent     -> code.renderAsHtml()
    is CompositeContent        -> contents.joinToString(separator = "\n") { it.renderAsHtml() }
    is Code                    -> this.renderAsHtml()
    is Title                   -> "<h$level>${title.renderAsHtml()}</h$level>"
    is Link                    -> """<a href="$link">$text</a>"""
    is StyleEditable           -> this.renderAsHtml()
    is EditableZone            -> content.renderAsHtml() // treated normally
    is Definitions             -> this.renderAsHtml()
}

fun StyleEditable.renderAsHtml() = "```CSS\n$initialCss\n```"


fun Code.renderAsHtml() = when (language) {
    Language.None -> "<pre>$code</pre>"
    else          -> """<pre class="$language">$code</pre>""" // FIXME use a marked
}


fun Definitions.renderAsHtml() = map
        .toList()
        .joinToString(separator = "\n", prefix = "<dl>", postfix = "</dl>") { (key, content) ->
            "<dt>$key</dt>\n<dd>${content.renderAsHtml()}</dd>"
        }
