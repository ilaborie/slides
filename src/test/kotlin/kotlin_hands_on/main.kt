package kotlin_hands_on

import org.ilaborie.logger.Logger
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.Presentation
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.content.*
import org.ilaborie.slides.content.Language.Kotlin
import org.ilaborie.slides.generateMissingExternals
import org.ilaborie.slides.hasMissingExternals
import java.io.File


fun main(args: Array<String>) {
    val logger = Logger("Kotlin Hands on")

    val kotlinHandsOn = Presentation(title = "Kotlin par l'exemple",
                                     id = "kotlinHandsOn",
                                     scripts = listOf(
                                             "../scripts/navigation.js",
                                             "../scripts/water-pouring.js"))
        .group("Introduction", skipPart = true) {
            slide("Speakers", contentType = HTML, styleClass = setOf("hide-title"))
                .slide("Roadmap", contentType = HTML)
                .slide("Pré-requis", contentType = HTML)
                .slide("Pourquoi un nouveau langage ?")
                .slide("Caractéristiques de Kotlin")
                .slide("Cible", contentType = HTML)
        }
        .group("Water Pouring Problem") {
            slide("Théière magique") {
                ExternalImageContent("Théière magique", ExternalResource("/kotlinHandsOn/01_water_pouring_problem/teapot.png"))
            }
                .slide("Verres", contentType = HTML, styleClass = setOf("operation"))
                .slide("Remplir", contentType = HTML, styleClass = setOf("operation"))
                .slide("Vider", contentType = HTML, styleClass = setOf("operation"))
                .slide("Verser", contentType = HTML, styleClass = setOf("operation"))
                .slide("Démo", contentType = HTML)
        }
        .group("Live Code") {
            slide("Hello World") {
                ExternalCodeContent(Kotlin, ExternalResource("/kotlinHandsOn/hello-world.kt")) +
                        Notice(NoticeKind.Tips,
                               UnorderedList(
                                       "Utilisez <kbd>Alt</kbd> + <kbd>Shift</kbd> + <kbd>(Cmd|Ctrl)</kbd> + <kbd>K</kbd> pour convertir une classe Java en Kotlin".html(),
                                       "Ou copiez du code Java dans un fichier Kotlin".html()
                               )
                        )
            }
                .slide("Glass") {
                    ExternalCodeContent(Kotlin, ExternalResource("/kotlinHandsOn/glass.kt"))
                }
                .slide("Moves") {
                    ExternalCodeContent(Kotlin, ExternalResource("/kotlinHandsOn/move.kt"))
                }
        }
        .group("Excercices") {
            slide("Choisir son exercice")
                .slide("Android", styleClass = setOf("exo"))
                .slide("Serveur avec SpringBoot 2", styleClass = setOf("exo"))
                .slide("Navigateur avec KotlinJS", styleClass = setOf("exo"))
                .slide("Freestyle", styleClass = setOf("exo"))
                .slide("Liens")
        }

    val slidesDir = File("src/test/resources/")

    if (kotlinHandsOn.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        kotlinHandsOn.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web/")
    kotlinHandsOn.buildAll(dist, "devoxx-ma")
}
