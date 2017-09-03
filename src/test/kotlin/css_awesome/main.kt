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
                            Quote(HtmlContent("When designing computer systems, one is often faced with a choice between using a more or less powerful language for publishing information, for expressing constraints, or for solving some problem. This finding explores tradeoffs relating the choice of language to reusability of information. The \"Rule of Least Power\" suggests <strong>choosing the least powerful language suitable</strong> for a given purpose."))
                        }
                        .slide(title = "Règles du jeu") {
                            listOf("Texte",
                                   "HTML (sémantique) & CSS (layout, style, animations simples)",
                                   "SVG (formes et animations complexes)",
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
                        .roadMap(title = "Plan")
            }
            .group("Utiliser un pré-processeur ?", "preprocessor") {
                slide(title = "LiveCoding: boutons", contentType = HTML, styleClass = setOf("hide-title")) {
                    StyleEditable(
                            ExternalResource("/cssIsAwesome/01_preprocessor/boutons.css"),
                            ExternalResource("/cssIsAwesome/01_preprocessor/boutons_final.css")) +
                            EditableZone(ExternalHtmlContent(ExternalResource("/cssIsAwesome/01_preprocessor/boutons.html")))
                }
                        .slide(title = "Alors utilise-t-on un pré-processeurs ?") {
                            Block("Oui, mais privilégiez:".p() +
                                          listOf("le CSS", "les post-processeurs").ul()) +
                                    UnorderedList(
                                            Link(Code(code = "currentColor"), "https://css-tricks.com/currentcolor/"),
                                            Link(Code(code = "background-origin"), "https://developer.mozilla.org/fr/docs/Web/CSS/background-origin"),
                                            Link("CSS Variables (aka Custom Properties)", "https://www.w3.org/TR/css-variables/"),
                                            Link("CSS Color Module Level 4", "https://www.w3.org/TR/css-color-4/"))
                        }
            }
            .group("Unités") {
                slide(title = "Une histoire d’unités CSS") {
                    Figure("Une histoire d’unités CSS".raw(),
                           ExternalLink("https://www.commitstrip.com/wp-content/uploads/2016/10/Strip-High-Level-CSS-650-final-2.jpg"),
                           Link("CommitStrip", "http://www.commitstrip.com/fr/"))
                }
                        .slide(title = "Les unités de longueur") {
                            Definitions(
                                    "px, cm, pt, ..." to "longueurs absolues (mesure physique)".html(),
                                    "em, rem" to "fonction de la <code>font-size</code>".html(),
                                    "ex, ch" to "hauteur d'un <code>x</code>, largeur d'un <code>0</code>".html(),
                                    "vh, vw" to "(100vh, 100vw) = (hauteur, largeur) du <i>viewport</i>".html(),
                                    "vmin, vmax" to "min(1vh, 1vw), max(1vh, 1vw)".raw())
                        }
                        .slide(title = "Holy Grail Layout avec calc", styleClass = setOf("hide-title")) {
                            ExternalCodeContent(Language.HTML, ExternalResource("/cssIsAwesome/02_unites/holy_grail.html")) +
                                    Link("Live coding", "./holy-grail.html")
                        }
                        .slide(title = "Bilan unités") {
                            UnorderedList(
                                    Link("Unités", "https://developer.mozilla.org/fr/docs/Web/CSS/length) et [Truc et astuces](https://www.w3.org/Style/Examples/007/units.fr.html"),
                                    Link(Code(code = "calc"), "https://developer.mozilla.org/fr/docs/Web/CSS/calc")
                            )
                        }
            }
            .group("Flexbox et Grid") {
                slide(title = "Holy Grail Layout avec flexbox & Grid", styleClass = setOf("hide-title")) {
                    ExternalCodeContent(Language.HTML, ExternalResource("/cssIsAwesome/03_flexbox_et_grid/holy_grail.html")) +
                            Link("Exemple Flexbox", "./holy-grail-flexbox.html") +
                            Link("Exemple Flexbox", "./holy-grail-grid.html")
                }
                        .slide(title = "Bilan Flexbox & Grid") {
                            "Flexbbox".h3() +
                                    UnorderedList(
                                            "Si on est sur une seule ligne ou colonne".raw(),
                                            Link("Flexbox, et le CSS redevient fun ! (Hubert SABLONNIÈRE)", "https://www.youtube.com/watch?v=5F_ngjHDcJQ"),
                                            Link("Solved by Flexbox", "https://philipwalton.github.io/solved-by-flexbox/"),
                                            Link("Flexbox Froggy", "https://flexboxfroggy.com/")
                                    )
                            "Grid".h3() +
                                    UnorderedList(
                                            "Si plusieurs lignes et colonnes".raw(),
                                            Link("Grid by exemples", "https://gridbyexample.com/"),
                                            Link("Grid Garden", "http://cssgridgarden.com/")
                                    )
                        }
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