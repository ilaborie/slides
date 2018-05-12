package js_functional

import mu.KotlinLogging
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.content.HtmlContent
import org.ilaborie.slides.dsl.codeEditorFromResources
import org.ilaborie.slides.dsl.codeFromResource
import org.ilaborie.slides.dsl.header
import org.ilaborie.slides.dsl.html
import org.ilaborie.slides.dsl.linkText
import org.ilaborie.slides.dsl.p
import org.ilaborie.slides.dsl.part
import org.ilaborie.slides.dsl.presentation
import org.ilaborie.slides.dsl.quote
import org.ilaborie.slides.dsl.roadmap
import org.ilaborie.slides.dsl.slide
import org.ilaborie.slides.dsl.slideFromResource
import org.ilaborie.slides.dsl.ul
import org.ilaborie.slides.generateMissingExternals
import org.ilaborie.slides.hasMissingExternals
import java.io.File


fun main(args: Array<String>) {
    val logger = KotlinLogging.logger("JS Functional")

    val jsFunctional = jsFunctional()
    val slidesDir = File("src/main/slides-resources/")

    if (jsFunctional.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        jsFunctional.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web/")
    jsFunctional.buildAll(dist, "gdg")
}

fun jsFunctional() =
    presentation(title = HtmlContent("""Programmation fonctionnelle en JavaScript :
        |<div><span class="shake">🦄</span> ou <span class="shake">💩</span></div>""".trimMargin()),
                 key = "jsFunctional") {
        addScript("../scripts/navigation.js")
        addScript("../scripts/monaco-editor/min/vs/loader.js")
        addScript("../scripts/code-editor.js")

        part(title = "Introduction", key = "intro") {
            skipHeader = true
            slideFromResource(title = "Speaker", contentType = HTML)
            slideFromResource(title = "Knowledge", contentType = HTML) {
                styleClass = setOf("hide-title", "steps")
            }
            roadmap(title = "🗺 Plan")
        }
        part(title = "Langages fonctionnels", key = "fun") {
            slideFromResource(title = "WarmUp", contentType = HTML) {
                styleClass = setOf("steps")
            }
            slideFromResource(title = "Catharsis", key = "fun", contentType = HTML) {
                styleClass = setOf("steps")
            }
            slideFromResource(title = "Paradigmes", key = "flavour", contentType = HTML) {
                styleClass = setOf("steps")
            }
            slideFromResource(title = "One to rule them all", key = "only_one", contentType = HTML) {
                styleClass = setOf("steps")
            }
            slideFromResource(title = "Fellowship", key = "rim", contentType = HTML) {
                styleClass = setOf("steps")
            }
            slideFromResource(title = "Statique vs Dynamique", key = "static_vs_dynamic", contentType = HTML) {
                styleClass = setOf("steps")
            }
        }
        part("Programmation fonctionnelle en JS - Part I", key = "part1") {
            slide(title = "Function", styleClass = setOf("full-screen")) {
                codeEditorFromResources("Functions en JavaScript",
                                        "/jsFunctional/code/01-functions.ts",
                                        "/jsFunctional/code/01-functions-final.ts")
            }
            slide(title = "Effets de bord") {
                codeFromResource("/jsFunctional/code/02-side-effect.ts")
                p {
                    html {
"""
⚠️ Évitez les fonctions avec effet de bord !<br>
C'est un nid à bugs.<br>
<code>=></code> Évitez les fonctions qui retournent <code>void</code>, ou qui n'ont pas de paramètres.
"""
                    }
                }
            }
            slide(title = "Function", key = "pure_function_2", styleClass = setOf("full-screen")) {
                codeEditorFromResources("Functions sans effet de bord",
                                        "/jsFunctional/code/02-side-effect.ts",
                                        "/jsFunctional/code/02-side-effect-final.ts")
            }
            slide(title = "Instruction vs Expression",
                  key = "statement_vs_expression",
                  styleClass = setOf("full-screen")) {
                codeEditorFromResources("Instruction vs Expression",
                                        "/jsFunctional/code/03-if-then-else.ts",
                                        "/jsFunctional/code/03-if-then-else-final.ts")
            }

            slide(title = "Mess with state") {
                quote(author = "Alan Key, The Early History Of Smalltalk",
                      cite = "http://worrydream.com/EarlyHistoryOfSmalltalk/") {
                    html { " The last thing you wanted any programmer to do is mess with internal state even if presented figuratively. Instead, the objects should be presented as <em>sites of higher level behaviors more appropriate for use as dynamic components</em>." }
                }
            }
            slide(title = "Immutable",
                  key = "immutable_1",
                  styleClass = setOf("full-screen")) {
                codeEditorFromResources("Immutable",
                                        "/jsFunctional/code/04-immutable.ts",
                                        "/jsFunctional/code/04-immutable-final.ts")
            }
            slide(title = "Immutable", key = "immutable_2") {
                p {
                    html { "Comment fait-on avec les structures de données ?" }
                }
                codeFromResource("/jsFunctional/code/immutable-api.ts")
                p {
                    html { "On peut utiliser " }
                    linkText("https://facebook.github.io/immutable-js/") { "Immutable.js" }
                }
            }
            slide(title = "High Order function", key = "hoc",
                  styleClass = setOf("full-screen")) {
                codeEditorFromResources("High Order functions",
                                        "/jsFunctional/code/05-hoc.ts",
                                        "/jsFunctional/code/05-hoc-final.ts")
            }
            slide(title = "for : 🤢") {
                codeFromResource("/jsFunctional/code/no-for.ts")
            }
            slide(title = "Composition") {
                codeFromResource("/jsFunctional/code/composition.ts")
                linkText("https://github.com/tc39/proposal-pipeline-operator") {
                    "Stage 1 -  pipeline operator"
                }
                codeFromResource("/jsFunctional/code/pipeline.ts")
            }
            slide(title = "Recursion 1/2", key = "recursion") {
                codeFromResource("/jsFunctional/code/factorial-for.ts")
                codeFromResource("/jsFunctional/code/factorial-rec.ts")
                codeFromResource("/jsFunctional/code/factorial-tailrec.ts")
            }
            slide(title = "Recursion 2/2", key = "recursion-2") {
                ul {
                    linkText("https://jsperf.com/plop-factorial") { "Test jsperf" }
                    linkText("http://www.ecma-international.org/ecma-262/6.0/#sec-tail-position-calls") { "Tail Position Calls" }
                    linkText("https://kangax.github.io/compat-table/es6/") { "Compatibilité" }
                    quote(author = "Erik Meijer, \"Functional Programming with Bananas, Lenses, Envelopes and Barbed Wire\", 1991",
                          cite = "https://maartenfokkinga.github.io/utwente/mmf91m.pdf") {
                        html { " Recursion is the GOTO of functional programming" }
                    }
                }
            }
            slideFromResource(title = "Lisibilité", contentType = HTML) {
                styleClass = setOf("steps")
            }
//            slide("Lisibilité") {
//                codeFromResource("/jsFunctional/code/read-1.ts")
//                codeFromResource("/jsFunctional/code/read-2.ts")
//                codeFromResource("/jsFunctional/code/read-3.ts")
//            }
            slideFromResource(title = "Part I - bilan 1/2", key = "part1_bilan_1")
            slideFromResource(title = "Part I - bilan 2/2", key = "part1_bilan_2")
        }
//        part("Entracte") {
//            slideFromResource(title = "PandaRoux", contentType = HTML)
//        }
        part("Programmation fonctionnelle en JS - Part II", key = "part2") {
            slideFromResource(title = "Ce qu'on a appris", key = "jargon_1")
            slideFromResource(title = "Ce qu'on va voir maintenant", key = "jargon_2")
            slide(title = "Curryfication", key = "currification_1",
                styleClass = setOf("full-screen")) {
                codeEditorFromResources("Curryfication",
                                        "/jsFunctional/code/06-curry.ts",
                                        "/jsFunctional/code/06-curry-final.ts")
            }
            slideFromResource(title = "Curryfication", key = "currification_2")
            slideFromResource(title = "Memoïsation")
            slideFromResource(title = "Algebraic Data Type", key = "adt")
            slideFromResource(title = "Pattern Matching")
//            slideFromResource(title = "Monads")
            slideFromResource(title = "High Order Kinds")
            slideFromResource(title = "Part II - bilan")
        }
        part("Remaques sur la performance", key = "perfo") {
            slideFromResource(title = "What")
            slideFromResource(title = "Rules")
            slideFromResource(title = "WhenMatter")
        }
        part("Conclusion") {
            slideFromResource(title = "🦄 ou 💩 ?", key = "_or___")
            slideFromResource(title = "Bibliothèques", key = "bibliotheque")
            slideFromResource(title = "Alternatives", key = "alternatives")
            slideFromResource(title = "Quote") { styleClass += "hide-title" }
            slideFromResource(title = "Valeur", key = "values")
            slideFromResource(title = "Intéret du JS", key = "value_of_js")
            slideFromResource(title = "Codons en fonctionel", key = "why_functional")
            slideFromResource(title = "Liens")
            slide("Questions", styleClass = setOf("hide-title")) {
                header(3) {
                    html { "Questions ?" }
                }
            }
        }
    }
