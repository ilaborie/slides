package org.ilaborie.slides.content


sealed class Content {
    operator fun plus(other: Content): Content = CompositeContent(this, other)
}

object EmptyContent : Content()

data class RawContent(val content: String) : Content()

data class HtmlContent(val html: String) : Content()
data class SvgContent(val svg: String) : Content()
data class MarkdownContent(val markdown: String) : Content()

data class ExternalHtmlContent(val externalHtml: External) : Content() {
    val htmlContent by lazy { HtmlContent(externalHtml.loadTextContent()) }
}

data class ExternalMarkdownContent(val externalMarkdown: External) : Content() {
    val markdownContent by lazy { MarkdownContent(externalMarkdown.loadTextContent()) }
}

data class ExternalCodeContent(val language: Language, val externalCode: External) : Content() {
    val code by lazy { Code(language, this.externalCode.loadTextContent(), this.externalCode.fileName) }
}

data class ExternalSvgContent(val externalSvg: External) : Content() {
    val svgContent by lazy { SvgContent(externalSvg.loadTextContent()) }
}

data class ExternalImageContent(val alt: String, val externalImage: External, val title: String?) : Content()

data class CompositeContent(val contents: List<Content>) : Content() {
    constructor(vararg contents: Content) : this(contents.toList())
}

data class Code(val language: Language = Language.None, val code: String, val fileName: String? = null) : Content()
data class Title(val title: Content, val level: Int) : Content()
data class Link(val text: String, val link: String) : Content()
data class StyleEditable(val initialCss: String) : Content()
data class EditableZone(val content: Content) : Content()

data class Definitions(val map: Map<String, Content>) : Content() {
    constructor(vararg pairs: Pair<String, Content>) : this(pairs.toMap())
}


// Lang
enum class Language {
    None, CSS, Java, Kotlin, TypeScript, JavaScript;

    override fun toString() = this.name.toLowerCase()

    companion object {
        fun findForExtension(ext: String): Language? = when {
            ext.endsWith("css")  -> CSS
            ext.endsWith("java") -> Java
            ext.endsWith("kt")   -> Kotlin
            ext.endsWith("ts")   -> TypeScript
            ext.endsWith("js")   -> JavaScript
            else                 -> null
        }

    }
}

// Extension
fun String.raw(): Content = RawContent(this)

fun String.html(): Content = HtmlContent(this)
fun String.hX(x: Int): Content = Title(raw(), x)
fun String.h1(): Content = hX(1)
fun String.h2(): Content = hX(2)
fun String.h3(): Content = hX(3)

fun Content.hX(x: Int): Content = Title(this, x)
fun Content.h1(): Content = hX(1)
fun Content.h2(): Content = hX(2)
fun Content.h3(): Content = hX(3)


