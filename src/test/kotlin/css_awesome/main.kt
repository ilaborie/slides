package css_awesome

import org.ilaborie.slides.BasicSlide
import org.ilaborie.slides.Presentation
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.content.*
import org.ilaborie.slides.content.web.EditableZone
import org.ilaborie.slides.content.web.StyleEditable
import org.ilaborie.slides.dsl.*
import java.io.File


val browsersThreshold = 0.5

fun cssLiveCode(prefix: String) =
    StyleEditable(ExternalResource("$prefix.css"), ExternalResource("$prefix-final.css")) +
            EditableZone(ExternalHtmlContent(ExternalResource("$prefix.html")))

fun main(args: Array<String>) {

    val slidesDir = File("src/test/resources/")
    val dist = File("src/main/web")

    val title = "C<span class=\"logo-askew\">S</span>S is awesome!".html()

    // Devfest Toulouse
    val cssIsAwesome = Presentation(title = title, id = "cssIsAwesome")
        .group("Introduction", skipPart = true) { intro(this) }
        .group("Utiliser un pré&#8209;processeur ?", "preprocessor") { preprocessor("FR", this) }
        .group("Unités") { unites("FR", this) }
        .group("Flexbox et Grid") { flexgrid("FR", this) }
        .group("Pseudo éléments") { pseudoElt("FR", this) }
        .group("Animations") { animations("FR", this) }
        .group("Pseudo classes d'état", "pseudo_classes") { pseudoState("FR", this) }
        .group("HTML") { html("FR", this) }
        .group("Conclusion") { conclusion(this) }
//    cssIsAwesome
//        .buildAll(dist, "devfest-tls")

    // Devoxx Maroc
    Presentation(title = title, id = "cssIsAwesome")
        .group("Introduction", skipPart = true) {
            intro(this)
                .removeSlide("omit")
                .removeSlide("least-power")
                .replaceSlide("regles_du_jeu",
                              BasicSlide(id = "regles_du_jeu",
                                         title = "Règles du jeu",
                                         content =
                                         Link("The Rule of Least Power", "https://www.w3.org/2001/tag/doc/leastPower.html") +
                                                 listOf("Texte",
                                                        "HTML (sémantique)",
                                                        "CSS (layout, style, animations simples)",
                                                        "SVG (formes et animations complexes)",
                                                        "JavaScript, WebAssembly (gestion d'états, appel backend, calculs)").ol() +
                                                 "⚠️... mais il y a toujours de bonnes raisons pour ne pas suivre ces règles".em()
                              ))
        }
        .group("Utiliser un pré&#8209;processeur ?", "preprocessor") { preprocessor("MA", this) }
        .group("Unités") { unites("MA", this) }
        .group("Flexbox et Grid") { flexgrid("MA", this) }
        .group("Pseudo éléments") { pseudoElt("MA", this) }
        .group("Animations") {
            animations("MA", this)
                .removeSlide("texte_de_chargement")
//                .replaceSlide("texte_de_chargement",
//                              BasicSlide(id = "texte_de_chargement",
//                                         title = "Texte de chargement",
//                                         content =
//                                         StyleEditable(ExternalResource("/cssIsAwesome/05_animations/loader-final.css"), ExternalResource("/cssIsAwesome/05_animations/loader-final.css")) +
//                                                 EditableZone(ExternalHtmlContent(ExternalResource("/cssIsAwesome/05_animations/loader.html")))
//                              ))
        }
        .group("Pseudo classes d'état", "pseudo_classes") { pseudoState("MA", this) }
        .group("Conclusion") {
            conclusion(this)
                .replaceSlide("liens",
                              BasicSlide(id = "liens",
                                         title = "Liens",
                                         content = UnorderedList(
                                                 Link("les slides en HTML", "https://ilaborie.github.io/slides/devoxx-ma.html#cssIsAwesome"),
                                                 Link("les slides en PDF", "https://ilaborie.github.io/slides/devoxx-ma.pdf"),
                                                 Link("le code", "https://github.com/ilaborie/slides"),
                                                 Link("Blog: 'Making Of'", "http://www.monkeypatch.io/2017/05/02/MakingOf_CSS_is_Awesome.html"))))
        }
        .buildAll(dist, "devoxx-ma")

    copyExtraFiles(slidesDir.resolve(cssIsAwesome.id), dist.resolve(cssIsAwesome.id))
}

private fun copyExtraFiles(srcDir: File, destDir: File) {
    listOf("holy-grail.html", "holy-grail-calc.html", "holy-grail-flexbox.html", "holy-grail-grid.html")
        .map { it to srcDir.resolve(it) }
        .map { (fileName, srcDir) -> srcDir.copyTo(target = destDir.resolve(fileName), overwrite = true) }
}


fun devFest() = presentation(title = "CSS is Awesome !", key = "cssIsAwesome") {
    title = HtmlContent("C<span class=\"logo-askew\">S</span>S is awesome!")
    // Intro
    part(title = "Introduction") {
        skipHeader = true
        slide(title = "$ whoami", key = "whoami") {
            title = Code(code = "$ whoami")
            header(4) { html { "Igor Laborie" } }
            block {
                block { html { "Expert Java & Web," } }
                link(link = "http://www.monkeypatch.io/", alt = "MonkeyPatch") {
                    svg(resource = "/cssIsAwesome/00_introduction/monkeypatch.svg")
                }
            }
            link(link = "https://twitter.com/ilaborie", alt = "Twitter") { html { "@ilaborie" } }
            link(link = "mailto:igor@monkeypatch.io", alt = "Twitter") { html { "igor@monkeypatch.io" } }
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
            cssLiveCode("/cssIsAwesome/01_preprocessor/boutons")
        }
        slide(title = "Alors utilise-t-on un pré&#8209;processeurs ?") {
            block {
                p { html { "Oui, mais privilégiez:" } }
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
                    link(link = "https://www.w3.org/TR/css-variables/") {
                        html { "CSS Variables (aka Custom Properties)" }
                    }
                    link(link = "https://www.w3.org/TR/css-color-4/") {
                        html { "CSS Color Module Level 4" }
                    }
                }
            }
        }
        slide(title = "Compatibilité", key = "compat-1") {
            cssCompatibility(country = "FR",
                             threshold = browsersThreshold,
                             features = listOf("currentcolor", "background-img-opts", "css-variables"))
        }
    }
    // Units
    part(title = "Unités") {
        slide(title = "Une histoire d’unités CSS") {
            figure(title = "Une histoire d’unités CSS", link = "https://www.commitstrip.com/wp-content/uploads/2016/10/Strip-High-Level-CSS-650-final-2.jpg")
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
            htmlFromResource("/cssIsAwesome/02_unites/holy_grail-inner.html")
            codeFromResource("/cssIsAwesome/02_unites/holy_grail.html")
        }
        slide(title = "Bilan unités") {
            ul {
                link(link="https://developer.mozilla.org/fr/docs/Web/CSS/length") {
                    html { "Unités" }
                }
                link(link = "https://www.w3.org/Style/Examples/007/units.fr.html") {
                    html { "Truc et astuces" }
                }
                link(link="https://developer.mozilla.org/fr/docs/Web/CSS/calc") {
                    code { "calc" }
                }
                link(link = "https://css-tricks.com/fun-viewport-units/") {
                    html { "Fun with Viewport Units" }
                }
            }
        }
        slide(title = "Compatibilité", key = "compat-2") {
            cssCompatibility(country = "FR",
                             threshold = browsersThreshold,
                             features = listOf("rem", "viewport-units", "calc"))
        }
    }
    // Flexbox, Grid
    part(title = "Flexbox et Grid") { }
    // Pseudo Elt
    part(title = "Pseudo éléments") { }
    // Animation
    part(title = "Animations") { }
    // Pseudo state
    part(title = "Pseudo classes d'état") { }
    // HTML
    part(title = "HTML") { }
    // Conclusion
    part(title = "Conclusion") { }
}
