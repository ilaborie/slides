package org.ilaborie.slides

import org.ilaborie.slides.ContentType.INTERNAL
import org.ilaborie.slides.ContentType.MARKDOWN
import org.ilaborie.slides.content.Content
import org.ilaborie.slides.content.EmptyContent
import org.ilaborie.slides.content.HtmlContent
import org.ilaborie.slides.content.Title
import org.ilaborie.slides.dsl.ContentContainer

enum class ContentType {
    INTERNAL, HTML, MARKDOWN;

    fun toFileExtension() = when (this) {
        INTERNAL -> ""
        HTML     -> ".html"
        MARKDOWN -> ".md"
    }
}

sealed class Slide : Slides {
    open fun id(): String = title().toString().normalize()
    abstract fun title(): Content
    open fun styleClass(): Set<String> = emptySet()
    abstract fun content(defaultContent: () -> Content): Content
    abstract fun contentType(): ContentType

    override fun toList() = listOf(this)
    // not called
    override fun removeSlide(slideId: String) = this

    override fun replaceSlide(slideKeys: String,
                              newTitle: String?,
                              replacement: ContentContainer.() -> Unit): Slides = this
}

data class BasicSlide(
    override val id: String,
    val title: Content = EmptyContent,
    val content: Content? = null,
    val contentType: ContentType = if (content != null) INTERNAL else MARKDOWN,
    val styleClass: Set<String> = emptySet()) : Slide() {

    constructor(
        title: String,
        id: String = title.normalize(),
        content: Content? = null,
        contentType: ContentType = if (content != null) INTERNAL else MARKDOWN,
        styleClass: Set<String> = emptySet()
    ) : this(title = HtmlContent(title), id = id, content = content, contentType = contentType, styleClass = styleClass)

    override fun id() = id
    override fun title() = Title(level = 3, title = title)
    override fun styleClass() = styleClass
    override fun contentType() = contentType
    override fun content(defaultContent: () -> Content) = content ?: defaultContent()
}

data class MainTitleSlide(val title: Content, override val id: String) : Slide() {
    override fun id() = id
    override fun title() = Title(level = 1, title = title)
    override fun styleClass() = setOf("cover")
    override fun contentType() = INTERNAL
    override fun content(defaultContent: () -> Content) = EmptyContent
}

data class PartTitleSlide(val title: String) : Slide() {
    override val id = "part_${title.normalize()}"
    override fun id() = id
    override fun title() = Title(level = 2, title = HtmlContent(title))
    override fun styleClass() = setOf("part")
    override fun contentType() = INTERNAL
    override fun content(defaultContent: () -> Content) = EmptyContent
}

data class RoadMapSlide(val title: String, val styleClass: Set<String> = emptySet()) : Slide() {
    override val id = "roadmap"
    override fun id() = id
    override fun title() = Title(level = 3, title = HtmlContent(title))
    override fun styleClass() = styleClass + "roadmap"
    override fun contentType() = INTERNAL
    override fun content(defaultContent: () -> Content) = defaultContent()
}
