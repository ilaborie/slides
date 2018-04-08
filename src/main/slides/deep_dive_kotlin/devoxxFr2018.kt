package deep_dive_kotlin

import mu.KotlinLogging
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.content.HtmlContent
import org.ilaborie.slides.content.Language
import org.ilaborie.slides.dsl.bash
import org.ilaborie.slides.dsl.code
import org.ilaborie.slides.dsl.codeFromResource
import org.ilaborie.slides.dsl.part
import org.ilaborie.slides.dsl.presentation
import org.ilaborie.slides.dsl.roadmap
import org.ilaborie.slides.dsl.slide
import org.ilaborie.slides.dsl.slideFromResource
import org.ilaborie.slides.dsl.svg
import org.ilaborie.slides.generateMissingExternals
import org.ilaborie.slides.hasMissingExternals
import java.io.File


fun main(args: Array<String>) {
    val logger = KotlinLogging.logger("DeepDive Kotlin")

    val kotlinHandsOn = prezDevoxxFr2018()

    val slidesDir = File("src/main/slides-resources/")

    if (kotlinHandsOn.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        kotlinHandsOn.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web/")
    kotlinHandsOn.buildAll(dist, "devoxx-fr")
}

fun prezDevoxxFr2018() =
    presentation(title = HtmlContent("Deep Dive Kotlin :<br/> du Hello World au ByteCode"), key = "deepDiveKotlin") {
        addScript("../scripts/navigation.js")
        addScript("../scripts/catnip.js")
        addScript("../scripts/catnip-deepDiveKotlin.js")

        part(title = "Introduction") {
            skipHeader = true
            slideFromResource(title = "Speakers", key = "speakers", contentType = HTML)
            roadmap(title = "🗺 Roadmap")
        }

        part(title = "ByteCode Java ?", key = "bytecode") {
            slide(title = "HelloWorld.java", key = "hw-java", styleClass = setOf("code", "java")) {
                codeFromResource("/deepDiveKotlin/bytecode/HelloWorld.java")
                bash { "javac HelloWorld.java" }
            }
            slide(title = "Java ByteCode binary", styleClass = setOf("code", "hex")) {
                bash { "hexdump -C HelloWorld.class" }
                codeFromResource("/deepDiveKotlin/bytecode/HelloWorld.class.hex")
            }
            slide(title = "Explorons le ByteCode", styleClass = setOf("code", "bytecode")) {
                bash { "javap -c HelloWorld.class" }
                codeFromResource("/deepDiveKotlin/bytecode/HelloWorld.class.txt")
            }
            slide(title = "Transpile", styleClass = setOf("diagram")) {
                svg("/deepDiveKotlin/bytecode/javac.svg")
            }
            slideFromResource(title = "À propos du ByteCode", key = "bytecode-details") {
                styleClass = setOf("details", "contrast")
            }
            slideFromResource(title = "Jouons un peu", key = "bytecode-play", contentType = HTML)
            slideFromResource(title = "Liens", key = "bytecode-links") { styleClass = setOf("bilan", "contrast") }
        }

        part(title = "Introduction Kotlin") {
            slideFromResource(title = "Historique", contentType = HTML)
            slideFromResource(title = "Cibles", contentType = HTML)

            slide(title = "HelloWorld.kt", key = "hw-kotlin", styleClass = setOf("code", "kotlin")) {
                codeFromResource("/deepDiveKotlin/introduction_kotlin/HelloWorld.kt")
                bash { "kotlinc HelloWorld.kt" }
            }
            slide(title = "hexdump", styleClass = setOf("code", "hex")) {
                codeFromResource("/deepDiveKotlin/introduction_kotlin/HelloWorldKt.class.hex")
            }
            slide(title = "Java ByteCode", styleClass = setOf("code", "bytecode")) {
                codeFromResource("/deepDiveKotlin/introduction_kotlin/HelloWorldKt.class.txt")
            }
            slide(title = "HelloWorld-java", styleClass = setOf("code", "java")) {
                codeFromResource("/deepDiveKotlin/introduction_kotlin/HelloWorldKt.java")
            }
            slideFromResource(title = "Bilan HelloWorld.kt") { styleClass = setOf("bilan", "contrast") }
            slideFromResource(title = "Performances ?", key = "performance") {
                styleClass = setOf("measure", "contrast")
            }
            slideFromResource(title = "Performance HelloWorld.kt") { styleClass = setOf("bilan", "contrast") }
        }

        part(title = "Les bases", key = "basic") {
            slide(title = "val-var.kt", styleClass = setOf("code", "kotlin")) {
                codeFromResource("/deepDiveKotlin/basic/val-var.kt")
            }
            slide(title = "string-template.kt", styleClass = setOf("code", "kotlin")) {
                codeFromResource("/deepDiveKotlin/basic/string-template.kt")
            }
            slide(title = "string-template.java", styleClass = setOf("code", "java")) {
                codeFromResource("/deepDiveKotlin/basic/String_templatesKt.java")
            }
            slide(title = "ByteCode de string-template", styleClass = setOf("code", "bytecode")) {
                codeFromResource("/deepDiveKotlin/basic/String_templatesKt.class.txt")
            }
            slide(title = "numeric.kt", styleClass = setOf("code", "kotlin")) {
                codeFromResource("/deepDiveKotlin/basic/numeric.kt")
            }
            slide(title = "numeric.java", styleClass = setOf("code", "java")) {
                codeFromResource("/deepDiveKotlin/basic/NumericKt.java")
            }
            slide(title = "ByteCode de numeric", styleClass = setOf("code", "bytecode")) {
                codeFromResource("/deepDiveKotlin/basic/NumericKt.class.txt")
            }
            slideFromResource(title = "C'est simple", key = "basic-bilan") { styleClass = setOf("bilan", "contrast") }
        }

        part(title = "null-safety") {
            slideFromResource(title = "elvis")
            // TODO manu
        }

        part(title = "Les fonctions", key = "fonction") {
            slide(title = "named-params.kt", styleClass = setOf("code", "kotlin")) {
                codeFromResource("/deepDiveKotlin/fonction/named.kt")
            }
            slide(title = "named-params.java", styleClass = setOf("code", "java")) {
                codeFromResource("/deepDiveKotlin/fonction/NamedKt.java")
            }
            slide(title = "default-value.kt", styleClass = setOf("code", "kotlin")) {
                codeFromResource("/deepDiveKotlin/fonction/default-value.kt")
            }
            slide(title = "default-value.java", styleClass = setOf("code", "java")) {
                codeFromResource("/deepDiveKotlin/fonction/Default_valueKt.java")
            }
            slide(title = "ByteCode de default-value", styleClass = setOf("code", "bytecode")) {
                codeFromResource("/deepDiveKotlin/fonction/Default_valueKt.class.txt")
            }
            slideFromResource(title = HtmlContent("C'est <code>fun</code> !"), key = "fun-bilan")
            { styleClass = setOf("bilan", "contrast") }
        }

        part(title = "Les lambdas", key = "lambda") {
            slideFromResource(title = "Les lambdas", key = "lambda")
            // TODO manu
        }

        part(title = "Les classes", key = "class") {
            slide(title = "Point.kt", styleClass = setOf("code", "kotlin")) {
                codeFromResource("/deepDiveKotlin/class/Point.kt")
            }
            slide(title = "Point.java", styleClass = setOf("code", "java")) {
                codeFromResource("/deepDiveKotlin/class/Point.java")
            }
            slide(title = "Point2.kt", styleClass = setOf("code", "kotlin")) {
                codeFromResource("/deepDiveKotlin/class/Point2.kt")
            }
            slide(title = "Point2.java", styleClass = setOf("code", "java")) {
                codeFromResource("/deepDiveKotlin/class/Point2.java")
            }
            slide(title = "Point3.kt", styleClass = setOf("code", "kotlin")) {
                codeFromResource("/deepDiveKotlin/class/Point3.kt")
            }
            slide(title = "Point3.java", styleClass = setOf("code", "java")) {
                codeFromResource("/deepDiveKotlin/class/Point3.java")
            }
            slide(title = "Point4.kt", styleClass = setOf("code", "kotlin")) {
                codeFromResource("/deepDiveKotlin/class/Point4.kt")
            }
            slide(title = "Point4.java", styleClass = setOf("code", "java")) {
                codeFromResource("/deepDiveKotlin/class/Point4.java")
            }
            // TODO manu
            slide(title = "Héritage en Kotlin", styleClass = setOf("code", "kotlin")) {
                codeFromResource("/deepDiveKotlin/class/inheritance.kt")
            }
            slideFromResource("Générics", key = "generics") { styleClass = setOf("details", "contrast") }
            slide(HtmlContent("Projection <code>*</code>"), key = "star", styleClass = setOf("details")) {
                code(Language.Kotlin) {
                    "interface Function<in T, out U>"
                }
                code(Language.Kotlin) {
                    "Function<*, String> // correspond à Function<in Nothing, String>"
                }
                code(Language.Kotlin) {
                    "Function<Int, *> // correspond à Function<Int, out Any?>"
                }
                code(Language.Kotlin) {
                    "Function<*, *> // correspond à Function<in Nothing, out Any?>"
                }
            }
            slide(title = "Sealed", styleClass = setOf("code", "kotlin")) {
                codeFromResource("/deepDiveKotlin/class/json.kt")
            }
            slide(title = "Alias en Kotlin", styleClass = setOf("code", "kotlin")) {
                codeFromResource("/deepDiveKotlin/class/typealias.kt")
            }
            slide(title = "ByteCode d'alias", styleClass = setOf("code", "bytecode")) {
                codeFromResource("/deepDiveKotlin/class/TypealiasKt.class.txt")
            }

            // TODO manu
            slideFromResource(title = "Classe, le bilan", key = "class-bilan") {
                styleClass = setOf("bilan", "contrast")
            }
        }

        part(title = "Types") {
            slideFromResource(title = "Hierarchie des types")
            // FIXME
        }

        part(title = "Extensions de fonction") {
            slideFromResource(title = "Extension")
            // TODO manu
        }

        part(title = "Structure") {
            slideFromResource(title = "Structures")
            // FIXME
        }

        part(title = "Pause") {}

        part(title = "ByteCode Android", key = "android") {
            // TODO manu
            slideFromResource(title = "Android ByteCode")
            slideFromResource(title = "ProGuard")
        }

        part(title = "Les collections", key = "collection") {
            slideFromResource(title = "Collection")
            // FIXME
            // TODO manu
        }

        part(title = "Les delegates", key = "delegate") {
            slideFromResource(title = "Delegate")
            // TODO manu
        }

        part(title = "En peu plus sur les fonctions") {
            slideFromResource(title = "Plus sur les fonctions")
            // FIXME
        }

        part(title = "kotlinx.serialization", key = "serialization") {
            slideFromResource(title = "serialisation")
            // TODO manu
        }

        part(title = "Les coroutines", key = "courountines") {
            slideFromResource(title = "Coroutines")
            // FIXME
        }

        part(title = "Conclusion") {
            slideFromResource(title = "Android") { styleClass = setOf("details", "contrast") }
            slideFromResource(title = "Serveur") { styleClass = setOf("details", "contrast") }
            slideFromResource(title = "Web et Natif") { styleClass = setOf("details", "contrast") }
            slideFromResource(title = "Kotlin vs Java") { styleClass = setOf("details", "contrast") }
            slideFromResource(title = "Liens") { styleClass = setOf("details", "contrast") }
            slideFromResource(title = "Q/A")
        }
    }
