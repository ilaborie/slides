package org.ilaborie.slides.content

import org.ilaborie.slides.*
import org.ilaborie.slides.content.web.*
import java.text.DecimalFormat


fun Slide.classes() = styleClass().joinToString(separator = " ")
fun Slide.titleAsString() = title().renderAsString()


fun Presentation.renderAsHtml(key: String): String {
    val slidesList = toList()
    fun previous(index: Int): String? = if (index != 0) slidesList[index - 1].id() else null
    fun next(index: Int): String? = if (index < (slidesList.size - 1)) slidesList[index + 1].id() else null

    val slideIndex = slidesList.mapIndexed { index, slide -> slide to index }
            .toMap()

    val groupsTitles = slides.filterIsInstance<Group>()
            .map { it.title }
            .joinToString(separator = "\n") { "<strong>$it</strong>" }
    val groupsNavs = slides.filterIsInstance<Group>()
            .map {
                it.toList().joinToString(separator = "\n") {
                    """<a href="#${it.id()}" class="${it.classes()}" title="${it.titleAsString()}">${slideIndex[it]}</a>"""
                }
            }
            .joinToString(separator = "\n") { "<nav>$it</nav>" }

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
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${title.renderAsString()}</title>
    <link rel="stylesheet" href="../slides.css">
    <link rel="stylesheet" href="../$id.css">
    <link rel="stylesheet" href="../$key.css">
    <link rel="stylesheet" href="../print.css" media="print">
    <link rel="icon" type="image/png" href="../assets/$key/favicon.png" />
</head>
<body class="$key $id">
    <div class="slides-nav hide-print" style="grid-template-columns : repeat(${slides.count() + 1}, auto);">
        <a href="#${coverSlide.id()}" class="${coverSlide.classes()}" title="${coverSlide.titleAsString()}">0</a>
        $groupsTitles
        $groupsNavs
    </div>
    <main>
${body.joinToString(separator = "\n")}
    </main>
    ${this.scripts.joinToString(separator = "\n") {
        """<script type="application/javascript" src="$it"></script>"""
    }}
</body>
</html>"""
}

fun Slide.renderAsHtml(previousId: String?, nextId: String?, defaultContent: () -> Content): String {
    val prevFun = if (previousId != null) """<a href="#$previousId" class="previous" aria-label="Précédant"></a>""" else ""
    val nextFun = if (nextId != null) """<a href="#$nextId" class="next" aria-label="Suivant"></a>""" else ""
    return """
<!-- Slide ${titleAsString()} -->
<section id="${id()}" class="${styleClass().joinToString(" ")}">
    $prevFun
    ${title().renderAsHtml()}
    <article>
        ${content(defaultContent).renderAsHtml()}
    </article>
    $nextFun
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
    is ExternalImageContent    -> """<img alt="$alt" src="${externalImage.dataUri}"${if (title != null) " title=\"$title\"" else ""}>"""
    is ExternalCodeContent     -> code.renderAsHtml()
    is CompositeContent        -> contents.joinToString(separator = "\n") { it.renderAsHtml() }
    is Code                    -> this.renderAsHtml()
    is Title                   -> "<h$level>${title.renderAsHtml()}</h$level>"
    is Link                    -> """<a href="$link">${content.renderAsHtml()}</a>"""
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
// For CSS, HTML
    is StyleEditable           -> this.renderAsHtml()
    is EditableZone            -> "<div class=\"editable\">${content.renderAsHtml()}</div>"
    is CssCompatibility        -> this.renderAsHtml()
    is Notice   -> this.renderAsHtml()
    else                       -> TODO()
}

fun StyleEditable.renderAsHtml() = """<style contenteditable="true" class="hide-print">${initialCss.textContent}</style>""" +
        if (finalCss != null)
            """<pre class="hljs lang-css show-print"><code>${getFormattedCode(Language.CSS, finalCss.textContent)}</code></pre>"""
        else ""

fun Code.renderAsHtml() = when (language) {
    Language.None -> "<code>$code</code>"
    else          -> """<pre class="hljs lang-$language"><code>${getFormattedCode(language, code)}</code></pre>"""
}

private val codeCache = mutableMapOf<Pair<Language, String>, String>()
private fun getFormattedCode(language: Language, code: String): String =
        codeCache.getOrPut(language to code) {
            val cmd = listOf("ts-node", "src/main/typescript/code-to-html.ts", language.toString().toLowerCase())
            logger.info { "Run ${cmd.joinToString()} ..." }
            val process = ProcessBuilder(cmd).start()
            val writer = process.outputStream.writer()
            writer.write(code)
            writer.close()
            return process.inputStream.bufferedReader().readText()
        }

fun Definitions.renderAsHtml() = map
        .toList()
        .joinToString(separator = "\n", prefix = "<dl>", postfix = "</dl>") { (key, content) ->
            "<dt>${key.renderAsHtml()}</dt>\n<dd>${content.renderAsHtml()}</dd>"
        }

fun Quote.renderAsHtml() = """
<blockquote${if (cite != null) "cite=\"$cite\"" else ""}>
    <p>${content.renderAsHtml()}</p>${if (author != null) "\n    <footer>--$author</footer>" else ""}
</blockquote>
"""

fun Figure.renderAsHtml() = """
<figure>
  <img src="${externalImage.dataUri}" alt="${title.renderAsString()}">
  <figcaption>${title.renderAsHtml()}</figcaption>
  ${if (copyright != null) "<p class=\"copyright\">${copyright.renderAsHtml()}</p>" else ""}
</figure>"""

private val mdCache = mutableMapOf<String, String>()
fun MarkdownContent.renderAsHtml(): String =
        mdCache.getOrPut(this.markdown) {
            val cmd = listOf("ts-node", "src/main/typescript/md-to-html.ts")
            logger.info { "Run ${cmd.joinToString()} ..." }
            val process = ProcessBuilder(cmd).start()
            val writer = process.outputStream.writer()
            writer.write(this.markdown)
            writer.close()
            process.inputStream.bufferedReader().readText()
        }

fun CssCompatibility.renderAsHtml(): String {
    val browsers: List<Browser> = table.columns()
            .sortedBy { it.usage }
            .reversed()
            .toList()

    fun Stat.toClass(feature: Feature): String = when (compatibility(feature)) {
        NotAvailable -> "not-available"
        Available    -> "available"
        is Partial   -> "partial"
        is Prefix    -> "partial prefix"
        is Flag      -> "partial flag"
        is Buggy     -> "buggy"
    }

    fun Stat.toInfo(feature: Feature): String {
        val compatibility = compatibility(feature)
        return when (compatibility) {
            NotAvailable -> version
            Available    -> version
            is Partial   -> """<div class="version">$version</div>
                |<div class="info">${MarkdownContent(compatibility.info.escapeHtml()).renderAsHtml()}</div>""".trimMargin()
            is Prefix    -> """<div class="version">$version</div>
                |<div class="info">Prefix ${MarkdownContent(compatibility.info.escapeHtml()).renderAsHtml()}</div>""".trimMargin()
            is Flag      -> """<div class="version">$version</div>
                |<div class="info">Flag ${MarkdownContent(compatibility.info.escapeHtml()).renderAsHtml()}</div>""".trimMargin()
            is Buggy     -> """<div class="version">$version</div>
                |<div class="info">${MarkdownContent(compatibility.info.escapeHtml()).renderAsHtml()}</div>""".trimMargin()
        }
    }

    val features = table.rows()
            .map { feature -> feature to (browsers.map { browser -> browser to table.get(feature, browser) }) }
            .map { (feature, values) ->
                feature to values.joinToString(separator = "") { (browser, value) ->
                    """<div class="value ${browser.key} ${feature.key}">${
                    (value?.stats ?: emptyList()).joinToString("\n") { stat ->
                        """<div class="${stat.toClass(feature)}">${stat.toInfo(feature)}</div>"""
                    }}</div>"""
                }
            }

    val formatter = DecimalFormat("0.0")

    return """
<div class="compatibility-caption country-$country">${formatter.format(threshold)}</div>
<div class="compatibility" style="grid-template-columns : repeat(${browsers.count() + 1}, 1fr);">
    <div class="void"></div>
    ${browsers.joinToString(separator = "") { """<div class="browser ${it.key}${if (it.mobile) " mobile" else ""}" aria-label="${it.key}"></div>""" }}
    <div class="void"></div>
    ${browsers.joinToString(separator = "") { """<div class="browser-percent">${formatter.format(it.usage)} %</div>""" }}
    ${features.joinToString(separator = "") { (feature, values) ->
        """<div class="feature ${feature.key}">
        |   <a href="https://caniuse.com/#feat=${feature.key}" aria-label="${feature.title}" title="{${feature.description.escapeHtml()}}">
        |       ${feature.title}
        |   </a>
        |</div>$values""".trimMargin()
    }}
</div>
"""
}

fun Notice.renderAsHtml(): String = """
<div class="notice notice-${kind.toString().toLowerCase()}">
    ${content.renderAsHtml()}
</div>
"""