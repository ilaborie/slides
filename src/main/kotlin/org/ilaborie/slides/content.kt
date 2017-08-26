package org.ilaborie.slides


sealed class Content

object EmptyContent : Content()

data class RawContent(val content: String) : Content()

data class HtmlContent(val html: String) : Content()
data class SvgContent(val svg: String) : Content()
data class MarkdownContent(val markdown: String) : Content()

data class ExternalHtmlContent(val externalHtml: External) : Content() {
    val htmlContent: HtmlContent by lazy { HtmlContent(externalHtml.loadTextContent()) }
}

data class ExternalCodeContent(val language: Language, val externalCode: External) : Content() {
    val code: Code by lazy { Code(language, this.externalCode.loadTextContent(), this.externalCode.fileName) }
}

data class ExternalSvgContent(val externalSvg: External) : Content() {
    val svgContent: SvgContent by lazy { SvgContent(externalSvg.loadTextContent()) }
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
    None, CSS, Java, Kotlin, TypeScript;

    override fun toString() = this.name.toLowerCase()
}


