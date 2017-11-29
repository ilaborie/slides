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
        slideFromResource(title = "Pourquoi un nouveau langage ?", contentType = MARKDOWN)
        slideFromResource(title = "Caractéristiques de Kotlin", contentType = MARKDOWN)
        slideFromResource(title = "Cible", contentType = HTML)
        roadmap()
    }
    part(title = "Water Pouring Problem") {
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
        slide("Solver") {
            html { "TODO" } // TODO
        }
        slide("State::move") {
            html { "TODO" } // TODO
        }
        slide("Mais aussi") {
            html { "TODO" } // TODO
        }
    }
    part("Kotlin dès maintenant") {
        slide("Android") {
            ul {
                html { "Faible overhead" }
                html { "Coder en Java 15 dès maintenant" }
                html { "Support officiel par Google" }
                linkText(link = "https://docs.google.com/document/d/1ReS3ep-hjxWA8kZi0YqDbEhCqTt29hG8P44aA9W0DM8/edit") { "Using Project Kotlin for Android" }
                linkText(link = "https://android.github.io/kotlin-guides/") { "Kotlin Guide" }
            }
        }
        slide("Server") {
            ul {
                html { "String 5, SpringBoot 2" }
                html { "Vert.x" }
                html { "SparkJava" }
                html { "..." }
            }
        }
        slide("Web") {
            ul {
                html { "Partager du code commun (backend, frontend)" }
                html { "React ?" }
            }
        }
        slide("Natif") {
            ul {
                html { "Devices sans JVM" }
                html { "iOS" }
                html { "WebAssembly" }
            }
        }
    }
    part("Conclusion") {
        slide("Pourquoi Kotlin") {
            html { "TODO" } // TODO
        }
        slideFromResource(title = "Liens")
    }
}