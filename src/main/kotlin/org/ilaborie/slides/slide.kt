package org.ilaborie.slides

import org.ilaborie.slides.content.*

sealed class Slide : Slides {
    open fun id(): String = title().toString().normalize()
    abstract fun title(): Content?
    open fun styleClass(): Set<String> = emptySet()
    abstract fun content(): Content

    override fun toList() = listOf(this)
}

data class BasicSlide(
        val id: String,
        val title: Content? = null,
        val content: Content = ExternalHtmlContent(ExternalResource("$id.html")),
        val styleClass: Set<String> = emptySet()) : Slide() {
    constructor(
            title: String,
            id: String = title.normalize(),
            content: Content = ExternalHtmlContent(ExternalResource("$id.html")),
            styleClass: Set<String> = emptySet()
    ) : this(title = title.raw(), id = id, content = content, styleClass = styleClass)

    override fun id() = id
    override fun title() = title
    override fun styleClass() = styleClass
    override fun content(): Content {
        val title = title?.h3() ?: EmptyContent
        return title + content
    }
}

data class MainTitleSlide(val title: String) : Slide() {
    override fun id() = title.normalize()
    override fun title() = title.raw()
    override fun styleClass() = setOf("cover")
    override fun content() = title.h1()
}

data class PartTitleSlide(val title: String) : Slide() {
    override fun id() = "part_${title.normalize()}"
    override fun title() = title.raw()
    override fun styleClass() = setOf("part")
    override fun content() = title.h2()
}
