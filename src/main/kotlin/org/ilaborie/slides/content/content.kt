package org.ilaborie.slides.content


open class Content {
    operator fun plus(other: Content): Content = CompositeContent(this, other)
}

// Basic
object EmptyContent : Content()

data class RawContent(val content: String) : Content()

data class CompositeContent(val contents: List<Content>) : Content() {
    constructor(vararg contents: Content) : this(contents.toList())
}

// Structured
data class HtmlContent(val html: String) : Content()

data class SvgContent(val svg: String) : Content()
data class MarkdownContent(val markdown: String) : Content()

// External
data class ExternalHtmlContent(val externalHtml: External) : Content() {
    val htmlContent by lazy { HtmlContent(externalHtml.loadTextContent()) }
}

data class ExternalMarkdownContent(val externalMarkdown: External) : Content() {
    val markdownContent by lazy { MarkdownContent(externalMarkdown.loadTextContent()) }
}

data class ExternalCodeContent(val language: Language, val externalCode: External) : Content() {
    val code by lazy {
        Code(this.externalCode.loadTextContent(), language, this.externalCode.fileName)
    }
}

data class ExternalSvgContent(val externalSvg: External) : Content() {
    val svgContent by lazy { SvgContent(externalSvg.loadTextContent()) }
}

data class ExternalImageContent(val alt: String, val externalImage: External, val title: String? = null) : Content()


// Structural
data class Code(val code: String, val language: Language = Language.None, val fileName: String? = null) : Content()

data class Title(val title: Content, val level: Int) : Content()
data class Link(val content: Content, val link: String) : Content() {
    constructor(text: String, link: String) : this(text.raw(), link)
}

data class Definitions(val map: Map<Content, Content>) : Content() {
    constructor(vararg pairs: Pair<String, Content>) : this(pairs.map { (key, value) -> key.raw() to value }.toMap())
}

data class OrderedList(val contents: List<Content>) : Content()

data class UnorderedList(val contents: List<Content>) : Content() {
    constructor(vararg contents: Content) : this(contents.toList())
}

data class Figure(val title: Content, val externalImage: External, val copyright: Content? = null) : Content()


// Styled
data class Paragraph(val content: Content) : Content()

data class Quote(val content: Content, val author: String? = null, val cite: String? = null) : Content()
data class Strong(val content: Content) : Content()
data class Emphasis(val content: Content) : Content()
data class Block(val content: Content) : Content()


// Lang
enum class Language {
    None, CSS, HTML, Java, Kotlin, TypeScript, JavaScript;

    override fun toString() = this.name.toLowerCase()

//    companion object {
//        fun findForExtension(ext: String): Language? = when {
//            ext.endsWith("css")  -> CSS
//            ext.endsWith("html") -> HTML
//            ext.endsWith("java") -> Java
//            ext.endsWith("kt")   -> Kotlin
//            ext.endsWith("ts")   -> TypeScript
//            ext.endsWith("js")   -> JavaScript
//            else                 -> null
//        }
//    }
}

