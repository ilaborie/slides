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

fun jsFunctional() = presentation(title = "Programmation fonctionnelle en JavaScript : 🦄 ou 💩 ?", key = "jsFunctional") {
    addScript("../scripts/navigation.js")

    part(title = "Introduction") {
        skipHeader = true
        slideFromResource(title = "Speaker", contentType = HTML)
        slideFromResource(title = "Knowledge", contentType = MARKDOWN)
        roadmap()
    }
    part(title = "Langages fonctionnel") {
        slideFromResource(title = "WarmUp", contentType = HTML)
        slideFromResource(title = "Fun", contentType = HTML)
        slideFromResource(title = "Flavour", contentType = HTML)
        slideFromResource(title = "Only one", contentType = HTML)
        slideFromResource(title = "Rim")
    }
    part("Programmation fonctionnelle en JavaScript") {
        slideFromResource(title = "Static vs Dynamic")
        slideFromResource(title = "Function")
        slideFromResource(title = "Pure Function")
        slideFromResource(title = "Immutable")
        slideFromResource(title = "Composition")
        slideFromResource(title = "Recursion")
        slideFromResource(title = "Currification")
        slideFromResource(title = "Memoïsation")
        slideFromResource(title = "Monad")
    }
    part("Libraries") {
        slideFromResource(title = "ImmutableJS")
        slideFromResource(title = "Ramda")
        slideFromResource(title = "Ramda")
    }
    part("Note on Performance") {
        slideFromResource(title = "ImmutableJS")
        slideFromResource(title = "Ramda")
        slideFromResource(title = "Ramda")
    }
    part("Conclusion") {
        slideFromResource(title = "Quote")
        slideFromResource(title = "Values")
        slideFromResource(title = "Value of JS")
        slideFromResource(title = "Why functional")
        slideFromResource(title = "Liens")
        slide("Question", styleClass = setOf("hide-title")) {
            header(3) { html { "Question ?" } }
        }
    }
}