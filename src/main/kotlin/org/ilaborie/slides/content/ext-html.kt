package org.ilaborie.slides.content

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.options.MutableDataSet
import org.ilaborie.slides.*


fun Slide.classes() = styleClass().joinToString(separator = " ")
fun Slide.titleAsString() = title()?.renderAsString() ?: id()

fun Presentation.renderAsHtml(key: String): String {
    val slidesList = toList()
    fun previous(index: Int): String? = if (index != 0) slidesList[index - 1].id() else null
    fun next(index: Int): String? = if (index < (slidesList.size - 2)) slidesList[index + 1].id() else null

    val nav = slidesList.mapIndexed { index, slide ->
        """<a href="#${slide.id()}" class="${slide.classes()}" title="${slide.titleAsString()}">$index</a>"""
    }

    val body = this.slides
            .mapIndexed { index, slides -> index to slides }
            .flatMap { (index, slides) -> slides.toList().map { Triple(index, slides, it) } }
            .map { (index, slides, slide) ->
                slide.renderAsHtml(previousId = previous(index), nextId = next(index)) { this.defaultContent(slides, slide) }
            }

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
${nav.joinToString(separator = "\n").indent(' ' * 12)}
        </nav>
    </div>
    <main>
${body.joinToString(separator = "\n").indent(' ' * 4)}
    </main>
</body>
</html>"""
}

fun Slide.renderAsHtml(previousId: String?, nextId: String?, defaultContent: () -> Content): String {
    val prevFun = if (previousId != null) """<a href="#$previousId" class="previous"></a>""" else ""
    val nextFun = if (nextId != null) """<a href="#$nextId" class="next"></a>""" else ""
    return """
<!-- Slide -->
<section id="${id()}" class="${styleClass().joinToString(" ")}">
  ${content(defaultContent).renderAsHtml()}
  <nav>
      $prevFun
      $nextFun
  </nav>
</section>
"""
}


fun Content.renderAsHtml(): String = when (this) {
    EmptyContent               -> ""
    is RawContent              -> content
    is HtmlContent             -> html
    is SvgContent              -> svg
    is MarkdownContent         -> this.renderAsHtml()
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
    is OrderedList             ->
        contents.joinToString(separator = "\n", prefix = "<ol>", postfix = "</ol>") { "<li>${it.renderAsHtml()}</li>" }
    is UnorderedList           ->
        contents.joinToString(separator = "\n", prefix = "<ul>", postfix = "</ul>") { "<li>${it.renderAsHtml()}</li>" }
    is Paragraph               -> "<p>${content.renderAsHtml()}</p>"
    is Quote                   -> this.renderAsHtml()
    is Strong                  -> "<strong>${content.renderAsHtml()}</strong>"
    is Emphasis                -> "<em>${content.renderAsHtml()}</em>"
    is Figure                  -> this.renderAsHtml()
}

fun StyleEditable.renderAsHtml() = "```CSS\n$initialCss\n```"


fun Code.renderAsHtml() = when (language) {
    Language.None -> "<pre>$code</pre>"
    else          -> """<pre class="$language">$code</pre>""" // FIXME preformat
}


fun Definitions.renderAsHtml() = map
        .toList()
        .joinToString(separator = "\n", prefix = "<dl>", postfix = "</dl>") { (key, content) ->
            "<dt>$key</dt>\n<dd>${content.renderAsHtml()}</dd>"
        }


fun Quote.renderAsHtml() = """
<blockquote${if (cite != null) "cite=\"$cite\"" else ""}>
    <p>${content.renderAsHtml()}</p>${if (author != null) "\n    <footer>--$author</footer>" else ""}
</blockquote>
"""

fun Figure.renderAsHtml() = """
<figure>
  <img src="${externalImage.link()}" alt="$title">
  <figcaption>${title.renderAsHtml()}</figcaption>${if (copyright != null) "\n<p class=\"copyright\">$copyright</p>" else ""}
</figure>"""


// Markdown

// uncomment to set optional extensions...
//options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));
//options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");

private val options = MutableDataSet()
private val parser = Parser.builder(options).build()
private val renderer = HtmlRenderer.builder(options).build()

fun MarkdownContent.renderAsHtml(): String {
    val document = parser.parse(markdown)
    return renderer.render(document)
}