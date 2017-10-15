package org.ilaborie.slides

import org.ilaborie.slides.ContentType.MARKDOWN
import org.ilaborie.slides.content.Content
import org.ilaborie.slides.content.raw

interface Slides {
    val id: String
    fun toList(): List<Slide>
    fun removeSlide(slideId: String): Slides
    fun replaceSlide(slideId: String, replacement: Slide): Slides
}

data class Presentation(val title: Content,
                        override val id: String,
                        val slides: List<Slides> = emptyList()) : Slides {
    constructor(title: String, id: String = title.normalize()) : this(title = title.raw(), id = id)

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

    fun group(title: String,
              id: String = title.normalize(),
              skipPart: Boolean = false,
              slidesBuilder: Group.() -> Group = { this }): Presentation =
        this + Group(title = title, id = id, skipPart = skipPart).slidesBuilder()

    operator fun plus(addon: Slides) =
        copy(slides = slides + addon)

    override fun removeSlide(slideId: String) =
        copy(slides = slides
            .filterNot { it.id == slideId }
            .map { it.removeSlide(id) })

    fun replaceGroup(groupId: String, replacement: Group): Slides =
        copy(slides = slides
            .map { if (it.id == groupId) replacement else it })

    override fun replaceSlide(slideId: String, replacement: Slide) =
        copy(slides = slides
            .map { it.replaceSlide(slideId, replacement) }
            .map { if (it.id == slideId) replacement else it })
}

data class Group(val title: String,
                 override val id: String = title.normalize(),
                 val slides: List<Slide> = emptyList(),
                 val skipPart: Boolean = false) : Slides {

    operator fun invoke(s: Slide): Int = slides.indexOf(s)

    override fun toList(): List<Slide> =
        if (skipPart) slides.toList()
        else listOf(PartTitleSlide(title)) + slides.toList()
///

    fun roadMap(title: String): Group =
        this + RoadMapSlide(title)

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
        copy(slides = slides + slide)


    override fun removeSlide(slideId: String) =
        copy(slides = slides.filterNot { it.id == slideId })

    override fun replaceSlide(slideId: String, replacement: Slide) =
        copy(slides = slides
            .map { if (it.id == slideId) replacement else it })
}

