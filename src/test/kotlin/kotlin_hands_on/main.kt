package kotlin_hands_on

import org.ilaborie.logger.Logger
import org.ilaborie.slides.Presentation
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.content.*
import org.ilaborie.slides.content.Language.Kotlin
import org.ilaborie.slides.generateMissingExternals
import org.ilaborie.slides.hasMissingExternals
import java.io.File


fun main(args: Array<String>) {

    val logger = Logger("Kotlin Hands on")

    val kotlinHandsOn = Presentation(title = "Kotlin par l'exemple", id = "kotlinHandsOn")
        .group("Introduction", skipPart = true) {
            slide("Pré-requis") {
                "TODO".html() +
                        OrderedList(
                                "TODO Wifi".html(),
                                Code("git clone"),
                                // FIXME https://stackoverflow.com/questions/21814652/how-to-download-dependencies-in-gradle
                                Code("./gradlew assemble"))
            }
                .slide("Speakers", styleClass = setOf("hide-title")) {
                    Block("Emmanuel Vinas".h4() +
                                    "Expert Android & Java".html() +
                                    Link("@emmanuelvinas", "https://twitter.com/emmanuelvinas") +
                                    Link("emmanuel@monkeypatch.io", "mailto:emmanuel@monkeypatch.io")
                    ) +
                            Block("Igor Laborie".h4() +
                                            "Expert Java & Web".html() +
                                            Link("@ilaborie", "https://twitter.com/ilaborie") +
                                            Link("igor@monkeypatch.io", "mailto:igor@monkeypatch.io")
                            ) +
                            Link(ExternalSvgContent(ExternalResource("/cssIsAwesome/00_introduction/monkeypatch.svg")), "http://www.monkeypatch.io/")
                }
                .slide("Pourquoi un nouveau langage ?") {
                    listOf(
                            "Safety",
                            "Time to market",
                            "Ease of transcription of our ideas"
                    ).ul()
                }
                .slide("Caractéristique de Kotlin") {
                    listOf(
                            "Sûr",
                            "Abordable",
                            "Pragmatic",
                            "Statiquement typé",
                            "Inspiré par Java, Scala, C#, Groovy, ..."
                    ).ul()
                }
                .slide("Cible") {
                    listOf(
                            "la JVM et Android",
                            "JavaScript",
                            "Native avec LLVM (Expérimentale)"
                    ).ul()
                }
                .roadMap("Roadmap")
        }
        .group("Water Pouring Problem") {
            slide("Théière magique") { "TODO".html() }
                .slide("Verre") { "TODO".html() }
                .slide("Opérations") {
                    UnorderedList(
                            "Vider".html(),
                            "Remplire".html(),
                            "Verser".html())
                }
                .slide("Démo") { "TODO".html() }

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
//                .slide("Serveur avec ktor")
                .slide("Navigateur avec KotlinJS", styleClass = setOf("exo"))
//                .slide("Native")
//                .slide("JavaFX")
                .slide("Freestyle", styleClass = setOf("exo"))
        }
        .group("Conclusion") {
            slide("Kotlin magic", styleClass = setOf("two-columns")) {
                UnorderedList(
                        "Null Safety".html(),
                        "Lambda".html(),
                        "data class".html(),
                        "Déconstruction".html(),
                        "No primitive type".html(),
                        "Function first class citizen".html(),
                        "Multi-platform".html(),
                        "DSL ready".html(),
                        "Named parameter, default parameter value".html(),
                        "Coroutine".html(),
                        "tail recursion".html(),
                        "super <code>switch</code> avec <code>when</code>".html(),
                        "...".html()
                )
            }
                .slide("Missing part") {
                    UnorderedList(
                            "Real pattern matching".html(),
                            "private by default ?".html())
                }

        }

    val slidesDir = File("src/test/resources/")

    if (kotlinHandsOn.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        kotlinHandsOn.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web/")
    kotlinHandsOn.buildAll(dist, "devoxx-ma")
}
