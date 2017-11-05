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
                .slide("Pourquoi un nouveau langage ?") {
                    listOf(
                            "Écrire du code plus sûr",
                            "Facilité la maintenance",
                            "Écrire plus rapidement",
                            "..."
                    ).ul()
                }
                .slide("Caractéristiques de Kotlin") {
                    listOf(
                            "Éviter les NPE, statiquement typé",
                            "Abordable, si on vient de Java",
                            "Expressif et pragmatique",
                            "Inspiré par Java, Scala, C#, Groovy, ..."
                    ).ul()
                }
                .slide("Cible", contentType = HTML)
//                .roadMap("Roadmap")
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
//                .slide("Native")
                .slide("Freestyle", styleClass = setOf("exo"))
                .slide("Liens")
        }
//        .group("Conclusion") {
//            slide("Les manques") {
//                UnorderedList(
//                        "Un vrai pattern-matching".html(),
//                        "Privé par défaut ?".html())
//            }
//                .slide("Ce qui est super", styleClass = setOf("two-columns")) {
//                    UnorderedList(
//                            "Null safety".html(),
//                            "Lambda".html(),
//                            "Les data class".html(),
//                            "Pas de type primitif".html(),
//                            "Top Level Function".html(),
//                            "Paramètres nommés, valeur par défaut".html(),
//                            "Tail recursion".html(),
//                            "Super <code>switch</code> avec <code>when</code>".html(),
//                            "Déconstruction".html(),
//                            "Coroutines".html(),
//                            "Posibilité d'écrire des DSL".html(),
//                            "Multi-platformes".html(),
//                            "...".html())
//                }
//        }

    val slidesDir = File("src/test/resources/")

    if (kotlinHandsOn.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        kotlinHandsOn.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web/")
    kotlinHandsOn.buildAll(dist, "devoxx-ma")
}
