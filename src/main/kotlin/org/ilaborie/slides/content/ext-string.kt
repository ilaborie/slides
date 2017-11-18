package org.ilaborie.slides.content

import org.jsoup.Jsoup


fun Content.renderAsString(): String = Jsoup.parse(this.renderAsHtml()).text()

fun String.escapeHtml():String = this
        .replace("\"", "&quot;")
        .replace(">", "&gt;")
        .replace("<", "&lt;")