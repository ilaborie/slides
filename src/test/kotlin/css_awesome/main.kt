package css_awesome

import org.ilaborie.logger.Logger
import org.ilaborie.slides.*
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.content.*
import java.io.File


fun main(args: Array<String>) {
    val logger = Logger("CSS")

    val titleLeastPower = Link("The Rule of Least Power", "https://www.w3.org/2001/tag/doc/leastPower.html")

    val cssIsAwesome = Presentation(title = "CSS is Awesome !", id = "cssIsAwesome")
            .group("Introduction", skipPart = true) {
                slide(title = Code(code = "$ whoami"), id = "whoami")
                        .slide(title = titleLeastPower, id = "least-power") {
                            Quote(MarkdownContent("When designing computer systems, one is often faced with a choice between using a more or less powerful language for publishing information, for expressing constraints, or for solving some problem. This finding explores tradeoffs relating the choice of language to reusability of information. The \"Rule of Least Power\" suggests **choosing the least powerful language suitable** for a given purpose."))
                        }
                        .slide(title = "Règles du jeu") {
                            listOf("Texte",
                                   "HTML & CSS",
                                   "SVG",
                                   "JavaScripts").ol() +
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
                        .slide(title = "Plan") {
                            // TODO build RoadMap with default resource
                            EmptyContent
                        }
            }
            .group("Utiliser un pré-processeur ?", "preprocessor") {
                slide(title = "LiveCoding: boutons", contentType = HTML, styleClass = setOf("hide-title"))
                        .slide(title = "Alors utilise-t-on un pré-processeurs ?")
            }
            .group("Unités") {
                slide(title = "Une histoire d’unités CSS")
                        .slide(title = "Les unités de longueur") {
                            Definitions(
                                    "px, cm, pt, ..." to "longueurs absolues (mesure physique)".html(),
                                    "em, rem" to "fonction de la <code>font-size</code>".html(),
                                    "ex, ch" to "hauteur d'un <code>x</code>, largeur d'un <code>0</code>".html(),
                                    "vh, vw" to "(100vh, 100vw) = (hauteur, largeur) du <i>viewport</i>".html(),
                                    "vmin, vmax" to "min(1vh, 1vw), max(1vh, 1vw)".raw())
                        }
                        .slide(title = "LiveCoding: Holy Grail Layout avec calc", styleClass = setOf("hide-title"))
                        .slide(title = "Bilan unités")
            }
            .group("Flexbox et Grid") {
                slide(title = "LiveCoding: Holy Grail Layout avec flexbox", styleClass = setOf("hide-title"))
                        .slide(title = "LiveCoding: Holy Grail Layout avec grid", styleClass = setOf("hide-title"))
                        .slide(title = "Bilan Flexbox & Grid")
            }
            .group("Pseudo éléments") {
                slide(title = "LiveCoding: le dinner d'un philosophe", contentType = HTML, styleClass = setOf("hide-title"))
                        .slide(title = "LiveCoding: Triangle avec des bordures", contentType = HTML, styleClass = setOf("hide-title"))
                        .slide(title = "LiveCoding: Info-bulle", contentType = HTML, styleClass = setOf("hide-title"))
                        .slide(title = "Bilan pseudo éléments")
            }
            .group("Animations") {
                slide(title = "LiveCoding: texte de chargement", contentType = HTML, styleClass = setOf("hide-title"))
                        .slide(title = "Bilan animations")
            }
            .group("Pseudo classes d'état") {
                slide(title = "Usage des info-bulles")
                        .slide(title = "LiveCoding: Checkbox", contentType = HTML, styleClass = setOf("hide-title"))
                        .slide(title = "LiveCoding: Switch", contentType = HTML, styleClass = setOf("hide-title"))
                        .slide(title = "LiveCoding: Collapsible panel", contentType = HTML, styleClass = setOf("hide-title"))
                        .slide(title = "Principe pour les onglets")
                        .slide(title = "Démo des onglets")
                        .slide(title = "Bilan Pseudo classes")
            }
            .group("Compatibilité des navigateurs") {
                slide(title = "Partie 1", styleClass = setOf("hide-title"))
                        .slide(title = "Partie 2", styleClass = setOf("hide-title"))
            }
            .group("Conclusion") {
                slide(title = "Bilan", styleClass = setOf("hide-title"))
                        .slide(title = "Traitez le CSS comme du code")
                        .slide(title = "Liens")
                        .slide(title = "Pour apprendre")
                        .slide(title = "🦄 rocks !", contentType = HTML)
            }


    val slidesDir = File("src/test/resources/")

    if (cssIsAwesome.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        cssIsAwesome.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web")
    cssIsAwesome.writeHtmlTo(dist, "index")
    cssIsAwesome.writeMarkdownTo(dist, "index")

}