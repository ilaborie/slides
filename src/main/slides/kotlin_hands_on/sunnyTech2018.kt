package kotlin_hands_on

import mu.KotlinLogging
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.ContentType.MARKDOWN
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.content.NoticeKind
import org.ilaborie.slides.dsl.*
import org.ilaborie.slides.generateMissingExternals
import org.ilaborie.slides.hasMissingExternals
import java.io.File


fun main(args: Array<String>) {
    val logger = KotlinLogging.logger("Kotlin Hands on")

    val kotlinHandsOn = prezSunnyTech2018()

    val slidesDir = File("src/main/slides-resources/")

    if (kotlinHandsOn.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        kotlinHandsOn.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web/")
    kotlinHandsOn.buildAll(dist, "sunnytech", exportPdf = true)
}

fun prezSunnyTech2018() = presentation(title = "Kotlin par l'exemple", key = "kotlinHandsOn") {
    addScript("../scripts/navigation.js")
    addScript("../scripts/water-pouring.js")

    part(title = "Introduction") {
        skipHeader = true
        slideFromResource(title = "Speakers", key = "speakers-st", contentType = HTML)
        slideFromResource(title = "🗺 Roadmap", key = "roadmap-bc", contentType = HTML)
        slideFromResource(title = "Wifi", key = "wifi", contentType = HTML)
        slideFromResource(title = "Pré-requis - IDE", key = "pre_requis___ide-bc", contentType = HTML)
        slideFromResource(title = "Pré-requis - Exercices", key = "pre_requis___exercices-st",  contentType = HTML)
//        slideFromResource(title = "Pourquoi un nouveau langage ?", contentType = MARKDOWN)
//        slideFromResource(title = "Caractéristiques de Kotlin", contentType = MARKDOWN)
//        slideFromResource(title = "Cible", contentType = HTML)
    }
    part(title = "Water Pouring Problem") {
        slide(title = "Tonneau magique") {
            img(alt = "Tonneau magique", resource = "/kotlinHandsOn/water_pouring_problem/barrels.png")
        }
        slideFromResource(title = "Verres", contentType = HTML) { styleClass = setOf("operation") }
        slideFromResource(title = "Remplir", contentType = HTML) { styleClass = setOf("operation") }
        slideFromResource(title = "Verser", contentType = HTML) { styleClass = setOf("operation") }
        slideFromResource(title = "Vider", contentType = HTML) { styleClass = setOf("operation") }
        slideFromResource(title = "Démo", contentType = HTML)
    }
    part("Pour démarrer") {
        slide("Hello World") {
            codeFromResource("/kotlinHandsOn/hello-world-st.kt")
            notice(NoticeKind.Tips) {
                ul {
                    html { "Utilisez <kbd>Alt</kbd> + <kbd>Shift</kbd> + <kbd>(Cmd|Ctrl)</kbd> + <kbd>K</kbd> pour convertir une classe Java en Kotlin" }
                    html { "Ou copiez du code Java dans un fichier Kotlin" }
                }
            }
        }
        slide("Glass") {
            codeFromResource("/kotlinHandsOn/glass.kt")
            notice(NoticeKind.Info) {
                ul {
                    html { "En écrivant du Kotlin vous aurez plein de <code>fun</code> !" }
                    html { "Le <code>typealias</code> nécessite Kotlin 1.1." }
                }
            }
        }
        slide("Moves") {
            codeFromResource("/kotlinHandsOn/move.kt")
            notice(NoticeKind.Info) {
                ul {
                    html { "Avec les <code>sealed</code> et les <code>data class</code> on peut faire des <em>Abstract Data Class</em>" }
                    html { "Le <code>sealed</code> nécessite Kotlin 1.1." }
                }
            }
        }
    }
    part("Excercices") {
        slideFromResource(title = "Choisir son exercice", key="choisir_st")
        slideFromResource(title = "Basique", key = "freestyle") { styleClass = setOf("exo") }
        slideFromResource(title = "Serveur avec SpringBoot 2") { styleClass = setOf("exo") }
        slideFromResource(title = "Android") { styleClass = setOf("exo") }
        slideFromResource(title = "Navigateur avec KotlinJS") { styleClass = setOf("exo") }
        slideFromResource(title = "Liens")
    }
}
