package css_awesome

import org.ilaborie.slides.ContentType
import org.ilaborie.slides.Group
import org.ilaborie.slides.content.*

fun pseudoState(group: Group) = group
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
        .slide(title = "Checkbox") {
            cssLiveCode("/cssIsAwesome/06_pseudo_classes/checkbox")
        }
        .slide(title = "Switch") {
            Block(
                    StyleEditable(ExternalResource("/cssIsAwesome/06_pseudo_classes/switch-1.css")) +
                            StyleEditable(ExternalResource("/cssIsAwesome/06_pseudo_classes/switch-2.css"))
            ) +
                    EditableZone(ExternalHtmlContent(ExternalResource("/cssIsAwesome/06_pseudo_classes/switch.html")))
        }
        .slide(title = "Panel") {
            cssLiveCode("/cssIsAwesome/06_pseudo_classes/panel")
        }
        .slide(title = "Principe pour les onglets") {
            ExternalCodeContent(Language.HTML, ExternalResource("/cssIsAwesome/06_pseudo_classes/tab.html"))
        }
        .slide(title = "Démo des onglets") {
            EditableZone(ExternalHtmlContent(ExternalResource("/cssIsAwesome/06_pseudo_classes/tab.html")))
        }