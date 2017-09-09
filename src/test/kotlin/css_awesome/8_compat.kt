package css_awesome

import org.ilaborie.slides.Group
import org.ilaborie.slides.content.*

fun html(group: Group) = group
        .slide("Panel", "panel_html") {
            ExternalCodeContent(Language.HTML, ExternalResource("/cssIsAwesome/07_HTML/details.html")) +
                    cssLiveCode("/cssIsAwesome/07_HTML/panel")
        }
        .slide("Dialog") {
            cssLiveCode("/cssIsAwesome/07_HTML/dialog")
        }
        .slide("Polyfill") {
            UnorderedList(
                    Link("Collapsible Panel Polyfill", "https://github.com/chemerisuk/better-details-polyfill/"),
                    Link("Dialog Polyfill", "https://github.com/GoogleChrome/dialog-polyfill")
            )
        }