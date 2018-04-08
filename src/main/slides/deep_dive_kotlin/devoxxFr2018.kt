package deep_dive_kotlin

import mu.KotlinLogging
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.content.HtmlContent
import org.ilaborie.slides.dsl.bash
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

fun prezDevoxxFr2018() = presentation(title = HtmlContent("Deep Dive Kotlin :<br/> du Hello World au ByteCode"), key = "deepDiveKotlin") {
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
        slideFromResource(title = "À propos du ByteCode", key = "bytecode-details")  { styleClass = setOf("details", "contrast") }
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
        slideFromResource(title = "Bilan HelloWorld.kt")  { styleClass = setOf("bilan", "contrast") }
        slideFromResource(title = "Performances ?", key = "performance") { styleClass = setOf("measure", "contrast") }
        slideFromResource(title = "Performance HelloWorld.kt")  { styleClass = setOf("bilan", "contrast") }
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
        slide(title = "ByteCode string-template", styleClass = setOf("code", "bytecode")) {
            codeFromResource("/deepDiveKotlin/basic/String_templatesKt.class.txt")
        }
        slide(title = "numeric.kt", styleClass = setOf("code", "kotlin")) {
            codeFromResource("/deepDiveKotlin/basic/numeric.kt")
        }
        slide(title = "numeric.java", styleClass = setOf("code", "java")) {
            codeFromResource("/deepDiveKotlin/basic/NumericKt.java")
        }
        slideFromResource(title = "C'est simple", key = "basic-bilan") { styleClass = setOf("bilan", "contrast") }
    }

    part(title = "null-safety") {
        slideFromResource(title = "elvis")
    }

    part(title = "Fonction") {
        slideFromResource(title = "function")
    }
    part(title = "Lambda") {
        slideFromResource(title = "Lambda")
    }
    part(title = "Class") {
        slideFromResource(title = "Class")
    }
    part(title = "Types") {
        slideFromResource(title = "Hierarchie des types")
    }
    part(title = "Extensions de fonction") {
        slideFromResource(title = "Extension")
    }
    part(title = "Structure") {
        slideFromResource(title = "Structures")
    }

    part(title = "Pause") {}

    part(title = "ByteCode Android", key = "android") {
        slideFromResource(title = "Android ByteCode")
    }

    part(title = "Collection") {
        slideFromResource(title = "Collection")
    }
    part(title = "Delegate") {
        slideFromResource(title = "Delegate")
    }
    part(title = "Plus sur les fonctions") {
        slideFromResource(title = "Plus sur les fonctions")
    }
    part(title = "Serialization") {
        slideFromResource(title = "serialisation")
    }
    part(title = "Coroutines") {
        slideFromResource(title = "Coroutines")
    }
    part(title = "DSL") {
        slideFromResource(title = "DSL")
    }

    part(title = "Conclusion") {
        slideFromResource(title = "Kotlin vs Java")
        slideFromResource(title = "Liens")
    }
}
