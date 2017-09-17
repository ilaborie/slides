package org.ilaborie.slides

import java.text.Normalizer

fun String.normalize(): String =
        Normalizer.normalize(this, Normalizer.Form.NFD)
                .toLowerCase()
                .replace(Regex("[\\s]"), "-")
                .replace(Regex("[^\\p{ASCII}]"), "")
                .replace(Regex("[\\W]"), "_")


operator fun Char.times(n: Int): String = when {
    n < 0  -> throw IllegalArgumentException("Negative number not allowed")
    n == 0 -> ""
    n == 1 -> this.toString()
    else   -> this + this.times(n - 1)
}


fun String.underline(underline: Char): String =
        "$this\n${underline * this.length}"


fun String.indent(indentation: String = "  "): String =
        this.lines().joinToString("\n") { indentation + it }