package css_awesome

import org.ilaborie.slides.BasicSlide
import org.ilaborie.slides.Presentation
import org.ilaborie.slides.buildAll
import org.ilaborie.slides.content.*
import org.ilaborie.slides.content.web.EditableZone
import org.ilaborie.slides.content.web.StyleEditable
import java.io.File


val browsersThreshold = 0.5

fun cssLiveCode(prefix: String) =
    StyleEditable(ExternalResource("$prefix.css"), ExternalResource("$prefix-final.css")) +
            EditableZone(ExternalHtmlContent(ExternalResource("$prefix.html")))

fun main(args: Array<String>) {

    val slidesDir = File("src/test/resources/")
    val dist = File("src/main/web")

    val title = "C<span class=\"logo-askew\">S</span>S is awesome!".html()

    // Devfest Toulouse
    val cssIsAwesome = Presentation(title = title, id = "cssIsAwesome")
        .group("Introduction", skipPart = true) { intro(this) }
        .group("Utiliser un pré&#8209;processeur ?", "preprocessor") { preprocessor("FR", this) }
        .group("Unités") { unites("FR", this) }
        .group("Flexbox et Grid") { flexgrid("FR", this) }
        .group("Pseudo éléments") { pseudoElt("FR", this) }
        .group("Animations") { animations("FR", this) }
        .group("Pseudo classes d'état", "pseudo_classes") { pseudoState("FR", this) }
        .group("HTML") { html("FR", this) }
        .group("Conclusion") { conclusion(this) }
//    cssIsAwesome
//        .buildAll(dist, "devfest-tls")

    // Devoxx Maroc
    Presentation(title = title, id = "cssIsAwesome")
        .group("Introduction", skipPart = true) { intro(this)
            .removeSlide("omit")
        }
        .group("Utiliser un pré&#8209;processeur ?", "preprocessor") { preprocessor("MA", this) }
        .group("Unités") { unites("MA", this) }
        .group("Flexbox et Grid") { flexgrid("MA", this) }
        .group("Pseudo éléments") { pseudoElt("MA", this) }
        .group("Animations") { animations("MA", this)
            .removeSlide("texte_de_chargement") }
        .group("Pseudo classes d'état", "pseudo_classes") { pseudoState("MA", this) }
        .group("Conclusion") { conclusion(this) }
        .replaceSlide("liens", BasicSlide(id = "Liens",
                                          content = UnorderedList(
                                                  Link("les slides en HTML", "https://ilaborie.github.io/slides/devoxx-ma.html#cssIsAwesome"),
                                                  Link("les slides en PDF", "https://ilaborie.github.io/slides/devoxx-ma.pdf"),
                                                  Link("le code", "https://github.com/ilaborie/slides"),
                                                  Link("Blog: 'Making Of'", "http://www.monkeypatch.io/2017/05/02/MakingOf_CSS_is_Awesome.html"))))
        .buildAll(dist, "devoxx-ma")

    copyExtraFiles(slidesDir.resolve(cssIsAwesome.id), dist.resolve(cssIsAwesome.id))
}


private fun copyExtraFiles(srcDir: File, destDir: File) {
    listOf("holy-grail.html", "holy-grail-calc.html", "holy-grail-flexbox.html", "holy-grail-grid.html")
        .map { it to srcDir.resolve(it) }
        .map { (fileName, srcDir) -> srcDir.copyTo(target = destDir.resolve(fileName), overwrite = true) }
}


