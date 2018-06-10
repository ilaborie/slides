package deep_dive_kotlin

import mu.KotlinLogging
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.content.Emphasis
import org.ilaborie.slides.content.HtmlContent
import org.ilaborie.slides.dsl.bash
import org.ilaborie.slides.dsl.codeFromResource
import org.ilaborie.slides.dsl.html
import org.ilaborie.slides.dsl.img
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
    // Duotone: #e1eff2, #99a4bf

    val logger = KotlinLogging.logger("DeepDive Kotlin")

    val deepDiveKotlin = prezJugTls()

    val slidesDir = File("src/main/slides-resources/")

    if (deepDiveKotlin.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        deepDiveKotlin.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web/")
    deepDiveKotlin.buildAll(dist, "jug", exportPdf = true)

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

fun prezJugTls() =
    presentation(title = HtmlContent("Deep Dive Kotlin :<br/> du Hello World au ByteCode"), key = "deepDiveKotlin") {
        addScript("../scripts/navigation.js")
        addScript("../scripts/catnip.js")
        addScript("../scripts/catnip-deepDiveKotlin.js")
        addScript("../scripts/deepDiveKotlin.js")

        part(title = "Introduction") {
            skipHeader = true
            slideFromResource(title = "Speakers", key = "speakers", contentType = HTML) {
                styleClass = setOf("manu", "igor")
            }
            roadmap(title = "🗺 Roadmap")
        }

        part(title = "ByteCode Java ?", key = "bytecode") {
            slide(title = "javac", styleClass = setOf("diagram", "igor")) {
                svg("/deepDiveKotlin/bytecode/Compile Java.svg")
            }
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
//            slideFromResource(title = "À propos du ByteCode", key = "bytecode-details") {
//                styleClass = setOf("details", "contrast", "igor")
//            }
            slideFromResource(title = "Jouons un peu", key = "bytecode-play", contentType = HTML) {
                styleClass += "igor"
            }
            slideFromResource(title = "Liens", key = "bytecode-links") {
                styleClass = setOf("bilan", "contrast", "igor")
            }
        }

        part(title = "Introduction Kotlin") {
            slideFromResource(title = "Historique", contentType = HTML) {
                // FIXME Igor
                styleClass = setOf("manu", "contrast")
            }
            slideFromResource(title = "Cibles", contentType = HTML) { styleClass = setOf("manu") }

            slide(title = "HelloWorld.kt",
                  key = "hw-kotlin",
                  styleClass = setOf("code", "kotlin", "igor", "live-code")) {
                codeFromResource("/deepDiveKotlin/introduction_kotlin/HelloWorld.kt")
                bash { "kotlinc HelloWorld.kt" }
            }
            slide(title = "kotlinc", styleClass = setOf("diagram", "igor")) {
                svg("/deepDiveKotlin/introduction_kotlin/Compile Kotlin.svg")
            }
            slide(title = "hexdump", styleClass = setOf("code", "hex", "igor")) {
                codeFromResource("/deepDiveKotlin/introduction_kotlin/HelloWorldKt.class.hex")
            }
            slide(title = "Java ByteCode", styleClass = setOf("code", "bytecode", "igor")) {
                codeFromResource("/deepDiveKotlin/introduction_kotlin/HelloWorldKt.class.txt")
            }
            slide(title = "HelloWorld-java", styleClass = setOf("code", "java", "igor")) {
                codeFromResource("/deepDiveKotlin/introduction_kotlin/HelloWorldKt.java")
            }
            slideFromResource(title = "Bilan HelloWorld.kt") { styleClass = setOf("bilan", "contrast", "igor") }
//            slideFromResource(title = "Performances ?", key = "performance") {
//                styleClass = setOf("measure", "contrast", "manu")
//            }
            slideFromResource(title = "Performance HelloWorld.kt") { styleClass = setOf("bilan", "contrast", "igor") }
        }

        part(title = "Les bases", key = "basic") {
            slide(title = "val-var.kt", styleClass = setOf("code", "kotlin", "manu")) {
                codeFromResource("/deepDiveKotlin/basic/val-var.kt")
            }
            slide(title = "string-template.kt", styleClass = setOf("code", "kotlin", "manu")) {
                codeFromResource("/deepDiveKotlin/basic/string-template.kt")
            }
            slide(title = "string-template.java", styleClass = setOf("code", "java", "manu")) {
                codeFromResource("/deepDiveKotlin/basic/String_templatesKt.java")
            }
            slide(title = "ByteCode de string-template", styleClass = setOf("code", "bytecode", "manu")) {
                codeFromResource("/deepDiveKotlin/basic/String_templatesKt.class.txt")
            }
            slide(title = "numeric.kt", styleClass = setOf("code", "kotlin", "manu")) {
                codeFromResource("/deepDiveKotlin/basic/numeric.kt")
            }
            slide(title = "numeric.java", styleClass = setOf("code", "java", "manu")) {
                codeFromResource("/deepDiveKotlin/basic/NumericKt.java")
            }
            slide(title = "ByteCode de numeric", styleClass = setOf("code", "bytecode", "manu")) {
                codeFromResource("/deepDiveKotlin/basic/NumericKt.class.txt")
            }
            slideFromResource(title = "C'est simple", key = "basic-bilan") {
                styleClass = setOf("bilan", "contrast", "manu")
            }
        }

        part(title = "null-safety") {
            slide(title = Emphasis(HtmlContent("billion-dollar mistake")), styleClass = setOf("contrast", "manu")) {
                quote(author = "Tony Hoare (C.A.R. Hoare)") {
                    html {
                        "I call it my <em>billion-dollar mistake</em>. It was the invention of the <code>null</code> reference in 1965. At that time, [...]. This has led to innumerable errors, vulnerabilities, and system crashes, which have probably caused a billion dollars of pain and damage in the last forty years."
                    }
                }
                linkText("https://www.infoq.com/presentations/Null-References-The-Billion-Dollar-Mistake-Tony-Hoare") { "Null References: The Billion Dollar Mistake" }
            }
            slide(title = "null-safety.kt", styleClass = setOf("code", "kotlin", "manu", "live-code")) {
                codeFromResource("/deepDiveKotlin/null_safety/null-safety.kt")
            }
            slide(title = "null-safety.java", styleClass = setOf("code", "java", "manu")) {
                codeFromResource("/deepDiveKotlin/null_safety/NullSafetyKt.java")
            }
            slideFromResource(title = "Bilan null-safety", key = "elvis") {
                styleClass = setOf("bilan", "contrast", "manu")
            }
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
            slide(title = "function.kt", styleClass = setOf("code", "kotlin", "manu")) {
                codeFromResource("/deepDiveKotlin/lambda/function.kt")
            }
            slide(title = "function.java", styleClass = setOf("code", "kotlin", "manu")) {
                codeFromResource("/deepDiveKotlin/lambda/FunctionKt.java")
            }
            slide(title = "lambda.kt", styleClass = setOf("code", "kotlin", "manu", "live-code")) {
                codeFromResource("/deepDiveKotlin/lambda/lambda.kt")
            }
            slide(title = "lambda.java", styleClass = setOf("code", "kotlin", "manu")) {
                codeFromResource("/deepDiveKotlin/lambda/LambdaKt.java")
            }
            slide(title = "let.kt", styleClass = setOf("code", "kotlin", "manu")) {
                codeFromResource("/deepDiveKotlin/lambda/let.kt")
            }
            slide(title = "let.java", styleClass = setOf("code", "kotlin", "manu")) {
                codeFromResource("/deepDiveKotlin/lambda/LetKt.java")
            }
            slideFromResource(title = "Les lambdas", key = "lambda") { styleClass = setOf("bilan", "contrast", "manu") }
        }

        part(title = "Les classes", key = "class") {
            slide(title = "astronomy.kt", styleClass = setOf("code", "kotlin", "manu", "live-code")) {
                codeFromResource("/deepDiveKotlin/class/astronomy.kt")
            }
            slide(title = "astronomy.java", styleClass = setOf("code", "java", "manu")) {
                codeFromResource("/deepDiveKotlin/class/Planet.java")
            }
            slide(title = "Héritage en Kotlin", styleClass = setOf("code", "kotlin", "igor", "live-code")) {
                codeFromResource("/deepDiveKotlin/class/inheritance.kt")
            }
            slideFromResource("Génériques", key = "generics") { styleClass = setOf("details", "contrast", "igor") }
//            slide(HtmlContent("Projection <code>*</code>"), key = "star", styleClass = setOf("details", "igor")) {
//                code(Language.Kotlin) {
//                    "interface Function<in T, out U>"
//                }
//                code(Language.Kotlin) {
//                    "Function<*, String> // correspond à Function<in Nothing, String>"
//                }
//                code(Language.Kotlin) {
//                    "Function<Int, *> // correspond à Function<Int, out Any?>"
//                }
//                code(Language.Kotlin) {
//                    "Function<*, *> // correspond à Function<in Nothing, out Any?>"
//                }
//            }
            slide(title = "Sealed", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/class/json.kt")
            }
            slide(title = "Alias en Kotlin", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/class/typealias.kt")
            }
//            slide(title = "ByteCode d'alias", styleClass = setOf("code", "bytecode", "igor")) {
//                codeFromResource("/deepDiveKotlin/class/TypealiasKt.class.txt")
//            }
            slideFromResource(title = "Classe, le bilan", key = "class-bilan") {
                styleClass = setOf("bilan", "contrast", "manu", "igor")
            }
        }

        part(title = "ByteCode Android", key = "android") {
            slide(title = "Compilation pour Android", key = "compile-android", styleClass = setOf("diagram", "manu")) {
                svg("/deepDiveKotlin/android/Compile Android.svg")
            }
            slide(title = "Dalvik EXecutable format", styleClass = setOf("code", "hex", "manu")) {
                linkText("https://source.android.com/devices/tech/dalvik/dex-format") { "Dalvik Executable format " }
                bash {
                    """java -jar ./scripts/lib/d8.jar --release \
                    |            --output ./target/android/dex \
                    |            ./target/android/hello.jar""".trimMargin()
                }
                codeFromResource("/deepDiveKotlin/android/classes.dex.txt")
            }

            slide(title = "dexdump", styleClass = setOf("code", "hex", "manu")) {

                bash {
                    """~/.android-sdk/build-tools/23.0.1/dexdump -d \
                    |      ./target/android/dex/classes.dex \
                    |      > ./target/android/dex/classes.dex.dump""".trimMargin()
                }
                codeFromResource("/deepDiveKotlin/android/classes.dex.dump")
            }
            slide(title = "smali", styleClass = setOf("code", "smali", "manu")) {
                bash {
                    """sh ./scripts/lib/dextools/d2j-dex2smali.sh \
                    |     ./target/android/dex/classes.dex -f \
                    |     -o ./target/android/smali""".trimMargin()
                }
                codeFromResource("/deepDiveKotlin/android/HelloWorldKt.smali")
            }
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
//            slide(title = "ByteCode factoriel avec for", styleClass = setOf("code", "bytecode", "igor")) {
//                codeFromResource("/deepDiveKotlin/structure/For_factorialKt.class.txt")
//            }
            slide(title = "rec-factorial.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/structure/rec-factorial.kt")
            }
//            slide(title = "ByteCode factoriel avec recursivité", styleClass = setOf("code", "bytecode", "igor")) {
//                codeFromResource("/deepDiveKotlin/structure/Rec_factorialKt.class.txt")
//            }
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
            slideFromResource(title = HtmlContent("Performances sur <code>10!</code> 1/2"),
                              key = "performances_sur_10_") { styleClass = setOf("measure", "contrast", "igor") }
            slide(title = HtmlContent("Performances sur <code>10!</code> 2/2"),
                  key = "performances_sur_10_2", styleClass = setOf("measure", "contrast", "igor")) {
                img("Performances sur 10!", "/deepDiveKotlin/structure/factorial performance.png")
            }
            slideFromResource(title = "Bilan structures", key = "bilan-structures") {
                styleClass = setOf("details", "contrast", "igor")
            }
        }

        part(title = "Extensions de fonctions", key = "extensions_de_fonction") {
            slide(title = "extension.kt", styleClass = setOf("code", "kotlin", "live-code", "manu")) {
                codeFromResource("/deepDiveKotlin/extensions_de_fonction/extension.kt")
            }
            slide(title = "extension.java", styleClass = setOf("java", "code", "manu")) {
                codeFromResource("/deepDiveKotlin/extensions_de_fonction/ExtensionKt.java")
            }
            slideFromResource(title = "Extension") { styleClass = setOf("bilan", "contrast", "manu") }
        }

        part(title = "Les collections", key = "collection") {
            slide(title = "Collections", styleClass = setOf("diagram", "igor")) {
                svg("/deepDiveKotlin/collection/Collection.svg")
            }
            slide(title = "Les Maps", styleClass = setOf("diagram", "igor")) {
                svg("/deepDiveKotlin/collection/Map.svg")
            }
            slide(title = "api.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/collection/api.kt")
            }
//            slide(title = "immutable-mutable.kt", styleClass = setOf("code", "kotlin", "igor", "play")) {
//                codeFromResource("/deepDiveKotlin/collection/immutable-mutable.kt")
//            }
//            slide(title = "break-immutable.kt", styleClass = setOf("code", "kotlin", "igor", "play")) {
//                codeFromResource("/deepDiveKotlin/collection/break-immutable.kt")
//            }
            slide(title = "sequence.kt", styleClass = setOf("code", "kotlin", "manu")) {
                codeFromResource("/deepDiveKotlin/collection/sequence.kt")
            }
            slideFromResource(title = "Performance des sequences", key = "performance_des_sequences") {
                styleClass = setOf("measure", "contrast", "manu")
            }
//            slide(title = "sequence2.kt", styleClass = setOf("code", "kotlin", "manu")) {
//                codeFromResource("/deepDiveKotlin/collection/sequence2.kt")
//            }
//            slideFromResource(title = "Performance des sequences 2/2", key = "performance_des_sequences2") {
//                styleClass = setOf("measure", "contrast", "manu")
//            }
            slide(title = "timed-sequence.kt", styleClass = setOf("code", "play", "kotlin", "manu")) {
                codeFromResource("/deepDiveKotlin/collection/timed-sequence.kt")
            }
            slide(title = "ranges.kt", styleClass = setOf("code", "kotlin", "manu")) {
                codeFromResource("/deepDiveKotlin/collection/ranges.kt")
            }
//            slide(title = "ranges.java", styleClass = setOf("code", "java", "manu")) {
//                codeFromResource("/deepDiveKotlin/collection/RangesKt.java")
//            }
            slide(title = "tuples.kt", styleClass = setOf("code", "kotlin", "manu")) {
                codeFromResource("/deepDiveKotlin/collection/tuples.kt")
            }
//            slide(title = "tuples.java", styleClass = setOf("code", "java", "manu")) {
//                codeFromResource("/deepDiveKotlin/collection/TuplesKt.java")
//            }
            slideFromResource(title = "Bilan collection", key = "collection") {
                styleClass = setOf("details", "contrast", "igor", "manu")
            }
        }

        part(title = "Bonus") {
            slide(title = "delegate.kt", styleClass = setOf("code", "kotlin", "manu")) {
                codeFromResource("/deepDiveKotlin/bonus/delegate.kt")
            }
            slideFromResource(title = "Delegate") {
                styleClass = setOf("details", "contrast", "manu")
            }
            slide(title = "reified.kt", styleClass = setOf("code", "kotlin", "igor")) {
                codeFromResource("/deepDiveKotlin/bonus/reified.kt")
            }
            slideFromResource(title = "Plus sur les fonctions", contentType = HTML) {
                styleClass = setOf("details", "contrast", "igor")
            }
        }

        part(title = "Conclusion") {
            //            slideFromResource(title = "Android") { styleClass = setOf("contrast", "manu") }
//            slideFromResource(title = "Serveur") { styleClass = setOf("contrast", "igor") }
//            slideFromResource(title = "Web et Natif", contentType = HTML) {
//                styleClass = setOf("contrast", "igor", "manu")
//            }
            slideFromResource(title = "Bilan", key = "bilan-other") {
                styleClass = setOf("details", "contrast", "igor")
            }
            slide(title = "Tendance", styleClass = setOf("contrast", "trends", "igor", "manu")) {
                svg("/deepDiveKotlin/conclusion/trends.svg")
                linkText("https://insights.stackoverflow.com/trends?tags=kotlin%2Cscala%2Cgroovy%2Cclojure") {
                    "Stackoverflow insights"
                }
            }
            slideFromResource(title = "Kotlin vs Java", key = "kotlin_vs_java") {
                styleClass = setOf("contrast", "manu")
            }
            slideFromResource(title = "Liens", key = "Liens_jug") { styleClass = setOf("contrast", "manu", "igor", "two-columns") }
            slideFromResource(title = "Liens présentation", key = "liens_presentation_jug") {
                styleClass = setOf("contrast", "manu", "igor")
            }
            slideFromResource(title = "Bibliothèques") { styleClass = setOf("contrast", "manu", "igor") }
            slideFromResource(title = "Merci", key = "merci-jug") { styleClass = setOf("contrast", "manu", "igor") }
        }
    }
