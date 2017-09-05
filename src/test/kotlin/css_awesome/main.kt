package css_awesome

import org.ilaborie.logger.Logger
import org.ilaborie.slides.*
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.content.*
import java.io.File


fun main(args: Array<String>) {
    val logger = Logger("CSS")

    val titleLeastPower = Link("The Rule of Least Power", "https://www.w3.org/2001/tag/doc/leastPower.html")

    fun cssLiveCode(prefix: String) =
            StyleEditable(ExternalResource("$prefix.css"), ExternalResource("$prefix-final.css")) +
                    EditableZone(ExternalHtmlContent(ExternalResource("$prefix.html")))


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
                slide(title = "Bordure des boutons", contentType = HTML, styleClass = setOf("hide-title")) {
                    cssLiveCode("/cssIsAwesome/01_preprocessor/boutons")
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
                slide(title = "Avec flexbox & Grid", styleClass = setOf("hide-title")) {
                    ExternalCodeContent(Language.HTML, ExternalResource("/cssIsAwesome/03_flexbox_et_grid/holy_grail.html")) +
                            Link("Exemple Flexbox", "./holy-grail-flexbox.html") +
                            Link("Exemple Grid", "./holy-grail-grid.html")
                }
                        .slide(title = "Bilan Flexbox & Grid") {
                            Block(
                                    "Flexbbox".h4() +
                                            UnorderedList(
                                                    "Si on est sur une seule ligne ou colonne".raw(),
                                                    Link("Flexbox, et le CSS redevient fun ! (Hubert SABLONNIÈRE)", "https://www.youtube.com/watch?v=5F_ngjHDcJQ"),
                                                    Link("Solved by Flexbox", "https://philipwalton.github.io/solved-by-flexbox/"),
                                                    Link("Flexbox Froggy", "https://flexboxfroggy.com/")
                                            )) +
                                    Block(
                                            "Grid".h4() +
                                                    UnorderedList(
                                                            "Si plusieurs lignes et colonnes".raw(),
                                                            Link("Grid by exemples", "https://gridbyexample.com/"),
                                                            Link("Grid Garden", "http://cssgridgarden.com/")
                                                    ))
                        }
            }
            .group("Pseudo éléments") {
                slide(title = "Le dinner d'un philosophe", contentType = HTML, styleClass = setOf("hide-title")) {
                    cssLiveCode("/cssIsAwesome/04_pseudo_elements/philosophe")
                }
                        .slide(title = "Triangle avec des bordures", contentType = HTML, styleClass = setOf("hide-title")) {
                            cssLiveCode("/cssIsAwesome/04_pseudo_elements/border")
                        }
                        .slide(title = "Info-bulle", contentType = HTML, styleClass = setOf("hide-title")) {
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
            }
            .group("Animations") {
                slide(title = "Texte de chargement", contentType = HTML, styleClass = setOf("hide-title")) {
                    cssLiveCode("/cssIsAwesome/05_animations/loader")
                }
                        .slide(title = "Dessiner") {
                            cssLiveCode("/cssIsAwesome/05_animations/draw")
                        }
                        .slide(title = "Bilan animations") {
                            UnorderedList(
                                    Link("Utiliser les animations CSS", "https://developer.mozilla.org/fr/docs/Web/CSS/Animations_CSS/Utiliser_les_animations_CSS"),
                                    Link("Text Spinner", "http://tawian.io/text-spinners/"),
                                    Link("CSS only loader", "https://www.pexels.com/blog/css-only-loaders/"),
                                    Link("Animate.css", "https://daneden.github.io/animate.css/"),
                                    Link("How SVG Line Animation Works", "https://css-tricks.com/svg-line-animation-works/"),
                                    Link("<code>\$lt;progress\$gt;</code>", "https://developer.mozilla.org/fr/docs/Web/HTML/Element/Progress")
                            )
                        }
            }
            .group("Pseudo classes d'état") {
                slide(title = "Usage des info-bulles")
                        .slide(title = "Pseudo états") {
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
                        .slide(title = "Checkbox", styleClass = setOf("hide-title")) {
                            cssLiveCode("/cssIsAwesome/06_pseudo_classes_d_etat/checkbox")
                        }
                        .slide(title = "Switch", styleClass = setOf("hide-title")) {
                            cssLiveCode("/cssIsAwesome/06_pseudo_classes_d_etat/switch")
                        }
                        .slide(title = "Panel", styleClass = setOf("hide-title")) {
                            cssLiveCode("/cssIsAwesome/06_pseudo_classes_d_etat/panel")
                        }
                        .slide(title = "Principe pour les onglets") {
                            ExternalCodeContent(Language.HTML, ExternalResource("/cssIsAwesome/06_pseudo_classes_d_etat/tab.html"))
                        }
                        .slide(title = "Démo des onglets") {
                            ExternalHtmlContent(ExternalResource("/cssIsAwesome/06_pseudo_classes_d_etat/tab.html"))
                        }
                        .slide(title = "Bilan Pseudo classes")  // TODO
            }
            .group("HTML") {
                slide("Panel", "panel_html") {
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
            }
            .group("Compatibilité des navigateurs", "compat") {
                slide(title = "Compat", styleClass = setOf("hide-title")) {
                    Link("caniuse", "http://caniuse.com").h4() +
                            Link("The CSS3 / CSS4 Test", "http://css3test.com").h4() +
                            ExternalHtmlContent(ExternalResource("/cssIsAwesome/08_compat/support.html"))
                }
            }
            .group("Conclusion") {
                slide(title = "Bilan", styleClass = setOf("hide-title")) {
                    listOf(
                            "Utilisez du CSS pour simpifier le code",
                            "Utilisez intelligemment les pre/post processeurs",
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
                        .slide(title = "🦄 rocks !", contentType = HTML) {
                            cssLiveCode("/cssIsAwesome/09_conclusion/end")
                        }
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