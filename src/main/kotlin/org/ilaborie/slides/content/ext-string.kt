package org.ilaborie.slides.content


fun Content.renderAsString(): String? = when (this) {
    is Title            -> title.renderAsString()
    is RawContent       -> content
    is Link             -> text
    is CompositeContent -> {
        val lst = contents
                .map { it.renderAsString() }
                .filterIsInstance<String>()
        if (lst.isNotEmpty()) lst.joinToString(separator = "\n") else null
    }
    else                -> null
}


// Extension
fun String.raw(): Content = RawContent(this)

fun String.html(): Content = HtmlContent(this)
fun String.hX(x: Int): Content = Title(raw(), x)
fun String.h1(): Content = hX(1)
fun String.h2(): Content = hX(2)
fun String.h3(): Content = hX(3)

fun String.strong(): Content = this.raw().strong()
fun String.em(): Content = this.raw().em()
fun String.p(): Content = this.raw().p()
fun String.quote(): Content = this.raw().quote()

fun List<String>.ul(): Content = UnorderedList(this.map { it.raw() })
fun List<String>.ol(): Content = OrderedList(this.map { it.raw() })

fun Content.hX(x: Int): Content = Title(this, x)
fun Content.h1(): Content = hX(1)
fun Content.h2(): Content = hX(2)
fun Content.h3(): Content = hX(3)

fun Content.strong(): Content = Strong(this)
fun Content.em(): Content = Emphasis(this)
fun Content.p(): Content = Paragraph(this)
fun Content.quote(author: String? = null, cite: String? = null): Content = Quote(this, author, cite)

