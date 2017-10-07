package css_awesome

import org.ilaborie.logger.Logger
import org.ilaborie.slides.Presentation
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.content.ExternalHtmlContent
import org.ilaborie.slides.content.ExternalResource
import org.ilaborie.slides.content.html
import org.ilaborie.slides.content.web.EditableZone
import org.ilaborie.slides.content.web.StyleEditable
import org.ilaborie.slides.generateMissingExternals
import org.ilaborie.slides.hasMissingExternals
import java.io.File


val browsersThreshold = 0.4

fun cssLiveCode(prefix: String) =
    StyleEditable(ExternalResource("$prefix.css"), ExternalResource("$prefix-final.css")) +
            EditableZone(ExternalHtmlContent(ExternalResource("$prefix.html")))

fun main(args: Array<String>) {
    val logger = Logger("CSS")

    val title = "C<span class=\"logo-askew\">S</span>S is awesome!".html()
    val cssIsAwesome = Presentation(title = title, id = "cssIsAwesome")
        .group("Introduction", skipPart = true) { intro(this) }
        .group("Utiliser un pré&#8209;processeur ?", "preprocessor") { preprocessor(this) }
        .group("Unités") { unites(this) }
        .group("Flexbox et Grid") { flexgrid(this) }
        .group("Pseudo éléments") { pseudoElt(this) }
        .group("Animations") { animations(this) }
        .group("Pseudo classes d'état", "pseudo_classes") { pseudoState(this) }
        .group("HTML") { html(this) }
        .group("Conclusion") { conclusion(this) }

    val slidesDir = File("src/test/resources/")

    if (cssIsAwesome.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        cssIsAwesome.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web")
    cssIsAwesome.buildAll(dist, "devfest-tls")
    cssIsAwesome.buildAll(dist, "devoxx-ma")

    copyExtraFiles(slidesDir.resolve(cssIsAwesome.id), dist.resolve(cssIsAwesome.id))
}


private fun copyExtraFiles(srcDir: File, destDir: File) {
    listOf("holy-grail.html", "holy-grail-calc.html", "holy-grail-flexbox.html", "holy-grail-grid.html")
        .map { it to srcDir.resolve(it) }
        .map { (fileName, srcDir) -> srcDir.copyTo(target = destDir.resolve(fileName), overwrite = true) }
}


