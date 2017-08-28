package org.ilaborie.slides

import org.ilaborie.slides.content.*

interface Slides {
    fun toList(): List<Slide>
}

data class Presentation(val title: String, val slides: List<Slides> = emptyList()) : Slides {
    constructor(title: String, vararg slides: Slides) : this(title, slides.toList())

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


        // FIXME check content (external file exists)
    }
}


data class Group(val title: String,
                 val slides: List<Slide> = emptyList(),
                 val skipPart: Boolean = false,
                 val prefix: String = "") : Slides {
    override fun toList(): List<Slide> =
            if (skipPart) slides.toList()
            else listOf(PartTitleSlide(title)) + slides.toList()
///

    private fun resource(slideId: String, contentType: String): Content {
        val resource = "/$prefix/${title.normalize()}/${slides.size.format("00")}_$slideId.$contentType"
        return when {
            contentType.endsWith("html") -> ExternalHtmlContent(ExternalResource(resource))
            contentType.endsWith("md")   -> ExternalMarkdownContent(ExternalResource(resource))
            contentType.endsWith("svg")  -> ExternalSvgContent(ExternalResource(resource))
            else                         -> {
                val lang = Language.findForExtension(contentType)
                if (lang != null) {
                    ExternalCodeContent(lang, ExternalResource(resource))
                } else TODO()
            }
        }
    }

    fun slide(title: Content,
              id: String,
              styleClass: Set<String> = emptySet(),
              contentType: String = "md",
              contentBuilder: () -> Content = { resource(id, contentType) }): Group =
            this + BasicSlide(title = title, id = id, styleClass = styleClass, content = contentBuilder())

    fun slide(title: String,
              id: String = title.normalize(),
              styleClass: Set<String> = emptySet(),
              contentType: String = "md",
              contentBuilder: () -> Content = { resource(id, contentType) }): Group =
            this + BasicSlide(title = title, id = id, styleClass = styleClass, content = contentBuilder())

    operator fun plus(slide: Slide): Group =
            this.copy(slides = slides + slide)
}

