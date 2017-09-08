package css_awesome

import org.ilaborie.logger.Logger
import org.ilaborie.slides.*
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.content.*
import java.io.File


fun cssLiveCode(prefix: String) =
        StyleEditable(ExternalResource("$prefix.css"), ExternalResource("$prefix-final.css")) +
                EditableZone(ExternalHtmlContent(ExternalResource("$prefix.html")))

fun main(args: Array<String>) {
    val logger = Logger("CSS")

    val title = "C<span class=\"logo-askew\">S</span>S is awesome!".html()
    val cssIsAwesome = Presentation(title = title, id = "cssIsAwesome")
            .group("Introduction", skipPart = true) { intro(this) }
            .group("Utiliser un pré&#8209;processeur ?", "preprocessor") {
                this
                        .slide(title = "Bordure des boutons", contentType = HTML, styleClass = setOf("hide-title")) {
                            cssLiveCode("/cssIsAwesome/01_preprocessor/boutons")
                        }
                        .slide(title = "Alors utilise-t-on un pré&#8209;processeurs ?") {
                            Block("Oui, mais privilégiez:".p() +
                                          listOf("le CSS", "les post&#8209;processeurs").ul()) +
                                    UnorderedList(
                                            Link(Code(code = "currentColor"), "https://css-tricks.com/currentcolor/"),
                                            Link(Code(code = "background-origin"), "https://developer.mozilla.org/fr/docs/Web/CSS/background-origin"),
                                            Link("CSS Variables (aka Custom Properties)", "https://www.w3.org/TR/css-variables/"),
                                            Link("CSS Color Module Level 4", "https://www.w3.org/TR/css-color-4/"))
                        }
            }
            .group("Unités") { unites(this) }
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
                slide(title = "Le dinner d'un philosophe", styleClass = setOf("hide-title")) {
                    cssLiveCode("/cssIsAwesome/04_pseudo_elements/philosophe")
                }
                        .slide(title = "Triangle avec des bordures", styleClass = setOf("hide-title")) {
                            cssLiveCode("/cssIsAwesome/04_pseudo_elements/border")
                        }
                        .slide(title = "Info-bulle", styleClass = setOf("hide-title")) {
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
            .group("Pseudo classes d'état", "pseudo_classes") {
                slide(title = "Usage des info-bulles", contentType = HTML)
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
                        .slide(title = "Checkbox", styleClass = setOf("hide-title")) {
                            cssLiveCode("/cssIsAwesome/06_pseudo_classes/checkbox")
                        }
                        .slide(title = "Switch", styleClass = setOf("hide-title")) {
                            cssLiveCode("/cssIsAwesome/06_pseudo_classes/switch")
                        }
                        .slide(title = "Panel", styleClass = setOf("hide-title")) {
                            cssLiveCode("/cssIsAwesome/06_pseudo_classes/panel")
                        }
                        .slide(title = "Principe pour les onglets") {
                            ExternalCodeContent(Language.HTML, ExternalResource("/cssIsAwesome/06_pseudo_classes/tab.html"))
                        }
                        .slide(title = "Démo des onglets") {
                            ExternalHtmlContent(ExternalResource("/cssIsAwesome/06_pseudo_classes/tab.html"))
                        }
                        .slide(title = "Bilan Pseudo classes") {
                            "TODO".strong()
                        }
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
            .group("Conclusion") { conclusion(this) }

    val slidesDir = File("src/test/resources/")

    if (cssIsAwesome.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        cssIsAwesome.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web")
    cssIsAwesome.writeHtmlTo(dist, "devfest-tls")
    cssIsAwesome.writeMarkdownTo(dist, "devfest-tls")

    val holyGrail = slidesDir.resolve(cssIsAwesome.id).resolve("holy-grail.html")
    holyGrail.copyTo(target = dist.resolve("holy-grail-calc.html"), overwrite = true)
    holyGrail.copyTo(target = dist.resolve("holy-grail-flexbox.html"), overwrite = true)
    holyGrail.copyTo(target = dist.resolve("holy-grail-grid.html"), overwrite = true)
}