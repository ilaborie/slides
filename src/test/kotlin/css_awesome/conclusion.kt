package css_awesome

import org.ilaborie.slides.Group
import org.ilaborie.slides.content.*

fun conclusion(group: Group) = group
        .slide(title = "Bilan", styleClass = setOf("hide-title")) {
            listOf(
                    "Utilisez du CSS pour simpifier le code",
                    "Utilisez intelligemment les pre/post&#8209;processeurs",
                    "HTML, SVG are Awesome !",
                    "JavaScript, TypeScript could be Awesome !").ol()
        }
        .slide(title = "Traitez le CSS comme du code") {
            listOf(
                    "Revue de code",
                    "DRY",
                    "Clean Code",
                    "Single Responsibility Principle",
                    "...").ol()
        }
        .slide(title = "Liens") {
            UnorderedList(
                    Link("les slides", ""),
                    Link("le code", ""),
                    Link("Making Of", ""))
        }
        .slide(title = "Pour apprendre") {
            UnorderedList(
                    Code("(Ctrl|Cmd) + Shift + i"),
                    ExternalImageContent("CSS Secret", ExternalLink("http://lea.verou.me/cover.png")) +
                            Link("CSS Secret by Lea Verou", "https://www.amazon.fr/CSS-Secrets-Lea-Verou/dp/1449372635"),
                    Link("CSS sur MDN", "https://developer.mozilla.org/fr/docs/Web/CSS"),
                    Link("CodePen", "https://codepen.io/") +
                            Link("JSFiddle", "https://jsfiddle.net/") +
                            Link("Dabblet", "http://dabblet.com/") + "...".raw(),
                    Link("CSS Tricks", ""),
                    Link("Shop Talk Show", ""),
                    Link("CSS Flags", "")
            )
        }
        .slide(title = "🦄 rocks !", styleClass = setOf("hide-title")) {
            cssLiveCode("/cssIsAwesome/09_conclusion/end")
        }