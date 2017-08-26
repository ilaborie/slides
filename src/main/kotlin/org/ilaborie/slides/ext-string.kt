package org.ilaborie.slides

import java.text.Normalizer


fun String.normalize(): String =
        Normalizer.normalize(this, Normalizer.Form.NFD)
                .toLowerCase()
                .replace(Regex("[\\s]"), "-")
                .replace(Regex("[\\W]"), "_")
                .replace(Regex("[^\\p{ASCII}]"), "")


operator fun String.times(n: Int): String = when {
    n < 0  -> throw IllegalArgumentException("Negative number not allowed")
    n == 0 -> ""
    n == 1 -> this
    else   -> this + this.times(n - 1)
}

fun <T> safe(default: T, dangerous: () -> T): T =
        try {
            dangerous()
        } catch (e: Throwable) {
            // e.printStackTrace()
            default
        }