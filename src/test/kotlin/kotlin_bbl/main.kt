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
    val slidesDir = File("src/test/resources/")

    if (kotlinBbl.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        kotlinBbl.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web/")
    kotlinBbl.buildAll(dist, "bbl")
}

fun kotlinBbl() = presentation(title = "Kotlin BBL", key = "kotlinBbl") {
    addScript("../scripts/navigation.js")
    addScript("../scripts/water-pouring.js")

    part(title = "Introduction") {
        skipHeader = true
        slideFromResource(title = "Speakers", contentType = HTML)
        roadmap()
        slideFromResource(title = "Pourquoi un nouveau langage ?", contentType = MARKDOWN)
        slideFromResource(title = "Caractéristiques de Kotlin", contentType = MARKDOWN)
        slideFromResource(title = "Cible", contentType = HTML)
    }
    part(title = "Water Pouring Problem") {
        slide(title = "Théière magique") {
            img(alt = "Théière magique", resource = "/kotlinHandsOn/water_pouring_problem/teapot.png")
        }
        slideFromResource(title = "Verres", contentType = HTML) { styleClass + "operation" }
        slideFromResource(title = "Remplir", contentType = HTML) { styleClass + "operation" }
        slideFromResource(title = "Vider", contentType = HTML) { styleClass + "operation" }
        slideFromResource(title = "Verser", contentType = HTML) { styleClass + "operation" }
        slideFromResource(title = "Démo", contentType = HTML)
    }
    part("Live Coding") {
        slide("Hello World") {
            notice(NoticeKind.Tips) {
                ul {
                    html { "Utilisez <kbd>Alt</kbd> + <kbd>Shift</kbd> + <kbd>(Cmd|Ctrl)</kbd> + <kbd>K</kbd> pour convertir une classe Java en Kotlin" }
                    html { "Ou copiez du code Java dans un fichier Kotlin" }
                }
            }
        }
        slide("Glass") {
            notice(NoticeKind.Info) {
                ul {
                    html { "En écrivant du Kotlin vous aurez plein de <code>fun</code> !" }
                    html { "Le <code>typealias</code> nécessite Kotlin 1.1." }
                }
            }
        }
        slide("Moves") {
            notice(NoticeKind.Info) {
                ul {
                    html { "Avec les <code>sealed</code> et les <code>data class</code> on peut faire des <em>Abstract Data Class</em>" }
                    html { "Le <code>sealed</code> nécessite Kotlin 1.1." }
                }
            }
        }
    }
    part("Android") {
    }
    part("Conclusion") {
        slideFromResource(title = "Liens")
    }
}