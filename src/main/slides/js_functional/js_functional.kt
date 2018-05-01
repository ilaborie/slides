package js_functional

import mu.KotlinLogging
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.dsl.codeEditorFromResources
import org.ilaborie.slides.dsl.codeFromResource
import org.ilaborie.slides.dsl.header
import org.ilaborie.slides.dsl.html
import org.ilaborie.slides.dsl.p
import org.ilaborie.slides.dsl.part
import org.ilaborie.slides.dsl.presentation
import org.ilaborie.slides.dsl.quote
import org.ilaborie.slides.dsl.roadmap
import org.ilaborie.slides.dsl.slide
import org.ilaborie.slides.dsl.slideFromResource
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
    presentation(title = "Programmation fonctionnelle en JavaScript :", key = "jsFunctional") {
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
                p {
                    html { "Eviter les fonctions avec effet de bord" }
                }
                codeFromResource("/jsFunctional/code/02-side-effect.ts")
                p {
                    html {
                        "⚠️ danger, c'est un nid à bugs.<br>" +
                                "=> Éviter les fonctions qui n'ont pas de paramètres, ou retournent `void`"
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

            slide(title = "We mess with state") {
                quote(author = "Alan Key, The Early History Of Smalltalk", cite = "http://worrydream.com/EarlyHistoryOfSmalltalk/") {
                    html {" The last thing you wanted any programmer to do is mess with internal state even if presented figuratively. Instead, the objects should be presented as <em>sites of higher level behaviors more appropriate for use as dynamic components</em>."}
                }
            }
            slide(title = "Immutable",
                  key = "immutable_1",
                  styleClass = setOf("full-screen")) {
                codeEditorFromResources("Immutable",
                                        "/jsFunctional/code/04-immutable.ts",
                                        "/jsFunctional/code/04-immutable-final.ts")
            }
            slideFromResource(title = "Immutable", key = "immutable_2")
            slideFromResource(title = "Composition")
            slideFromResource(title = "Recursion")
            slideFromResource(title = "Lisibilité")
            slideFromResource(title = "Part I - bilan 1/2", key= "part1_bilan_1")
            slideFromResource(title = "Part I - bilan 2/2", key= "part1_bilan_2")
        }
        part("Entracte") {
            slideFromResource(title = "PandaRoux", contentType = HTML)
        }
        part("Programmation fonctionnelle en JS - Part II", key = "part2") {
            slideFromResource(title = "Jargon")
            slideFromResource(title = "Currification")
            slideFromResource(title = "Memoïsation")
            slideFromResource(title = "ADT")
            slideFromResource(title = "Pattern Matching")
            slideFromResource(title = "Monads")
            slideFromResource(title = "Part II - bilan")
        }
        part("Remaques sur la performance", key = "perfo") {
            slideFromResource(title = "What")
            slideFromResource(title = "Rules")
            slideFromResource(title = "WhenMatter")
        }
        part("Conclusion") {
            slideFromResource(title = "🦄 or 💩 ?")
            slideFromResource(title = "Bibliothèque")
            slideFromResource(title = "Quote") { styleClass += "hide-title" }
            slideFromResource(title = "Values")
            slideFromResource(title = "Value of JS")
            slideFromResource(title = "Why functional")
            slideFromResource(title = "Liens")
            slide("Questions", styleClass = setOf("hide-title")) {
                header(3) {
                    html { "Questions ?" }
                }
            }
        }
    }
