package demo


import org.ilaborie.logger.Logger
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.dsl.*
import org.ilaborie.slides.generateMissingExternals
import org.ilaborie.slides.hasMissingExternals
import java.io.File


fun main(args: Array<String>) {
    val logger = Logger("Demo")

    val demoPresentation = demo()
    val slidesDir = File("src/main/slides-resources/")

    if (demoPresentation.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        demoPresentation.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web/")
    demoPresentation.buildAll(dist, "gdg")
}

fun demo() =
    presentation(title = "demo", key = "demo") {
        addScript("../scripts/navigation.js")
        addScript("../scripts/monaco-editor/min/vs/loader.js")
        addScript("../scripts/code-editor.js")

        part(title = "Component") {
            roadmap()
        }
        part(title = "Code Editor") {
            slide("Hello World") {
                codeEditorFromResoures("/demo/plop.ts", "/demo/plop-final.ts")
            }
        }
    }