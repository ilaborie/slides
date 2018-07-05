package js_functional

import jvm_functional.jvmFunctional
import mu.KotlinLogging
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.content.HtmlContent
import org.ilaborie.slides.dsl.codeEditorFromResources
import org.ilaborie.slides.dsl.codeFromResource
import org.ilaborie.slides.dsl.header
import org.ilaborie.slides.dsl.html
import org.ilaborie.slides.dsl.img
import org.ilaborie.slides.dsl.link
import org.ilaborie.slides.dsl.linkText
import org.ilaborie.slides.dsl.p
import org.ilaborie.slides.dsl.part
import org.ilaborie.slides.dsl.presentation
import org.ilaborie.slides.dsl.quote
import org.ilaborie.slides.dsl.roadmap
import org.ilaborie.slides.dsl.slide
import org.ilaborie.slides.dsl.slideFromResource
import org.ilaborie.slides.dsl.step
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
    jsFunctional.buildAll(dist, "gdg", exportPdf = false)
}

fun jsFunctional() =
    presentation(title = HtmlContent("""Programmation fonctionnelle en JavaScript :
        |<div><span class="shake">🦄</span> ou <span class="shake">💩</span>?</div>""".trimMargin()),
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
                ul {
                    html { "⚠️ Évitez les fonctions avec effet de bord !" }
                    html { "C'est un nid à bugs." }
                    html { "<code>=></code> Évitez les fonctions qui retournent <code>void</code>, ou qui n'ont pas de paramètres." }
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
                quote(author = "Alan Kay, The Early History Of Smalltalk",
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
                    html { ", " }
                    linkText("http://swannodette.github.io/mori/") { "mori" }
                }
            }
            slide(title = "Higher Order Functions", key = "hoc",
                  styleClass = setOf("full-screen")) {
                codeEditorFromResources("Higher Order Functions",
                                        "/jsFunctional/code/05-hoc.ts",
                                        "/jsFunctional/code/05-hoc-final.ts")
            }
            slide(title = "for : 🤢") {
                codeFromResource("/jsFunctional/code/no-for.ts")
            }
            slide(title = "Composition", styleClass = setOf("steps")) {
                step { codeFromResource("/jsFunctional/code/composition.js") }
                step {
                    linkText("https://github.com/tc39/proposal-pipeline-operator") {
                        "Stage 1 -  pipeline operator"
                    }
                    codeFromResource("/jsFunctional/code/pipeline.js")
                }
            }
            /*
            slide(title = "Recursion 1/2", key = "recursion", styleClass = setOf("steps")) {
                step { codeFromResource("/jvmFunctional/code/factorial-for.js") }
                step { codeFromResource("/jvmFunctional/code/factorial-rec.js") }
                step { codeFromResource("/jvmFunctional/code/factorial-tailrec.js") }
            }
            slide(title = "Recursion 2/2", key = "recursion-2", styleClass = setOf("steps")) {
                step {

                    linkText("https://jsperf.com/plop-factorial") { "Test jsperf" }
                    linkText("http://www.ecma-international.org/ecma-262/6.0/#sec-tail-position-calls") { "Tail Position Calls" }
                    linkText("https://kangax.github.io/compat-table/es6/") { "Compatibilité" }
                }
                step {
                    quote(author = "Erik Meijer, \"Functional Programming with Bananas, Lenses, Envelopes and Barbed Wire\", 1991",
                          cite = "https://maartenfokkinga.github.io/utwente/mmf91m.pdf") {
                        html { " Recursion is the GOTO of functional programming" }
                    }
                }
            }
            */
            slide("Lisibilité", styleClass = setOf("steps")) {
                step { codeFromResource("/jsFunctional/code/read-1.js") }
                step { codeFromResource("/jsFunctional/code/read-2.js") }
                step { codeFromResource("/jsFunctional/code/read-3.js") }
                step { codeFromResource("/jsFunctional/code/read-4.js") }
            }
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
            slide(title = "Memoïsation", key = "memoisation",
                  styleClass = setOf("full-screen")) {
                codeEditorFromResources("Memoïsation",
                                        "/jsFunctional/code/07-fibo.ts",
                                        "/jsFunctional/code/07-fibo-final.ts")
            }
            slideFromResource(title = "Memoïsation", key = "memoisation_2")
            slide(title = "Algebraic Data Type", key = "adt") {
                codeFromResource("/jsFunctional/code/adt.re")
                p { html { "😞 les <em>enum</em> ou les <em>type union</em> de TypeScript ne sont pas des ADT." } }
                linkText("https://github.com/natefaubion/adt.js") { "adt.js" }
            }
            slide(title = "Pattern Matching", styleClass = setOf("steps")) {
                step {
                    codeFromResource("/jsFunctional/code/pattern-matching.re")
                }
                step {
                    codeFromResource("/jsFunctional/code/pattern-matching.js")
                    p {
                        html {
                            """🔮 <a href="https://github.com/tc39/proposal-pattern-matching">Stage 0 - ECMAScript Pattern Matching</a>"""
                        }
                    }
                }
            }
            slide(title = "Déconstruction") {
                codeFromResource("/jsFunctional/code/deconstruction.js")
            }
            slide(title = "M-word", key = "monad-def", styleClass = setOf("steps")) {
                step {
                    quote(author = "🤡") {
                        html { "A monad is just a monoïd in the category of endofunctors, what's the problem?" }
                    }
                }
            }
            slide(title = "Functor", styleClass = setOf("steps")) {
                step {
                    quote(author = "😂", cite = "https://fr.wikipedia.org/wiki/Foncteur") {
                        html {
                            "Généralisation aux catégories de la notion de morphisme."
                        }
                    }
                }
                step {
                    codeFromResource("/jsFunctional/code/functor.ts")
                    link("https://github.com/fantasyland/fantasy-land#functor") {
                        html { "Fantasy Land <code>Functor</code>" }
                    }
                }
                step {
                    codeFromResource("/jsFunctional/code/endofunctor.ts")
                }
            }
            slide(title = "Monoïd", styleClass = setOf("steps")) {
                step {
                    quote(author = "🤣", cite = "https://fr.wikipedia.org/wiki/Mono%C3%AFde") {
                        html { "C'est un magma associatif et unifère, c'est-à-dire un demi-groupe unifère." }
                    }
                }
                step {
                    codeFromResource("/jsFunctional/code/semigroup.ts")
                }
                step {
                    codeFromResource("/jsFunctional/code/monoid.ts")
                    p {
                        link("https://github.com/fantasyland/fantasy-land#monoid") {
                            html { "Fantasy Land <code>Monoid</code>" }
                        }
                        html { ", " }
                        link("https://github.com/fantasyland/fantasy-land#semigroup") {
                            html { "Fantasy Land <code>Semigroup</code>" }
                        }
                    }
                }
            }
            slide(title = "Monade") {
                codeFromResource("/jsFunctional/code/monad.ts")
                linkText(link = "https://github.com/tc39/proposal-flatMap") {
                    "Stage 3 - <code>Array.prototype.{flatMap,flatten}</code>"
                }
                link("https://github.com/fantasyland/fantasy-land#monad") {
                    html { "Fantasy Land <code>Monad</code>" }
                }
                img("", "/jsFunctional/part2/fantasy-lang.png")
            }
            slide(title = "Monade pour les humains", key = "monad_2", styleClass = setOf("steps")) {
                step {
                    quote(author = "\uD83D\uDE33, \uD83E\uDD2F") {
                        html { "J'ai toujours pas compris !" }
                    }
                }
                step {
                    ul {
                        html { "C'est un <del>objet</del> \uD83C\uDF2F" }
                        html { "qui a des méthodes simples comme par exemple <code>map</code> ou <code>flatMap</code>" }
                        html { "qui doit respecter des règles (axioms)" }
                        html { "ce qui garantit une haute composabilité." }
                        html { "<code>Option&lt;V&gt;</code>, <code>Either&lt;A,B&gt;</code>, <code>Result&lt;S,E&gt;</code>, <code>Future&lt;V&gt;</code>, ..." }
                    }
                }
            }
            slideFromResource(title = "Monades en JS")
//            slideFromResource(title = "🧠 Higher Order Kinds", key = "high_order_kinds")
            slideFromResource(title = "Part II - bilan")
        }
        part("Remarques sur la performance", key = "perfo") {
            slideFromResource(title = "Quoi ?", key = "what")
            slideFromResource(title = "Règles", key = "rules")
            slideFromResource(title = "Si besoin...", key = "whenmatter")
        }
        part("Conclusion") {
            slideFromResource(title = "🦄 ou 💩 ?", key = "_or___")
            slideFromResource(title = "Bibliothèques", key = "bibliotheque")
            slideFromResource(title = "Alternatives", key = "alternatives")
//            slideFromResource(title = "Quote") { styleClass += "hide-title" }
            slideFromResource(title = "Intéret du JS", key = "value_of_js")
            slideFromResource(title = "Codons en fonctionel", key = "why_functional")
            slideFromResource(title = "Valeur", key = "values")
            slideFromResource(title = "Liens")
            slide("Questions", styleClass = setOf("hide-title")) {
                header(3) {
                    html { "Questions ?" }
                }
            }
        }
        part("Quizz") {
            slide(title = "Question 1", key = "q1", styleClass = setOf("quizz", "steps")) {
                step {
                    html {
                        """
                            <div><strong>Quel est le prénom de Haskell Curry ? </strong></div>
                            <div>A. Haskell</div>
                            <div>C. Brooks</div>
                            <div>B. Curry</div>
                            <div>D. Obi-Wan</div>
                        """.trimIndent()
                    }
                }
                step {
                    html {
                        "Réponse <strong>A</strong>"
                    }
                }
            }
            slide(title = "Question 2", key = "q2", styleClass = setOf("quizz", "steps")) {
                step {
                    html {
                        """
                            <div><strong>Combien de doigts a un paresseux à deux doigts ?</strong></div>
                            <div>A. deux</div>
                            <div>B. quarante-deux</div>
                            <div>C. dix</div>
                            <div>D. huit</div>
                        """.trimIndent()
                    }
                }
                step {
                    html {
                        "Réponse <strong>C</strong>"
                    }
                }
            }
            slide(title = "Question 3", key = "q3", styleClass = setOf("quizz", "steps")) {
                step {
                    html {
                        """
                            <div><strong>Qui a dit "La récursivité c'est le GOTO de la programation fonctionelle" ?</strong></div>
                            <div>A. Moses Schönfinkel</div>
                            <div>B. Erik Meijer</div>
                            <div>C. Edsger Dijkstra</div>
                            <div>D. Donald Duck</div>
                        """.trimIndent()
                    }
                }
                step {
                    html {
                        "Réponse <strong>B</strong>"
                    }
                }
            }
            slide(title = "Question 4", key = "q4", styleClass = setOf("quizz", "steps")) {
                step {
                    html {
                        """
                            <div><strong>La fonction <code>() => 42</code></strong></div>
                            <div>A. a un effet de bord</div>
                            <div>B. est idempotente</div>
                            <div>C. n'est pas currifiable</div>
                            <div>D. est une fonction d'ordre supérieur</div>
                        """.trimIndent()
                    }
                }
                step {
                    html {
                        "Réponse <strong>B</strong>"
                    }
                }
            }
            slide(title = "Question 5", key = "q5", styleClass = setOf("quizz", "steps")) {
                step {
                    html {
                        """
                            <div><strong>Avec la méthode <code>reduce</code>, je peux faire facilement?</strong></div>
                            <div>A. un équivalent de <code>flatMap</code></div>
                            <div>B. le min (ou max) d'un tableau</div>
                            <div>C. le café</div>
                            <div>D. toutes les réponses sont bonnes</div>
                        """.trimIndent()
                    }
                }
                step {
                    html {
                        "Réponse <strong>D</strong>"
                    }
                }
            }
        }
    }
