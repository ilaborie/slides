package css_awesome

import org.ilaborie.slides.Group
import org.ilaborie.slides.content.*

fun pseudoElt(group: Group) = group
        .slide(title = "Le dinner d'un philosophe", styleClass = setOf("live-code")) {
            cssLiveCode("/cssIsAwesome/04_pseudo_elements/philosophe")
        }
        .slide(title = "Triangle avec des bordures", styleClass = setOf("live-code")) {
            cssLiveCode("/cssIsAwesome/04_pseudo_elements/border")
        }
        .slide(title = "Info-bulle", styleClass = setOf("live-code")) {
            cssLiveCode("/cssIsAwesome/04_pseudo_elements/popover")
        }
        .slide(title = "Bilan pseudo éléments") {
            UnorderedList(
                    Link("The :before and :after pseudo-elements", "https://www.w3.org/TR/CSS22/generate.html#before-after-content"),
                    "mais aussi <code>::first-letter</code>, <code>::first-line</code>, <code>::selection</code>, <code>::backdrop</code>".html(),
                    Link("An Ultimate Guide To CSS Pseudo-Classes And Pseudo-Elements", "https://www.smashingmagazine.com/2016/05/an-ultimate-guide-to-css-pseudo-classes-and-pseudo-elements")
            ) +
                    "⚠️ <code>::before</code> et <code>::after</code> ne marchent pas sur <code>input</code>, <code>img</code>, <code>iframe</code> (pas encore spécifié)".html().p() +
                    UnorderedList(
                            "Table et assiette de ".raw() + Link("CSS Diner", "https://flukeout.github.io/"),
                            Link("Dîner des philosophes", "https://fr.wikipedia.org/wiki/D%C3%AEner_des_philosophes"))
        }