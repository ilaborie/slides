package org.ilaborie.slides

interface Slides {
    fun toList(): List<Slide>
}

data class Presentation(val title: String, private val slides: List<Slides>) : Slides {
    constructor(title: String, vararg slides: Slides) : this(title, slides.toList())

    override fun toList(): List<Slide> =
            listOf(MainTitleSlide(title)) + slides.flatMap { it.toList() }

    init {
        // FIXME check id unique
        // FIXME check content (external file exists)
    }
}


data class Group(val title: String, val slides: Slides) : Slides {
    constructor(title: String, vararg slides: Slide) : this(title, object : Slides {
        override fun toList() = slides.toList()
    })

    override fun toList(): List<Slide> =
            listOf(PartTitleSlide(title)) + slides.toList()

}

