package css_awesome

import org.ilaborie.slides.Group
import org.ilaborie.slides.content.*

private val titleLeastPower = Link("The Rule of Least Power", "https://www.w3.org/2001/tag/doc/leastPower.html")

fun intro(group: Group) = group
        .slide(title = Code(code = "$ whoami"), id = "whoami") {
            "Igor Laborie".h4() +
                    Block(Block("Expert Java & Web,".raw()) +
                                  Link(ExternalSvgContent(ExternalResource("/cssIsAwesome/00_introduction/monkeypatch.svg")), "http://www.monkeypatch.io/")) +
                    Link("@ilaborie", "https://twitter.com/ilaborie") +
                    Link("igor@monkeypatch.io", "mailto:igor@monkeypatch.io") +
                    "⚠️ Je ne suis pas un designer".em()
        }
        .slide(title = titleLeastPower, id = "least-power") {
            Quote(HtmlContent("When designing computer systems, one is often faced with a choice between using a more or less powerful language for publishing information, for expressing constraints, or for solving some problem. This finding explores tradeoffs relating the choice of language to reusability of information. The \"Rule of Least Power\" suggests <strong>choosing the least powerful language suitable</strong> for a given purpose."))
        }
        .slide(title = "Règles du jeu") {
            listOf("Texte",
                   "HTML (sémantique) & CSS (layout, style, animations simples)",
                   "SVG (formes et animations complexes)",
                   "JavaScript, ...").ol() +
                    "⚠️... mais il y a toujours de bonnes raisons pour ne pas suivre ces règles".em()
        }
        .slide(title = "Le CSS c'est vaste", styleClass = setOf("two-columns")) {
            listOf("Selectors",
                   "Box model",
                   "Float",
                   "Media Query",
                   "Transitions",
                   "Gradients",
                   "Responsive Design",
                   "Media",
                   "Variables",
                   "Colors",
                   "Shapes",
                   "...").ul()
        }
        .roadMap(title = "Plan")