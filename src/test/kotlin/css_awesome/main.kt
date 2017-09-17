package css_awesome

import org.ilaborie.logger.Logger
import org.ilaborie.slides.*
import org.ilaborie.slides.content.*
import java.io.File


val browsersThreshold = .5

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
            .group("Compatibilité des navigateurs", "compat") { compat(this) }
            .group("Conclusion") { conclusion(this) }

    val slidesDir = File("src/test/resources/")

    if (cssIsAwesome.hasMissingExternals()) {
        logger.info { "Generate missing slides" }
        cssIsAwesome.generateMissingExternals(slidesDir)
    }

    val dist = File("src/main/web")
    val key = "devfest-tls"
    cssIsAwesome.writeHtmlTo(dist, key)
    cssIsAwesome.writeMarkdownTo(dist, key)
    cssIsAwesome.writePdfTo(dist.resolve("$key.html"), dist.resolve("$key.pdf"))

    val holyGrail = slidesDir.resolve(cssIsAwesome.id).resolve("holy-grail.html")
    holyGrail.copyTo(target = dist.resolve("holy-grail-calc.html"), overwrite = true)
    holyGrail.copyTo(target = dist.resolve("holy-grail-flexbox.html"), overwrite = true)
    holyGrail.copyTo(target = dist.resolve("holy-grail-grid.html"), overwrite = true)
}