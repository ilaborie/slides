package deep_dive_kotlin

import mu.KotlinLogging
import org.ilaborie.slides.ContentType.HTML
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.dsl.*
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
    part(title = "Qu'est-ce que le ByteCode Java ?", key = "bytecode") {

        slideFromResource(title = "Java le langage")
        slideFromResource(title = "Compilons")
        slideFromResource(title = "Analysons")
        slideFromResource(title = "Décompilons")
        slideFromResource(title = "Bilan")
    }
    part(title = "Introduction Kotlin") {
        slideFromResource(title = "Historique", contentType = HTML)
        slideFromResource(title = "Cible")

        slide(title = "HelloWorld") {
            codeFromResource("/deepDiveKotlin/src/kotlin/_00_helloworld/HelloWorld.kt")
        }
        slide(title = "HelloWorld-dump") {
            codeFromResource("/deepDiveKotlin/target/kotlin/_00_helloworld/HelloWorldKt.class.hex")
        }
        slide(title = "HelloWorld-bytecode") {
            codeFromResource("/deepDiveKotlin/target/kotlin/_00_helloworld/HelloWorldKt.class.txt")
        }
        slide(title = "HelloWorld-java") {
            codeFromResource("/deepDiveKotlin/target/kotlin/_00_helloworld/HelloWorldKt.java")
        }
        slideFromResource(title = "Intrinsics")
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
    part(title = "Extension de fonction") {
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
