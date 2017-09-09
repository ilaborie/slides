package css_awesome

import org.ilaborie.slides.Group
import org.ilaborie.slides.content.*

fun unites(group: Group) = group
        .slide(title = "Une histoire d’unités CSS") {
            Figure("Une histoire d’unités CSS".raw(),
                   ExternalLink("https://www.commitstrip.com/wp-content/uploads/2016/10/Strip-High-Level-CSS-650-final-2.jpg"),
                   Link("CommitStrip", "http://www.commitstrip.com/fr/2016/10/10/a-story-about-css-units/"))
        }
        .slide(title = "Les unités de longueur") {
            Definitions(
                    "px, cm, pt, ..." to "longueurs absolues (mesure physique)".html(),
                    "em, rem" to "fonction de la <code>font-size</code>".html(),
                    "ex, ch" to "hauteur d'un <code>x</code>, largeur d'un <code>0</code>".html(),
                    "vh, vw" to "(100vh, 100vw) = (hauteur, largeur) du <i>viewport</i>".html(),
                    "vmin, vmax" to "min(1vh, 1vw), max(1vh, 1vw)".raw())
        }
        .slide(title = "Holy Grail avec <code>calc</code>".html(), id = "holy-grail-calc") {
            Block(
                    ExternalHtmlContent(ExternalResource("/cssIsAwesome/02_unites/holy_grail-inner.html")) +
                            ExternalCodeContent(Language.HTML, ExternalResource("/cssIsAwesome/02_unites/holy_grail.html")))
        }
        .slide(title = "Bilan unités") {
            UnorderedList(
                    Link("Unités", "https://developer.mozilla.org/fr/docs/Web/CSS/length) et [Truc et astuces](https://www.w3.org/Style/Examples/007/units.fr.html"),
                    Link(Code(code = "calc"), "https://developer.mozilla.org/fr/docs/Web/CSS/calc")
            )
        }