package org.ilaborie.slides.content

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

    val body = (listOf(coverSlide) + this.slides)
            .flatMap { slides -> slides.toList().map { slides to it } }
            .mapIndexed { index, (parent, slide) -> Triple(index, parent, slide) }
            .map { (index, parent, slide) ->
                slide.renderAsHtml(previousId = previous(index), nextId = next(index)) {
                    this.defaultContent(parent, slide)
                }
            }

    return """
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>$title</title>
    <link rel="stylesheet" href="slides.css">
    <link rel="stylesheet" href="$key.css">
</head>
<body class="$key">
    <div class="slides-nav">
        <nav>
${nav.joinToString(separator = "\n").indent(' ' * 12)}
        </nav>
    </div>
    <main>
${body.joinToString(separator = "\n")}
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
${content(defaultContent).renderAsHtml().indent(' ' * 2)}
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
    is Link                    -> """<a href="$link">${content.renderAsHtml()}</a>"""
    is StyleEditable           -> this.renderAsHtml()
    is EditableZone            -> "<div class=\"editable\">${content.renderAsHtml()}</div>"
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
    is Block                   -> "<div>${content.renderAsHtml()}</div>"
}

fun StyleEditable.renderAsHtml() = "<style scoped contenteditable=\"true\">${initialCss.loadTextContent()}</style>"

// FIXME test
fun Code.renderAsHtml() = when (language) {
    Language.None -> "<pre>$code</pre>"
    else          -> {
        val process = ProcessBuilder("ts-node", "src/main/typescript/code-to-html.ts", language.toString().toLowerCase()).start()
        val writer = process.outputStream.writer()
        writer.write(this.code)
        writer.close()
        val code = process.inputStream.bufferedReader().readText()
        """<pre class="lang-$language"><code>
        |${code.trimIndent()}
        |</code></pre>""".trimMargin() // FIXME preformat
    }
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
  <figcaption>${title.renderAsHtml()}</figcaption>${if (copyright != null) "\n<p class=\"copyright\">${copyright.renderAsHtml()}</p>" else ""}
</figure>"""

// FIXME test
fun MarkdownContent.renderAsHtml(): String {
    val process = ProcessBuilder("ts-node", "src/main/typescript/md-to-html.ts").start()
    val writer = process.outputStream.writer()
    writer.write(this.markdown)
    writer.close()
    return process.inputStream.bufferedReader().readText()
}