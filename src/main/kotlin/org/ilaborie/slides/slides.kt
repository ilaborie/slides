package org.ilaborie.slides

import org.ilaborie.slides.ContentType.MARKDOWN
import org.ilaborie.slides.content.Content

interface Slides {
    fun toList(): List<Slide>
}

data class Presentation(val title: String,
                        val id: String = title.normalize(),
                        val slides: List<Slides> = emptyList()) : Slides {

    operator fun invoke(s: Slides): Int = slides.indexOf(s)

    override fun toList(): List<Slide> =
            listOf(MainTitleSlide(title)) + slides.flatMap { it.toList() }

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

    fun group(title: String,
              id: String = title.normalize(),
              skipPart: Boolean = false,
              slidesBuilder: Group.() -> Group = { this }): Presentation =
            this + Group(title = title, id = id, skipPart = skipPart).slidesBuilder()

    operator fun plus(addon: Slides) =
            this.copy(slides = slides + addon)
}

data class Group(val title: String,
                 val id: String = title.normalize(),
                 val slides: List<Slide> = emptyList(),
                 val skipPart: Boolean = false) : Slides {

    operator fun invoke(s: Slide): Int = slides.indexOf(s)

    override fun toList(): List<Slide> =
            if (skipPart) slides.toList()
            else listOf(PartTitleSlide(title)) + slides.toList()
///

    fun slide(title: Content,
              id: String,
              styleClass: Set<String> = emptySet(),
              contentType: ContentType = MARKDOWN,
              contentBuilder: () -> Content? = { null }): Group =
            this + BasicSlide(title = title,
                              id = id,
                              styleClass = styleClass,
                              content = contentBuilder(),
                              contentType = contentType)

    fun slide(title: String,
              id: String = title.normalize(),
              styleClass: Set<String> = emptySet(),
              contentType: ContentType = MARKDOWN,
              contentBuilder: () -> Content? = { null }): Group =
            this + BasicSlide(title = title,
                              id = id,
                              styleClass = styleClass,
                              content = contentBuilder(),
                              contentType = contentType)

    operator fun plus(slide: Slide): Group =
            this.copy(slides = slides + slide)
}

