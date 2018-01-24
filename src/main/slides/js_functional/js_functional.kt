package js_functional

import org.ilaborie.logger.Logger
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.ContentType.MARKDOWN
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.dsl.*
import org.ilaborie.slides.generateMissingExternals
import org.ilaborie.slides.hasMissingExternals
import java.io.File


fun main(args: Array<String>) {
    val logger = Logger("JS Functional")

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
    presentation(title = "Programmation fonctionnelle en JavaScript : 🦄 ou 💩 ?", key = "jsFunctional") {
        addScript("../scripts/navigation.js")

        part(title = "Introduction") {
            skipHeader = true
            slideFromResource(title = "Speaker", contentType = HTML)
            slideFromResource(title = "Knowledge", contentType = MARKDOWN)
            roadmap()
        }
        part(title = "Langages fonctionnels") {
            slideFromResource(title = "WarmUp")
            slideFromResource(title = "Fun")
            slideFromResource(title = "Flavour")
            slideFromResource(title = "Only one")
            slideFromResource(title = "Rim")
            slideFromResource(title = "Static vs Dynamic")
        }
        part("Programmation fonctionnelle en JavaScript - Part I") {
            slideFromResource(title = "Function")
            slideFromResource(title = "Pure Function")
            slideFromResource(title = "Immutable")
            slideFromResource(title = "Composition")
            slideFromResource(title = "Recursion")
            slideFromResource(title = "Lisibilité")
            slideFromResource(title = "Part I - bilan")
        }
        part("Entract") {
            slideFromResource(title = "PandaRoux", contentType = HTML)
        }
        part("Programmation fonctionnelle en JavaScript - Part II") {
            slideFromResource(title = "Jargon")
            slideFromResource(title = "Currification")
            slideFromResource(title = "Memoïsation")
            slideFromResource(title = "ADT")
            slideFromResource(title = "Pattern Matching")
            slideFromResource(title = "Monads")
            slideFromResource(title = "Part II - bilan")
        }
        part("Remaque sur la performance") {
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