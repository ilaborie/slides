package kotlin_hands_on

import org.ilaborie.logger.Logger
import org.ilaborie.slides.Presentation
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.content.ExternalCodeContent
import org.ilaborie.slides.content.ExternalResource
import org.ilaborie.slides.content.Language.Java
import org.ilaborie.slides.content.Language.Kotlin
import org.ilaborie.slides.content.ul
import org.ilaborie.slides.generateMissingExternals
import org.ilaborie.slides.hasMissingExternals
import java.io.File


fun main(args: Array<String>) {
    val logger = Logger("Kotlin Hands on")

    val kotlinHandsOn = Presentation(title = "Kotlin par l'exemples", id = "kotlinHandsOn")
        .group("Introduction", skipPart = true) {
            slide("Pourquoi un nouveau langage ?") {
                listOf(
                        "Safety",
                        "Time to market"
                ).ul()
            }
                .roadMap("Roadmap")
        }
        .group("Base") {

            slide("(Java) Hello World !") {
                ExternalCodeContent(Java, ExternalResource("/kotlin_hands_on/hello-world.java"))
            }.
                slide("(Kotlin) Hello World !") {
                    ExternalCodeContent(Kotlin, ExternalResource("/kotlin_hands_on/hello-world.kt"))
                }
        }
        .group("Avancés")
        .group("Plus loin")
        .group("Tips")
        .group("Conclusion")

    val slidesDir = File("src/test/resources/")

    if (kotlinHandsOn.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        kotlinHandsOn.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web/")
    kotlinHandsOn.buildAll(dist, "devoxx-ma")
}
