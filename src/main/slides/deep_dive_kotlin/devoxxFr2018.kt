package deep_dive_kotlin

import mu.KotlinLogging
import org.ilaborie.slides.BasicSlide
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.PartTitleSlide
import org.ilaborie.slides.Presentation
import org.ilaborie.slides.RoadMapSlide
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.content.Emphasis
import org.ilaborie.slides.content.HtmlContent
import org.ilaborie.slides.content.Language
import org.ilaborie.slides.content.renderAsMarkdown
import org.ilaborie.slides.content.renderAsString
import org.ilaborie.slides.content.titleAsString
import org.ilaborie.slides.dsl.bash
import org.ilaborie.slides.dsl.code
import org.ilaborie.slides.dsl.codeFromResource
import org.ilaborie.slides.dsl.html
import org.ilaborie.slides.dsl.linkText
import org.ilaborie.slides.dsl.part
import org.ilaborie.slides.dsl.presentation
import org.ilaborie.slides.dsl.quote
import org.ilaborie.slides.dsl.roadmap
import org.ilaborie.slides.dsl.slide
import org.ilaborie.slides.dsl.slideFromResource
import org.ilaborie.slides.dsl.svg
import org.ilaborie.slides.generateMissingExternals
import org.ilaborie.slides.hasMissingExternals
import java.io.File


fun main(args: Array<String>) {
    val logger = KotlinLogging.logger("DeepDive Kotlin")

    val deepDiveKotlin = prezDevoxxFr2018()

    val slidesDir = File("src/main/slides-resources/")

    if (deepDiveKotlin.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        deepDiveKotlin.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web/")
    deepDiveKotlin.buildAll(dist, "devoxx-fr")

    // Generates notes
//    val hasCode = listOf("play", "live-code")
//    val who = listOf("igor", "manu")
//    (listOf(deepDiveKotlin) +
//            deepDiveKotlin.slides
//                .flatMap { it.toList() })
//        .joinToString("\n") { slides ->
//            when (slides) {
//                is Presentation   ->
//                    "# ${slides.title.renderAsString()}"
//                is PartTitleSlide ->
//                    "## ${slides.titleAsString()}"
//                is RoadMapSlide   ->
//                    "### ${slides.titleAsString()}"
//                is BasicSlide     -> {
//                    "### ${slides.titleAsString()}" +
//                            (slides.styleClass.intersect(who).let {
//                                if (it.isNotEmpty()) "\n${it.joinToString(", ")}\n" else ""
//                            }) +
//                            (slides.styleClass.intersect(hasCode)
//                                .let {
//                                    if (it.isNotEmpty()) """
//                                    |${it.joinToString(", ")}
//                                    |
//                                    |${slides.content?.renderAsMarkdown()?: ""}
//                                    |""".trimMargin()
//                                    else ""
//                                })
//                }
//                else              -> slides.id
//            }
//        }
//        .let(::println)
}

fun prezDevoxxFr2018() =
    presentation(title = HtmlContent("Deep Dive Kotlin :<br/> du Hello World au ByteCode"), key = "deepDiveKotlin") {
        addScript("../scripts/navigation.js")
        addScript("../scripts/catnip.js")
        addScript("../scripts/catnip-deepDiveKotlin.js")

        part(title = "Introduction") {
            skipHeader = true
            slideFromResource(title = "Speakers", key = "speakers", contentType = HTML) {
                styleClass = setOf("manu", "igor")
            }
            roadmap(title = "🗺 Roadmap")
        }

        part(title = "ByteCode Java ?", key = "bytecode") {
            slide(title = "HelloWorld.java", key = "hw-java", styleClass = setOf("code", "java", "igor", "live-code")) {
                codeFromResource("/deepDiveKotlin/bytecode/HelloWorld.java")
                bash { "javac HelloWorld.java" }
            }
            slide(title = "Java ByteCode binary", styleClass = setOf("code", "hex", "igor", "live-code")) {
                bash { "hexdump -C HelloWorld.class" }
                codeFromResource("/deepDiveKotlin/bytecode/HelloWorld.class.hex")
            }
            slide(title = "Explorons le ByteCode", styleClass = setOf("code", "bytecode", "igor", "live-code")) {
                bash { "javap -c HelloWorld.class" }
                codeFromResource("/deepDiveKotlin/bytecode/HelloWorld.class.txt")
            }
            slide(title = "Transpile", styleClass = setOf("diagram", "igor")) {
                svg("/deepDiveKotlin/bytecode/javac.svg")
            }
            slideFromResource(title = "À propos du ByteCode", key = "bytecode-details") {
                styleClass = setOf("details", "contrast", "igor")
            }
            slideFromResource(title = "Jouons un peu", key = "bytecode-play", contentType = HTML)
            slideFromResource(title = "Liens", key = "bytecode-links") {
                styleClass = setOf("bilan", "contrast", "igor")
            }
        }

        part(title = "Introduction Kotlin") {
            slideFromResource(title = "Historique", contentType = HTML) {
                // FIXME Igor
                styleClass = setOf("manu")
            }
            slideFromResource(title = "Cibles", contentType = HTML) { styleClass = setOf("manu") }

            slide(title = "HelloWorld.kt", key = "hw-kotlin", styleClass = setOf("code", "kotlin", "manu", "live-code")) {
                codeFromResource("/deepDiveKotlin/introduction_kotlin/HelloWorld.kt")
                bash { "kotlinc HelloWorld.kt" }
            }
            slide(title = "hexdump", styleClass = setOf("code", "hex", "manu")) {
                codeFromResource("/deepDiveKotlin/introduction_kotlin/HelloWorldKt.class.hex")
            }
            slide(title = "Java ByteCode", styleClass = setOf("code", "bytecode", "manu")) {
                codeFromResource("/deepDiveKotlin/introduction_kotlin/HelloWorldKt.class.txt")
            }
            slide(title = "HelloWorld-java", styleClass = setOf("code", "java", "manu")) {
                codeFromResource("/deepDiveKotlin/introduction_kotlin/HelloWorldKt.java")
            }
            slideFromResource(title = "Bilan HelloWorld.kt") { styleClass = setOf("bilan", "contrast", "manu") }
//            slideFromResource(title = "Performances ?", key = "performance") {
//                styleClass = setOf("measure", "contrast", "manu")
//            }
            slideFromResource(title = "Performance HelloWorld.kt") { styleClass = setOf("bilan", "contrast", "igor") }
        }

        part(title = "Les bases", key = "basic") {
            slide(title = "val-var.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/basic/val-var.kt")
            }
            slide(title = "string-template.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/basic/string-template.kt")
            }
            slide(title = "string-template.java", styleClass = setOf("code", "java", "igor")) {
                codeFromResource("/deepDiveKotlin/basic/String_templatesKt.java")
            }
            slide(title = "ByteCode de string-template", styleClass = setOf("code", "bytecode", "igor")) {
                codeFromResource("/deepDiveKotlin/basic/String_templatesKt.class.txt")
            }
            slide(title = "numeric.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/basic/numeric.kt")
            }
            slide(title = "numeric.java", styleClass = setOf("code", "java", "igor")) {
                codeFromResource("/deepDiveKotlin/basic/NumericKt.java")
            }
            slide(title = "ByteCode de numeric", styleClass = setOf("code", "bytecode", "igor")) {
                codeFromResource("/deepDiveKotlin/basic/NumericKt.class.txt")
            }
            slideFromResource(title = "C'est simple", key = "basic-bilan") {
                styleClass = setOf("bilan", "contrast", "igor")
            }
        }

        part(title = "null-safety") {
            slide(title = Emphasis(HtmlContent("billion-dollar mistake")), styleClass = setOf("contrast", "manu")) {
                quote(author = "Tony Hoare (C.A.R. Hoare)") {
                    html {
                        "I call it my billion-dollar mistake. It was the invention of the <code>null</code> reference in 1965. At that time, I was designing the first comprehensive type system for references in an object oriented language (ALGOL W). My goal was to ensure that all use of references should be absolutely safe, with checking performed automatically by the compiler. But I couldn't resist the temptation to put in a <code>null</code> reference, simply because it was so easy to implement. This has led to innumerable errors, vulnerabilities, and system crashes, which have probably caused a billion dollars of pain and damage in the last forty years."
                    }
                }
                linkText("https://www.infoq.com/presentations/Null-References-The-Billion-Dollar-Mistake-Tony-Hoare") { "Null References: The Billion Dollar Mistake" }
            }
            slideFromResource(title = "elvis") { styleClass = setOf("manu") }
            // TODO manu
        }

        part(title = "Les types", key = "types") {
            slide(title = "Types basiques", styleClass = setOf("diagram", "igor")) {
                svg("/deepDiveKotlin/types/KotlinBaseTypes.svg")
            }
            slide(title = "Types basiques nullable", styleClass = setOf("diagram", "igor")) {
                svg("/deepDiveKotlin/types/KotlinBaseTypes_.svg")
            }
            slide(title = "todo.kt", styleClass = setOf("code", "kotlin", "igor", "play")) {
                codeFromResource("/deepDiveKotlin/types/todo.kt")
            }
            slideFromResource(title = "Hierarchie des types") {
                styleClass = setOf("bilan", "contrast", "igor")
            }
        }

        part(title = "Les fonctions", key = "fonction") {
            slide(title = "named-params.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/fonction/named.kt")
            }
            slide(title = "named-params.java", styleClass = setOf("code", "java", "igor")) {
                codeFromResource("/deepDiveKotlin/fonction/NamedKt.java")
            }
            slide(title = "default-value.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/fonction/default-value.kt")
            }
            slide(title = "default-value.java", styleClass = setOf("code", "java", "igor")) {
                codeFromResource("/deepDiveKotlin/fonction/Default_valueKt.java")
            }
            slide(title = "ByteCode de default-value", styleClass = setOf("code", "bytecode", "igor")) {
                codeFromResource("/deepDiveKotlin/fonction/Default_valueKt.class.txt")
            }
            slideFromResource(title = HtmlContent("C'est <code>fun</code> !"), key = "fun-bilan")
            { styleClass = setOf("bilan", "contrast", "igor") }
        }

        part(title = "Les lambdas", key = "lambda") {
            slideFromResource(title = "Les lambdas", key = "lambda") { styleClass = setOf("manu") }
            // TODO manu
        }

        part(title = "Les classes", key = "class") {
            // TODO manu
            slide(title = "Héritage en Kotlin", styleClass = setOf("code", "kotlin", "igor", "live-code")) {
                codeFromResource("/deepDiveKotlin/class/inheritance.kt")
            }
            slideFromResource("Générics", key = "generics") { styleClass = setOf("details", "contrast", "igor") }
            slide(HtmlContent("Projection <code>*</code>"), key = "star", styleClass = setOf("details", "igor")) {
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
            slide(title = "Sealed", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/class/json.kt")
            }
            slide(title = "Alias en Kotlin", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/class/typealias.kt")
            }
            slide(title = "ByteCode d'alias", styleClass = setOf("code", "bytecode", "igor")) {
                codeFromResource("/deepDiveKotlin/class/TypealiasKt.class.txt")
            }

            // TODO manu
            slideFromResource(title = "Classe, le bilan", key = "class-bilan") {
                styleClass = setOf("bilan", "contrast", "manu", "igor")
            }
        }

        part(title = "Extensions de fonctions", key = "extensions_de_fonction") {
            slideFromResource(title = "Extension") { styleClass = setOf("manu") }
            // TODO manu
        }

        part(title = "Pause") {}

        part(title = "ByteCode Android", key = "android") {
            // TODO manu
            slideFromResource(title = "Android ByteCode") { styleClass = setOf("manu") }
            slideFromResource(title = "ProGuard") { styleClass = setOf("manu") }
        }

        part(title = "Autres structures", key = "structure") {
            slide(title = "if-then-else.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/structure/if-then-else.kt")
            }
            slide(title = "for.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/structure/for.kt")
            }
            slide(title = "for.java", styleClass = setOf("code", "java", "igor")) {
                codeFromResource("/deepDiveKotlin/structure/ForKt.java")
            }
            slide(title = "ByteCode du for", styleClass = setOf("code", "bytecode", "igor")) {
                codeFromResource("/deepDiveKotlin/structure/ForKt.class.txt")
            }
            slide(title = "while-do.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/structure/while-do.kt")
            }
            slide(title = "when.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/structure/when.kt")
            }
            slide(title = "for-factorial.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/structure/for-factorial.kt")
            }
            slide(title = "ByteCode factoriel avec for", styleClass = setOf("code", "bytecode", "igor")) {
                codeFromResource("/deepDiveKotlin/structure/For_factorialKt.class.txt")
            }
            slide(title = "rec-factorial.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/structure/rec-factorial.kt")
            }
            slide(title = "ByteCode factoriel avec recursivité", styleClass = setOf("code", "bytecode", "igor")) {
                codeFromResource("/deepDiveKotlin/structure/Rec_factorialKt.class.txt")
            }
            slide(title = "tailrec-factorial.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/structure/tailrec-factorial.kt")
            }
            slide(title = "ByteCode factoriel avec recursivité terminal 1/2",
                  styleClass = setOf("code", "bytecode", "igor")) {
                codeFromResource("/deepDiveKotlin/structure/Tailrec_factorialKt.class.txt")
            }
            slide(title = "ByteCode factoriel avec recursivité terminal 2/2",
                  styleClass = setOf("code", "bytecode", "igor")) {
                codeFromResource("/deepDiveKotlin/structure/Tailrec_factorialKt${'$'}tailRecFactorial$1.class.txt")
            }
            slideFromResource(title = "Performances sur 10!") { styleClass = setOf("measure", "contrast", "igor") }
            slideFromResource(title = "Bilan structures", key = "bilan-structures") {
                styleClass = setOf("details", "contrast", "igor")
            }
        }

        part(title = "Les collections", key = "collection") {
            slide(title = "api.kt", styleClass = setOf("code", "kotlin", "igor", "live-code")) {
                codeFromResource("/deepDiveKotlin/collection/api.kt")
            }
            slide(title = "immutable-mutable.kt", styleClass = setOf("code", "kotlin", "igor", "play")) {
                codeFromResource("/deepDiveKotlin/collection/immutable-mutable.kt")
            }
            slide(title = "break-immutable.kt", styleClass = setOf("code", "kotlin", "igor", "play")) {
                codeFromResource("/deepDiveKotlin/collection/break-immutable.kt")
            }
            // TODO manu
            slideFromResource(title = "Bilan collection", key = "collection") {
                styleClass = setOf("details", "contrast", "igor", "manu")
            }
        }

        part(title = "Les delegates", key = "delegate") {
            slideFromResource(title = "Delegate") { styleClass = setOf("manu") }
            // TODO manu
        }

        part(title = "Un peu plus sur les fonctions", key = "plus_sur_les_fonctions") {
            slide(title = "inline.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/plus_sur_les_fonctions/inline.kt")
            }
            slide(title = "Logger.java", styleClass = setOf("code", "java", "igor")) {
                codeFromResource("/deepDiveKotlin/plus_sur_les_fonctions/Logger.java")
            }
            slide(title = "reified.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/plus_sur_les_fonctions/reified.kt")
            }
            slideFromResource(title = "Plus sur les fonctions") { styleClass = setOf("details", "contrast", "igor") }
        }

        part(title = "Conclusion") {
            slideFromResource(title = "Android") { styleClass = setOf("contrast", "manu") }
            slideFromResource(title = "Serveur") { styleClass = setOf("contrast", "igor") }
            slideFromResource(title = "Web et Natif") { styleClass = setOf("contrast", "igor", "manu") }
            slideFromResource(title = "Kotlin vs Java", key = "kotlin_vs_java") {
                styleClass = setOf("contrast", "manu", "igor")
            }
            slideFromResource(title = "Liens") { styleClass = setOf("contrast", "manu", "igor") }
            slideFromResource(title = "Bibliothèques") { styleClass = setOf("contrast", "manu", "igor") }
            slideFromResource(title = "Merci") { styleClass = setOf("contrast", "manu", "igor") }
        }
    }
