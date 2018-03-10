package js_functional

import mu.KotlinLogging
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.dsl.*
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
            slideFromResource(title = "Pure Function")
            slideFromResource(title = "Immutable")
            slideFromResource(title = "Composition")
            slideFromResource(title = "Recursion")
            slideFromResource(title = "Lisibilité")
            slideFromResource(title = "Part I - bilan")
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