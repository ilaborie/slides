package deep_dive_kotlin

import mu.KotlinLogging
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.buildAll
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

fun prezDevoxxFr2018() = presentation(title = "Deep Dive Kotlin: du Hello World au ByteCode", key = "deepDiveKotlin") {
    addScript("../scripts/navigation.js")

    part(title = "Introduction") {
        skipHeader = true
        slideFromResource(title = "Speakers", key = "speakers", contentType = HTML)
        roadmap(title = "🗺 Roadmap")
    }
    part(title = "ByteCode Java ?", key = "bytecode") {
        slide(title = "HelloWorld.java", key = "hw-java", styleClass = setOf("code", "java")) {
            codeFromResource("/deepDiveKotlin/src/java/_00_helloworld/HelloWorld.java")
            bash { "javac HelloWorld.java" }
        }
        slide(title = "Java ByteCode binary", styleClass = setOf("code", "hex")) {
            bash { "hexdump -C HelloWorld.class" }
            codeFromResource("/deepDiveKotlin/target/java/_00_helloworld/HelloWorld.class.hex")
        }
        slide(title = "Explorons le ByteCode", styleClass = setOf("code", "bytecode")) {
            bash { "javap -c -v HelloWorld.class" }
            codeFromResource("/deepDiveKotlin/target/java/_00_helloworld/HelloWorld.class.txt")
        }
        slide(title = "Transpile", styleClass = setOf("diagram")) {
            svg("/deepDiveKotlin/bytecode/javac.svg")
        }
        slide(title = "ByteCode Inter", styleClass = setOf("diagram")) {
            svg("/deepDiveKotlin/bytecode/javac.svg")
        }
        slideFromResource(title = "Liens", key="bytecode-links")
    }

    part(title = "Introduction Kotlin") {
        slideFromResource(title = "Historique", contentType = HTML)
        slideFromResource(title = "Cibles", contentType = HTML)

        slide(title = "HelloWorld.kt", key = "hw-kotlin", styleClass = setOf("code", "kotlin")) {
            codeFromResource("/deepDiveKotlin/src/kotlin/_00_helloworld/HelloWorld.kt")
            bash { "kotlinc HelloWorld.kt" }
        }
        slide(title = "hexdump", styleClass = setOf("code", "hex")) {
            codeFromResource("/deepDiveKotlin/target/kotlin/_00_helloworld/HelloWorldKt.class.hex")
        }
        slide(title = "Java ByteCode", styleClass = setOf("code", "bytecode")) {
            codeFromResource("/deepDiveKotlin/target/kotlin/_00_helloworld/HelloWorldKt.class.txt")
        }
        slide(title = "HelloWorld-java", styleClass = setOf("code", "java")) {
            codeFromResource("/deepDiveKotlin/target/kotlin/_00_helloworld/HelloWorldKt.java")
        }
        slideFromResource(title = "Bilan HelloWorld.kt")
        slideFromResource(title = "Performance HelloWorld.kt")
    }

    part(title = "Les bases", key = "basic") {
        slideFromResource(title = "val/var, Number, String")
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
