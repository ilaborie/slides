package jvm_functional

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
import org.ilaborie.slides.dsl.step
import org.ilaborie.slides.dsl.ul
import org.ilaborie.slides.generateMissingExternals
import org.ilaborie.slides.hasMissingExternals
import java.io.File


fun main(args: Array<String>) {
    val logger = KotlinLogging.logger("JS Functional")

    val jvmFunctional = jvmFunctional()
    val slidesDir = File("src/main/slides-resources/")

    if (jvmFunctional.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        jvmFunctional.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web/")
    jvmFunctional.buildAll(dist, "bbl", exportPdf = false)
}

fun jvmFunctional() =
    presentation(title = HtmlContent("""Programmation fonctionnelle sur la JVM :
        |<div><span class="shake">🦄</span> ou <span class="shake">💩</span>?</div>""".trimMargin()),
                 key = "jvmFunctional") {
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
        part("Partie I", key = "part1") {
            slide(title = "Functions en Java") {
                codeFromResource("/jvmFunctional/code/functions.java")
            }
            slide(title = "Functions en Scala, Kotlin") {
                codeFromResource("/jvmFunctional/code/functions.scala")
                codeFromResource("/jvmFunctional/code/functions.kt")
            }
            slide(title = "Effets de bord") {
                codeFromResource("/jvmFunctional/code/side-effect.java")
                ul {
                    html { "⚠️ Évitez les fonctions avec effet de bord !" }
                    html { "C'est un nid à bugs." }
                    html { "<code>=></code> Évitez les fonctions qui retournent <code>void</code>, ou qui n'ont pas de paramètres." }
                }
            }
            slide(title = "Pas d'effet de bord") {
                codeFromResource("/jvmFunctional/code/no-side-effect.java")
                codeFromResource("/jvmFunctional/code/no-side-effect.scala")
                codeFromResource("/jvmFunctional/code/no-side-effect.kt")
            }
            slide(title = "Instruction vs Expression") {
                codeFromResource("/jvmFunctional/code/if-then-else.java")
            }
            slide(title = "Expressions") {
                codeFromResource("/jvmFunctional/code/if-then-else-2.java")
                codeFromResource("/jvmFunctional/code/if-then-else-2.scala")
                codeFromResource("/jvmFunctional/code/if-then-else-2.kt")
            }
            slide(title = "Mess with state") {
                quote(author = "Alan Key, The Early History Of Smalltalk",
                      cite = "http://worrydream.com/EarlyHistoryOfSmalltalk/") {
                    html { " The last thing you wanted any programmer to do is mess with internal state even if presented figuratively. Instead, the objects should be presented as <em>sites of higher level behaviors more appropriate for use as dynamic components</em>." }
                }
            }
            slide(title = "Java Mutable") {
                codeFromResource("/jvmFunctional/code/point.java")
            }
            slide(title = "Java Immutable") {
                codeFromResource("/jvmFunctional/code/point-2.java")
            }
            slide(title = "Scala, Kotlin Immutable") {
                codeFromResource("/jvmFunctional/code/point-2.scala")
                codeFromResource("/jvmFunctional/code/point-2.kt")
            }
            slide(title = "Immutable", key = "immutable_2") {
                p {
                    html { "Comment fait-on avec les structures de données ?" }
                }
                codeFromResource("/jvmFunctional/code/immutable-api.java")
                p {
                    html { "On peut utiliser " }
                    linkText("http://www.eclipse.org/collections/") { "Eclipse Collections" }
                    html { ", " }
                    linkText("http://www.vavr.io") { "Vavr" }
                    html { ", ..." }
                }
            }
            slide(title = "Java High Order function") {
                codeFromResource("/jvmFunctional/code/hoc-1.java")
                codeFromResource("/jvmFunctional/code/hoc-2.java")
            }
            slide(title = "Scala, Kotlin High Order function") {
                codeFromResource("/jvmFunctional/code/hoc.scala")
                codeFromResource("/jvmFunctional/code/hoc.kt")
            }
            slide(title = "Java for : 🤮 1/2") {
                codeFromResource("/jvmFunctional/code/for-1.java")
            }
            slide(title = "Java for : 🤢 2/2") {
                codeFromResource("/jvmFunctional/code/for-2.java")
            }
            slide(title = "Java Stream") {
                codeFromResource("/jvmFunctional/code/for-3.java")
            }
            slide(title = "Scala for : 🤔") {
                codeFromResource("/jvmFunctional/code/for.scala")
            }
            slide(title = "Kotlin for : 🤢") {
                codeFromResource("/jvmFunctional/code/for.kt")
            }
            slide(title = "Java Recursion 1/2", key = "recursion", styleClass = setOf("steps")) {
                step { codeFromResource("/jvmFunctional/code/factorial-for.java") }
                step { codeFromResource("/jvmFunctional/code/factorial-rec.java") }
            }
            slide(title = "Java Recursion 2/2", key = "recursion2", styleClass = setOf("steps")) {
                step { codeFromResource("/jvmFunctional/code/factorial-tailrec.java") }
                step { codeFromResource("/jvmFunctional/code/factorial-stream.java") }
            }
            slide(title = "Scala, Kotlin Tail Recursion") {
                codeFromResource("/jvmFunctional/code/factorial-tailrec.scala")
                codeFromResource("/jvmFunctional/code/factorial-tailrec.kt")
            }
            slide("Java Lisibilité", styleClass = setOf("steps")) {
                step { codeFromResource("/jvmFunctional/code/read-1.java") }
                step { codeFromResource("/jvmFunctional/code/read-2.java") }
                step { codeFromResource("/jvmFunctional/code/read-3.java") }
                step { codeFromResource("/jvmFunctional/code/read-4.java") }
            }
            slideFromResource(title = "Java le bilan", key = "part1_bilan_1")
            slideFromResource(title = "Scala, Kotlin le bilan", key = "part1_bilan_2")
        }
        part("Partie II", key = "part2") {
            slideFromResource(title = "Ce qu'on a appris", key = "jargon_1")
            slideFromResource(title = "Ce qu'on va voir maintenant", key = "jargon_2")
            slideFromResource(title = "Curryfication", key = "currification_1")
            slide(title = "Java Curryfication", key = "currification_2") {
                codeFromResource("/jvmFunctional/code/curry.java")
                p{
                    linkText("http://www.vavr.io/vavr-docs/#_currying") {"Vavr Currying"}
                }
            }
            slide(title = "Javascript Memoïsation", key = "memoisation",
                  styleClass = setOf("full-screen")) {
                codeEditorFromResources("Memoïsation",
                                        "/jvmFunctional/code/07-fibo.ts",
                                        "/jvmFunctional/code/07-fibo-final.ts")
            }
            slide(title = "Java Memoïsation", key = "memoisation_2") {
                codeFromResource("/jvmFunctional/code/memo.java")
            }
            slideFromResource(title = "Memoïsation", key = "memoisation_3")
            slide(title = "Algebraic Data Type", key = "adt") {
                codeFromResource("/jvmFunctional/code/adt.re")
                ul {
                    html { "😞 on peut utiliser des <em>abstract class</em> ou des <em>enum</em> ne sont pas des vraiment des ADT." }
                    linkText("https://github.com/derive4j/derive4j") { "Derive4J" }
                }

            }
            slide(title = "Pattern Matching", styleClass = setOf("steps")) {
                codeFromResource("/jvmFunctional/code/pattern-matching.re")
            }
            slideFromResource(title = "Pattern matching en Java", key = "Pattern_matching_java")
            slide(title = "Pattern matching en Scala, Kotlin", key = "Pattern_matching_kt") {
                codeFromResource("/jvmFunctional/code/pattern-matching.scala")
                codeFromResource("/jvmFunctional/code/pattern-matching.kt")
            }
//
//            slide(title = "Déconstruction") {
//                codeFromResource("/jvmFunctional/code/deconstruction.js")
//            }

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
                    codeFromResource("/jvmFunctional/code/functor.java")
                }
                step {
                    codeFromResource("/jvmFunctional/code/endofunctor.java")
                }
            }
            slide(title = "Monoïd", styleClass = setOf("steps")) {
                step {
                    quote(author = "🤣", cite = "https://fr.wikipedia.org/wiki/Mono%C3%AFde") {
                        html { "C'est un magma associatif et unifère, c'est-à-dire un demi-groupe unifère." }
                    }
                }
                step {
                    codeFromResource("/jvmFunctional/code/semigroup.java")
                }
                step {
                    codeFromResource("/jvmFunctional/code/monoid.java")
                }
            }
            slide(title = "Monade") {
                codeFromResource("/jvmFunctional/code/monad.java")
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
                        html { "qui doivent respectées des règles (axioms)" }
                        html { "ce qui garenti une haute composabilité." }
                        html { "<code>Option&lt;V&gt;</code>, <code>Either&lt;A,B&gt;</code>, <code>Try&lt;S,E&gt;</code>, <code>Future&lt;V&gt;</code>, ..." }
                    }
                }
            }
//            slideFromResource(title = "Monades en JS")
            slideFromResource(title = "🧠 High Order Kinds", key = "high_order_kinds")
//            slideFromResource(title = "Partie II - bilan")
        }
        part("Remaques sur la performance", key = "perfo") {
            slideFromResource(title = "Quoi ?", key = "what")
            slideFromResource(title = "Règles", key = "rules")
            slideFromResource(title = "Si besoin...", key = "whenmatter")
        }
        part("Conclusion") {
            slideFromResource(title = "🦄 ou 💩 ?", key = "_or___")
            slideFromResource(title = "Future et bibliothèques", key = "bibliotheque")
            slideFromResource(title = "Alternatives", key = "alternatives")
            slideFromResource(title = "Codons en fonctionel", key = "why_functional")
//            slideFromResource(title = "Valeur", key = "values")
            slide("Questions", styleClass = setOf("hide-title")) {
                header(3) {
                    html { "Questions ?" }
                }
            }
        }
    }
