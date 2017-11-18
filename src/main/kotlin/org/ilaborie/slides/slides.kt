package org.ilaborie.slides

import org.ilaborie.slides.content.Content
import org.ilaborie.slides.content.HtmlContent
import org.ilaborie.slides.dsl.ContentContainer
import org.ilaborie.slides.dsl.SlideBuilder

interface Slides {
    val id: String
    fun toList(): List<Slide>
    fun removeSlide(slideId: String): Slides
    fun replaceSlide(slideKeys: String,
                     newTitle: String? = null,
                     replacement: ContentContainer.() -> Unit): Slides
}

data class Presentation(val title: Content,
                        override val id: String,
                        val slides: List<Slides> = emptyList(),
                        val scripts: List<String> = emptyList()) : Slides {
    val coverSlide by lazy { MainTitleSlide(title, id) }

    operator fun invoke(s: Slides): Int = slides.indexOf(s)

    override fun toList(): List<Slide> =
        listOf(coverSlide) + slides.flatMap { it.toList() }

    init {
        // check id unique
        val multipleId = slides.flatMap { it.toList() }
            .groupBy { it.id() }
            .filterValues { it.size > 1 }
            .toList()

        if (multipleId.isNotEmpty()) {
            val errors = multipleId.joinToString(separator = " and ") { (id, list) ->
                "$id: ${list.joinToString(prefix = "{", postfix = "}")}"
            }
            throw IllegalArgumentException("Same id used !\n$errors")
        }
    }

    operator fun plus(addon: Slides) =
        copy(slides = slides + addon)

    override fun removeSlide(slideId: String) =
        copy(slides = slides
            .filterNot { it.id == slideId }
            .map { it.removeSlide(slideId) })

    override fun replaceSlide(slideKeys: String,
                              newTitle: String?,
                              replacement: ContentContainer.() -> Unit): Presentation {
        val (pKey) = slideKeys.split("/")
        return if (pKey != id) this.copy() else
            copy(slides = slides.map { it.replaceSlide(slideKeys, newTitle, replacement) })
    }
}

data class Group(val title: String,
                 override val id: String = title.normalize(),
                 val slides: List<Slide> = emptyList(),
                 val skipPart: Boolean = false) : Slides {

    operator fun invoke(s: Slide): Int = slides.indexOf(s)

    override fun toList(): List<Slide> =
        if (skipPart) slides.toList()
        else listOf(PartTitleSlide(title)) + slides.toList()

    operator fun plus(slide: Slide): Group =
        copy(slides = slides + slide)


    override fun removeSlide(slideId: String) =
        copy(slides = slides.filterNot { it.id == slideId })

    override fun replaceSlide(slideKeys: String,
                              newTitle: String?,
                              replacement: ContentContainer.() -> Unit): Group {
        val (pKey, gKey, sKey) = slideKeys.split("/")
        fun newSlide(previous: Slide): Slide {
            val c = ContentContainer()
            replacement(c)
            val b = SlideBuilder().apply {
                title = newTitle?.let { HtmlContent(it) } ?: previous.title()
                id = previous.id()
                content = c()
            }
            return b(presentationKey = pKey, groupKey = gKey)
        }
        return if (gKey != id) this.copy() else
            copy(slides = slides
                .map { if (it.id == sKey) newSlide(it) else it })
    }
}

