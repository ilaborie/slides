package css_awesome

import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.content.Code
import org.ilaborie.slides.content.HtmlContent
import org.ilaborie.slides.content.Link
import org.ilaborie.slides.dsl.*


fun main(args: Array<String>) {
//    val slidesDir = File("src/main/slides-resources/")
//    val dist = File("src/main/web")
//
//    // Devfest Toulouse
    val cssIsAwesome = cssIsAwesome(country = "FR", browsersThreshold = 0.4)
    println("Skip generation of $cssIsAwesome")
//
//    // Devoxx Maroc
//    val cssIsAwesomeShort = cssIsAwesome(country = "MA", browsersThreshold = 0.5)
//        .removeSlide(slideId = "texte_de_chargement")
//        .replaceSlide(slideKeys = "/cssIsAwesome/conclusion/liens") {
//            ul {
//                linkText(link = "https://ilaborie.github.io/slides/devoxx-ma.html#cssIsAwesome") {
//                    "les slides en HTML"
//                }
//                linkText(link = "https://ilaborie.github.io/slides/devoxx-ma.pdf") {
//                    "les slides en PDF"
//                }
//                linkText(link = "https://github.com/ilaborie/slides") {
//                    "le code"
//                }
//                linkText(link = "http://www.monkeypatch.io/2017/05/02/MakingOf_CSS_is_Awesome.html") {
//                    "Blog: 'Making Of'"
//                }
//            }
//        }
//
////    cssIsAwesome.buildAll(dist, "devfest-tls")
////    cssIsAwesomeShort.buildAll(dist, "devoxx-ma")
//
//    copyExtraFiles(slidesDir.resolve(cssIsAwesome.id), dist.resolve(cssIsAwesome.id))
}

//private fun copyExtraFiles(srcDir: File, destDir: File) {
//    listOf("holy-grail.html", "holy-grail-calc.html", "holy-grail-flexbox.html", "holy-grail-grid.html")
//        .map { it to srcDir.resolve(it) }
//        .map { (fileName, srcDir) -> srcDir.copyTo(target = destDir.resolve(fileName), overwrite = true) }
//}

fun cssIsAwesome(country: String = "FR", browsersThreshold: Number = 0.5) =
    presentation(title = "CSS is Awesome !", key = "cssIsAwesome") {
        title = HtmlContent("C<span class=\"logo-askew\">S</span>S is awesome!")
        // Intro
        part(title = "Introduction") {
            skipHeader = true
            slide(title = "$ whoami", key = "whoami") {
                title = Code(code = "$ whoami")
                headerText(4) { "Igor Laborie" }
                block {
                    block { html { "Expert Java & Web," } }
                    link(link = "http://www.monkeypatch.io/", alt = "MonkeyPatch") {
                        svg(resource = "/cssIsAwesome/introduction/monkeypatch.svg")
                    }
                }
                linkText(link = "https://twitter.com/ilaborie", alt = "Twitter") { "@ilaborie" }
                linkText(link = "mailto:igor@monkeypatch.io", alt = "Twitter") { "igor@monkeypatch.io" }
                em { html { "⚠️ Je ne suis pas un designer" } }
            }
            slide(title = "The Rule of Least Power", key = "least-power") {
                title = Link("The Rule of Least Power", "https://www.w3.org/2001/tag/doc/leastPower.html")
                quote {
                    """When designing computer systems, one is often faced with a choice between using a more or less powerful language for publishing information, for expressing constraints, or for solving some problem.
                    |This finding explores tradeoffs relating the choice of language to reusability of information. The "Rule of Least Power" suggests <strong>choosing the least powerful language suitable</strong> for a given purpose.""".trimMargin()
                }
            }
            slide(title = "Règles du jeu") {
                ol {
                    html { "Texte" }
                    html { "CSS (layout, style, animations simples)" }
                    html { "SVG (formes et animations complexes)" }
                    html { "JavaScript, WebAssembly (gestion d'états, appel backend, calculs)" }
                }
                em {
                    html { "⚠️... mais il y a toujours de bonnes raisons pour ne pas suivre ces règles" }
                }
            }
            slide(title = "Le CSS c'est vaste", key = "omit", styleClass = setOf("two-columns")) {
                ul {
                    html { "Selectors" }
                    html { "Box model" }
                    html { "Float" }
                    html { "Media Query" }
                    html { "Animations" }
                    html { "Gradients" }
                    html { "Responsive Design" }
                    html { "Media" }
                    html { "Variables" }
                    html { "Colors" }
                    html { "Shapes" }
                    html { "..." }
                }
            }
            roadmap(title = "Plan")
        }
        // Préprocesseur
        part(title = "Utiliser un pré&#8209;processeur ?") {
            slide(title = "Bordure des boutons", styleClass = setOf("live-code")) {
                cssLiveCode("/cssIsAwesome/preprocessor/boutons")
            }
            slide(title = "Alors utilise-t-on un pré&#8209;processeurs ?") {
                block {
                    pText { "Oui, mais privilégiez :" }
                    ul {
                        html { "le CSS" }
                        html { "les post&#8209;processeurs" }
                    }
                }
                block {
                    ul {
                        link(link = "https://css-tricks.com/currentcolor/") {
                            code { "currentColor" }
                        }
                        link(link = "https://developer.mozilla.org/fr/docs/Web/CSS/background-origin") {
                            code { "background-origin" }
                        }
                        linkText(link = "https://www.w3.org/TR/css-variables/") {
                            "CSS Variables (aka Custom Properties)"
                        }
                        linkText(link = "https://www.w3.org/TR/css-color-4/") {
                            "CSS Color Module Level 4"
                        }
                    }
                }
            }
            slide(title = "Compatibilité", key = "compat-1") {
                cssCompatibility(country = country,
                                 threshold = browsersThreshold,
                                 features = listOf("currentcolor", "background-img-opts", "css-variables"))
            }
        }
        // Units
        part(title = "Unités") {
            slide(title = "Une histoire d’unités CSS") {
                figure(title = "Une histoire d’unités CSS",
                       link = "https://www.commitstrip.com/wp-content/uploads/2016/10/Strip-High-Level-CSS-650-final-2.jpg")
                link(link = "http://www.commitstrip.com/fr/2016/10/10/a-story-about-css-units/") {
                    html { "CommitStrip" }
                }

            }
            slide(title = "Les unités de longueur") {
                definitions {
                    html { "px, cm, pt, ..." } to html { "longueurs absolues (mesure physique)" }
                    html { "em, rem" } to html { "fonction de la <code>font-size</code>" }
                    html { "ex, ch" } to html { "hauteur d'un <code>x</code>, largeur d'un <code>0</code>" }
                    html { "vh, vw" } to html { "(100vh, 100vw) = (hauteur, largeur) du <i>viewport</i>" }
                    html { "vmin, vmax" } to html { "min(1vh, 1vw), max(1vh, 1vw)" }
                }
            }
            slide(title = "Holy Grail avec <code>calc</code>", key = "holy-grail-calc") {
                htmlFromResource("/cssIsAwesome/unites/holy_grail-inner.html")
                codeFromResource("/cssIsAwesome/unites/holy_grail.html")
            }
            slide(title = "Bilan unités") {
                ul {
                    linkText(link = "https://developer.mozilla.org/fr/docs/Web/CSS/length") {
                        "Unités"
                    }
                    linkText(link = "https://www.w3.org/Style/Examples/007/units.fr.html") {
                        "Truc et astuces"
                    }
                    link(link = "https://developer.mozilla.org/fr/docs/Web/CSS/calc") {
                        code { "calc" }
                    }
                    linkText(link = "https://css-tricks.com/fun-viewport-units/") {
                        "Fun with Viewport Units"
                    }
                }
            }
            slide(title = "Compatibilité", key = "compat-2") {
                cssCompatibility(country = country,
                                 threshold = browsersThreshold,
                                 features = listOf("rem", "viewport-units", "calc"))
            }
        }
        // Flexbox, Grid
        part(title = "Flexbox et Grid") {
            slide(title = "Holy Grail avec <code>flexbox</code>", key = "holy-grail-flexbox") {
                htmlFromResource("/cssIsAwesome/unites/holy_grail-inner.html")
                codeFromResource("/cssIsAwesome/unites/holy_grail.html")
            }
            slide(title = "Holy Grail avec <code>grid</code>", key = "holy-grail-grid") {
                htmlFromResource("/cssIsAwesome/unites/holy_grail-inner.html")
                codeFromResource("/cssIsAwesome/unites/holy_grail.html")
            }
            slide(title = "Bilan Flexbox & Grid") {
                block {
                    headerText(4) { "Flexbox" }
                    ul {
                        linkText(link = "https://www.youtube.com/watch?v=5F_ngjHDcJQ") {
                            "Flexbox, et le CSS redevient fun ! (Hubert SABLONNIÈRE)"
                        }
                        linkText(link = "https://philipwalton.github.io/solved-by-flexbox/") {
                            "Solved by Flexbox"
                        }
                        linkText(link = "Flexbox Froggy") {
                            "https://flexboxfroggy.com/"
                        }
                    }
                }
                block {
                    headerText(4) { "Grid" }
                    ul {
                        link(link = "https://developer.mozilla.org/fr/docs/Web/CSS/@supports") {
                            code { "@supports" }
                        }
                        linkText(link = "https://gridbyexample.com/") {
                            "Grid by examples"
                        }
                        linkText(link = "https://www.youtube.com/watch?v=txZq7Laz7_4") {
                            "CSS Grid Changes Everything (About Web Layouts) by Morten Rand-Hendriksen"
                        }
                        linkText(link = "http://cssgridgarden.com/") {
                            "Grid Garden"
                        }
                    }
                }
            }
            slide(title = "Compatibilité", key = "compat-3") {
                cssCompatibility(country = country,
                                 threshold = browsersThreshold,
                                 features = listOf("flexbox", "css-grid", "css-featurequeries"))
            }
        }
        // Pseudo Elt
        part(title = "Pseudo éléments") {
            slide(title = "Le dîner d'un philosophe", styleClass = setOf("live-code")) {
                codeFromResource("/cssIsAwesome/pseudo_elements/philosophe-table.html")
                cssLiveCode("/cssIsAwesome/pseudo_elements/philosophe")
            }
            slide(title = "Triangle avec des bordures", styleClass = setOf("live-code")) {
                cssLiveCode("/cssIsAwesome/pseudo_elements/border")
            }
            slide(title = "Info-bulle", styleClass = setOf("live-code")) {
                cssLiveCode("/cssIsAwesome/pseudo_elements/popover")
            }
            slide(title = "Bilan pseudo éléments") {
                ul {
                    linkText(link = "https://www.w3.org/TR/CSS22/generate.html#before-after-content") {
                        "The :before and :after pseudo-elements"
                    }
                    html {
                        "mais aussi <code>::first-letter</code>, <code>::first-line</code>, <code>::selection</code>, <code>::backdrop</code>"
                    }
                    linkText(link = "https://www.smashingmagazine.com/2016/05/an-ultimate-guide-to-css-pseudo-classes-and-pseudo-elements") {
                        "An Ultimate Guide To CSS Pseudo-Classes And Pseudo-Elements"
                    }
                }
                pText {
                    "️<code>::before</code> et <code>::after</code> ne marchent pas sur <code>input</code>, <code>img</code>, <code>iframe</code> (pas encore spécifié)"
                }
                ul {
                    block {
                        html { "Table et assiette de " }
                        linkText(link = "https://flukeout.github.io/") { "CSS Diner" }
                    }
                    linkText(link = "https://fr.wikipedia.org/wiki/D%C3%AEner_des_philosophes") { "Dîner des philosophes" }
                }
            }
            slide(title = "Compatibilité", key = "compat-4") {
                cssCompatibility(country = country,
                                 threshold = browsersThreshold,
                                 features = listOf("css-gencontent", "transforms2d"))
            }
        }
        // Animation
        part(title = "Animations") {
            slide(title = "Texte de chargement", styleClass = setOf("live-code")) {
                cssLiveCode("/cssIsAwesome/animations/loader")
            }
            slide(title = "Dessiner", styleClass = setOf("live-code")) {
                cssLiveCode("/cssIsAwesome/animations/draw")
            }
            slide(title = "Bilan animations") {
                ul {
                    linkText(link = "https://developer.mozilla.org/fr/docs/Web/CSS/Animations_CSS/Utiliser_les_animations_CSS") {
                        "Utiliser les animations CSS"
                    }
                    linkText(link = "http://tawian.io/text-spinners/") {
                        "Text spinners"
                    }
                    linkText(link = "https://www.pexels.com/blog/css-only-loaders/") {
                        "CSS only loaders"
                    }
                    linkText(link = "https://daneden.github.io/animate.css/") {
                        "Animate.css"
                    }
                    linkText(link = "https://css-tricks.com/svg-line-animation-works/") {
                        "How SVG Line Animation Works"
                    }
                    linkText(link = "https://jakearchibald.com/2013/animated-line-drawing-svg/") {
                        "Animated line drawing in SVG"
                    }
                    linkText(link = "https://csstriggers.com/") {
                        "CSS triggers"
                    }
                }
            }
            slide(title = "Compatibilité", key = "compat-5") {
                cssCompatibility(country = country,
                                 threshold = browsersThreshold,
                                 features = listOf("css-animation", "svg"))
            }
        }
        // Pseudo state
        part(title = "Pseudo classes d'état") {
            slideFromResource(title = "Usage des info-bulles", contentType = HTML)
            slide(title = "Pseudo états", styleClass = setOf("two-columns")) {
                ul {
                    code { ":hover" }
                    code { ":focus" }
                    code { ":visited" }
                    code { ":checked" }
                    code { ":valid" }
                    code { ":invalid" }
                    code { ":empty" }
                    code { ":active" }
                    code { ":target" }
                    html { "..." }
                }
            }
            slide(title = "Checkbox Hack", styleClass = setOf("live-code")) {
                cssLiveCode("/cssIsAwesome/pseudo_classes/checkbox")
            }
            slide(title = "Switch") {
                val initialCss = "/cssIsAwesome/pseudo_classes/switch-1"
                val initialCss1 = "/cssIsAwesome/pseudo_classes/switch-2"
                styleEditable(initialCss, initialCss)
                codeFromResource(initialCss + ".css")
                styleEditable(initialCss1, initialCss1)
                codeFromResource(initialCss1 + ".css")
                editableZone("/cssIsAwesome/pseudo_classes/switch.html")
            }
            slide(title = "Panel", styleClass = setOf("live-code")) {
                linkText(link = "https://snook.ca/archives/html_and_css/hiding-content-for-accessibility") {
                    "Hiding Content for Accessibility"
                }
                cssLiveCode("/cssIsAwesome/pseudo_classes/panel")
            }
            slide(title = "Principe pour les onglets") {
                codeFromResource("/cssIsAwesome/pseudo_classes/tab.html")
            }
            slide(title = "Démo des onglets") {
                editableZone("/cssIsAwesome/pseudo_classes/tab.html")
            }
            slide(title = "Compatibilité", key = "compat-6") {
                cssCompatibility(country = country,
                                 threshold = browsersThreshold,
                                 features = listOf("css-sel3", "form-validation", "transforms3d"))
            }
        }
        // HTML
        part(title = "HTML") {
            slide("Progress") {
                linkText(link = "https://css-tricks.com/html5-progress-element/") {
                    "The HTML5 progress Element"
                }
                val progress = "/cssIsAwesome/HTML/progress.html"
                codeFromResource(progress)
                editableZone(progress)
            }
            slide(title = "Panel", key = "panel_html") {
                codeFromResource("/cssIsAwesome/HTML/panel.html")
                cssLiveCode("/cssIsAwesome/HTML/panel")
            }
            slide(title = "Dialog", styleClass = setOf("live-code")) {
                cssLiveCode("/cssIsAwesome/HTML/dialog")
            }
            slide(title = "Polyfill") {
                ul {
                    linkText(link = "https://github.com/chemerisuk/better-details-polyfill/") {
                        "Better details polyfill"
                    }
                    linkText(link = "https://github.com/GoogleChrome/dialog-polyfill") {
                        "Dialog Polyfill"
                    }
                }
            }
            slide(title = "Compatibilité", key = "compat-7") {
                cssCompatibility(country = country,
                                 threshold = browsersThreshold,
                                 features = listOf("progress", "details", "dialog"))
            }
        }
        // Conclusion
        part(title = "Conclusion") {
            slide(title = "Bilan") {
                ol {
                    html { "Utilisez du CSS pour simpifier le code" }
                    html { "Utilisez intelligemment les pre/post&#8209;processeurs" }
                    html { "HTML, SVG are Awesome !" }
                    html { "JavaScript, TypeScript can be Awesome !" }
                }
            }
            slide(title = "Traitez le CSS comme du code", styleClass = setOf("hide-title")) {
                headerText(3) { "👉 Traitez le CSS comme du code" }
                ol {
                    html { "Revue de code" }
                    html { "DRY" }
                    html { "Clean Code" }
                    html { "Single Responsibility Principle" }
                    html { "..." }
                }
            }
            slide("Liens") {
                ul {
                    linkText(link = "https://ilaborie.github.io/slides/devfest-tls.html#cssIsAwesome") {
                        "les slides en HTML"
                    }
                    linkText(link = "https://ilaborie.github.io/slides/devfest-tls.pdf") {
                        "les slides en PDF"
                    }
                    linkText(link = "https://github.com/ilaborie/slides") {
                        "le code"
                    }
                    linkText(link = "http://www.monkeypatch.io/2017/05/02/MakingOf_CSS_is_Awesome.html") {
                        "Blog: 'Making Of'"
                    }
                }
            }
            slide(title = "Pour apprendre") {
                ul {
                    code { "(Ctrl|⌘) + Shift + i" }
                    linkText(link = "https://www.amazon.fr/CSS-Secrets-Lea-Verou/dp/1449372635") {
                        "CSS Secrets by Lea Verou"
                    }
                    block {
                        linkText(link = "https://developer.mozilla.org/fr/docs/Web/CSS") { "CSS sur MDN" }
                        html { ", " }
                        linkText(link = "http://a11yproject.com/") { "The A11Y Project" }
                    }
                    block {
                        linkText(link = "https://codepen.io/") { "CodePen" }
                        html { ", " }
                        linkText(link = "https://jsfiddle.net/") { "JSFiddle" }
                        html { ", " }
                        linkText(link = "http://dabblet.com/") { "Dabblet" }
                        html { ", ..." }
                    }
                    block {
                        linkText(link = "https://css-tricks.com/") { "CSS Tricks" }
                        html { ", " }
                        linkText(link = "https://www.smashingmagazine.com/") { "Smashing Magazine" }
                        html { ", ..." }
                    }
                    block {

                        linkText(link = "https://pixelastic.github.io/css-flags/") { "CSS Flags" }
                        html { ", " }
                        linkText(link = "http://a.singlediv.com/") { "A Single Div" }
                        html { ", ..." }
                    }
                }
            }
            slide(title = "🦄 rocks !", styleClass = setOf("hide-title")) {
                codeFromResource("/cssIsAwesome/conclusion/end.html")
            }
        }
    }
