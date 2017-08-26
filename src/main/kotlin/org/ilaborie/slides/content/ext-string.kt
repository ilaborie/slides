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
    else                                            -> null
}
