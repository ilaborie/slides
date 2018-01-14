package kotlin_bbl

import org.ilaborie.logger.Logger
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.ContentType.MARKDOWN
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.content.NoticeKind
import org.ilaborie.slides.dsl.*
import org.ilaborie.slides.generateMissingExternals
import org.ilaborie.slides.hasMissingExternals
import java.io.File


fun main(args: Array<String>) {
    val logger = Logger("Kotlin BBL")

    val kotlinBbl = kotlinBbl()
    val slidesDir = File("src/main/slides-resources/")

    if (kotlinBbl.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        kotlinBbl.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web/")
    kotlinBbl.buildAll(dist, "bbl")
}

fun kotlinBbl() = presentation(title = "", key = "kotlinBbl") {
    addScript("../scripts/navigation.js")
    addScript("../scripts/water-pouring.js")

    part(title = "Introduction") {
        skipHeader = true
        slideFromResource(title = "Speakers", contentType = HTML)
        slideFromResource(title = "Pourquoi un nouveau langage ?", contentType = MARKDOWN)
        slideFromResource(title = "Caractéristiques de Kotlin", contentType = MARKDOWN)
        slideFromResource(title = "Cible", contentType = HTML)
        slide("Hello World") {
            codeFromResource("/kotlinBbl/code/hello.kt")
            notice(NoticeKind.Tips) {
                ul {
                    html { "Utilisez <kbd>Alt</kbd> + <kbd>Shift</kbd> + <kbd>(Cmd|Ctrl)</kbd> + <kbd>K</kbd> pour convertir une classe Java en Kotlin<br>" }
                    html { "Ou copiez du code Java dans un fichier Kotlin" }
                }
            }
        }
    }
    part(title = "Water Pouring Problem") {
        slideFromResource(title = "Verres", contentType = HTML) { styleClass + "operation" }
        slideFromResource(title = "Remplir", contentType = HTML) { styleClass + "operation" }
        slideFromResource(title = "Vider", contentType = HTML) { styleClass + "operation" }
        slideFromResource(title = "Verser", contentType = HTML) { styleClass + "operation" }
        slideFromResource(title = "Démo", contentType = HTML)
    }
    part("Live Coding") {
        slide("Glass & State") {
            codeFromResource("/kotlinBbl/code/glass.kt")
        }
        slide("Move") {
            codeFromResource("/kotlinBbl/code/moves.kt")
        }
        slide("Solver") {
            codeFromResource("/kotlinBbl/code/solve.kt")
        }
        slide("State::move") {
            codeFromResource("/kotlinBbl/code/state-move.kt")
        }
    }
    part("Kotlin dès maintenant") {
        slideFromResource(title = "Android")
        slideFromResource(title = "Serveur")
        slideFromResource(title = "Web et Natif")
    }
    part("Conclusion") {
        slideFromResource(title = "Pourquoi Kotlin")
        slideFromResource(title = "Liens")
        slide("Question", styleClass = setOf("hide-title")) {
            header(3) { html { "Question ?" } }

        }
    }
}