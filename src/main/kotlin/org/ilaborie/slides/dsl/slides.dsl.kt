package org.ilaborie.slides.dsl

import org.ilaborie.slides.*
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.ContentType.MARKDOWN
import org.ilaborie.slides.content.*

/**
 * Presentation
 */
fun presentation(title: String,
                 key: String = title.normalize(),
                 b: PresentationBuilder.() -> Unit): Presentation =
    PresentationBuilder(title = title, key = key)
        .apply(b)
        .invoke()

typealias IPresentationBuilder = () -> Presentation
class PresentationBuilder(private val key: String, title: String) : IPresentationBuilder {
    private var groups = listOf<IPartBuilder>()
    private var scripts = listOf<String>()
    var title: Content = HtmlContent(title)

    override fun invoke() = Presentation(
            title = this.title,
            id = key,
            slides = groups.map { it(key) },
            scripts = scripts)

    fun addScript(script: String) {
        scripts += script
    }

    fun addGroup(group: IPartBuilder) {
        groups += group
    }

}

fun PresentationBuilder.part(title: String, function: PartBuilder.() -> Unit) {
    addGroup(PartBuilder(title = title).apply(function))
}

/**
 * Part Builder
 */
typealias IPartBuilder = (String) -> Group
class PartBuilder(private val title: String,
                  private val key: String = title.normalize()) : IPartBuilder {

    private var slideBuilders = listOf<ISlideBuilder>()
    var skipHeader = false

    override fun invoke(presentationKey: String) = Group(
            title = title,
            id = key,
            slides = slideBuilders.map { it(presentationKey, key) },
            skipPart = skipHeader)

    fun addSlide(slide: ISlideBuilder) {
        this.slideBuilders += slide
    }
}


fun PartBuilder.roadmap(title: String = "Roadmap") {
    addSlide(object : ISlideBuilder {
        override var styleClass = setOf<String>()
        override fun invoke(presentationKey: String, groupKey: String?): Slide =
            RoadMapSlide(title, styleClass)
    })
}


fun PartBuilder.slide(title: String?,
                      key: String? = title?.normalize(),
                      styleClass: Set<String> = setOf(),
                      b: ContentContainer.() -> Unit) {

    addSlide(SlideBuilder()
                 .apply {
                     this.id = key ?: ""
                     this.title = title?.let { HtmlContent(it) } ?: EmptyContent
                     this.styleClass += styleClass
                     val builder = ContentContainer()
                     b(builder)
                     this.content = builder()
                 })
}


fun PartBuilder.slideFromResource(title: String,
                                  key: String = title.normalize(),
                                  contentType: ContentType = MARKDOWN,
                                  b: ISlideBuilder.() -> Unit = {}) {

    addSlide(object : ISlideBuilder {
        override var styleClass = setOf<String>()
        override fun invoke(presentationKey: String, groupKey: String?): Slide {
            val resource = if (groupKey == null) "/$presentationKey/$key" else "/$presentationKey/$groupKey/$key"
            val content = when (contentType) {
                MARKDOWN -> ExternalMarkdownContent(ExternalResource("$resource.md"))
                HTML     -> ExternalHtmlContent(ExternalResource("$resource.html"))
                else     -> throw IllegalStateException("Unsupported content type: $contentType")
            }
            return BasicSlide(id = key,
                              title = HtmlContent(title),
                              content = content,
                              contentType = contentType,
                              styleClass = styleClass)
        }

    }.apply(b))
}

/**
 * Slide Builder
 */
interface ISlideBuilder {
    var styleClass: Set<String>
    operator fun invoke(presentationKey: String, groupKey: String? = null): Slide

}

class SlideBuilder : ISlideBuilder {
    internal var title: Content = EmptyContent
    internal var id: String = ""

    override var styleClass = setOf<String>()
    var content: Content = EmptyContent

    override fun invoke(presentationKey: String, groupKey: String?): BasicSlide =
        BasicSlide(
                id = id,
                title = title,
                styleClass = styleClass,
                content = content,
                contentType = ContentType.INTERNAL)

}
