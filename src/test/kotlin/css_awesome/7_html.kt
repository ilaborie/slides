package css_awesome

import org.ilaborie.slides.Group
import org.ilaborie.slides.content.*
import org.ilaborie.slides.content.web.CssCompatibility
import org.ilaborie.slides.content.web.EditableZone

private val progress = ExternalResource("/cssIsAwesome/07_HTML/progress.html")
fun html(group: Group) = group
        .slide("Progress") {
            Link("The HTML5 progress Element\n", "https://css-tricks.com/html5-progress-element/") +
                    ExternalCodeContent(Language.HTML, progress) +
                    EditableZone(ExternalHtmlContent(progress))
        }
        .slide("Panel", "panel_html") {
            ExternalCodeContent(Language.HTML, ExternalResource("/cssIsAwesome/07_HTML/panel.html")) +
                    cssLiveCode("/cssIsAwesome/07_HTML/panel")
        }
        .slide("Dialog", styleClass = setOf("live-code")) {
            cssLiveCode("/cssIsAwesome/07_HTML/dialog")
        }
        .slide("Polyfill") {
            UnorderedList(
                    Link("Better details polyfill", "https://github.com/chemerisuk/better-details-polyfill/"),
                    Link("Dialog Polyfill", "https://github.com/GoogleChrome/dialog-polyfill")
            )
        }
        .slide(title = "Compatibilité", id = "compat-7") {
            CssCompatibility(browsersThreshold, listOf("details", "dialog", "progress"))
        }
