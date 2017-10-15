package css_awesome

import org.ilaborie.slides.ContentType
import org.ilaborie.slides.Group
import org.ilaborie.slides.content.*
import org.ilaborie.slides.content.web.CssCompatibility
import org.ilaborie.slides.content.web.EditableZone
import org.ilaborie.slides.content.web.StyleEditable

fun pseudoState(country:String, group: Group) = group
        .slide(title = "Usage des info-bulles", contentType = ContentType.HTML)
        .slide(title = "Pseudo états", styleClass = setOf("two-columns")) {
            UnorderedList(
                    Code(":hover"),
                    Code(":focus"),
                    Code(":visited"),
                    Code(":checked"),
                    Code(":valid"),
                    Code(":invalid"),
                    Code(":empty"),
                    Code(":target"),
                    "...".raw())
        }
        .slide(title = "Checkbox Hack", styleClass = setOf("live-code")) {
            cssLiveCode("/cssIsAwesome/06_pseudo_classes/checkbox")
        }
        .slide(title = "Switch") {
            val initialCss = ExternalResource("/cssIsAwesome/06_pseudo_classes/switch-1.css")
            val initialCss1 = ExternalResource("/cssIsAwesome/06_pseudo_classes/switch-2.css")
            StyleEditable(initialCss) +
                    ExternalCodeContent(Language.CSS, initialCss) +
                    StyleEditable(initialCss1) +
                    ExternalCodeContent(Language.CSS, initialCss1) +
                    EditableZone(ExternalHtmlContent(ExternalResource("/cssIsAwesome/06_pseudo_classes/switch.html")))
        }
        .slide(title = "Panel", styleClass = setOf("live-code")) {
            Link("Hiding Content for Accessibility", "https://snook.ca/archives/html_and_css/hiding-content-for-accessibility") +
                    cssLiveCode("/cssIsAwesome/06_pseudo_classes/panel")
        }
        .slide(title = "Principe pour les onglets") {
            ExternalCodeContent(Language.HTML, ExternalResource("/cssIsAwesome/06_pseudo_classes/tab.html"))
        }
        .slide(title = "Démo des onglets") {
            EditableZone(ExternalHtmlContent(ExternalResource("/cssIsAwesome/06_pseudo_classes/tab.html")))
        }
        .slide(title = "Compatibilité", id = "compat-6") {
            CssCompatibility(country, browsersThreshold, listOf("css-sel3", "form-validation", "transforms3d"))
        }