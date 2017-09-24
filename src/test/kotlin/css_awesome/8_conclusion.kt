package css_awesome

import org.ilaborie.slides.Group
import org.ilaborie.slides.content.*

fun conclusion(group: Group) = group
        .slide(title = "Bilan") {
            listOf(
                    "Utilisez du CSS pour simpifier le code",
                    "Utilisez intelligemment les pre/post&#8209;processeurs",
                    "HTML, SVG are Awesome !",
                    "JavaScript, TypeScript could be Awesome !").ol()
        }
        .slide(title = "Traitez le CSS comme du code", styleClass = setOf("hide-title")) {
            "👉 Traitez le CSS comme du code".h3() +
                    listOf(
                            "Revue de code",
                            "DRY",
                            "Clean Code",
                            "Single Responsibility Principle",
                            "...").ol()
        }
        .slide(title = "Liens") {
            UnorderedList(
                    Link("les slides en HTML", "https://ilaborie.github.io/slides/devfest-tls.html#cssIsAwesome"),
                    Link("les slides en PDF", "https://ilaborie.github.io/slides/devfest-tls.pdf"),
                    Link("le code", "https://github.com/ilaborie/slides"),
                    Link("Blog: 'Making Of'", "http://www.monkeypatch.io/2017/05/02/MakingOf_CSS_is_Awesome.html"))
        }
        .slide(title = "Pour apprendre") {
            UnorderedList(
                    Code("(Ctrl|⌘) + Shift + i"),
                    ExternalImageContent("CSS Secret", ExternalLink("http://lea.verou.me/cover.png")) +
                            Link("CSS Secret by Lea Verou", "https://www.amazon.fr/CSS-Secrets-Lea-Verou/dp/1449372635"),
                    Link("CSS sur MDN", "https://developer.mozilla.org/fr/docs/Web/CSS") + ", ".raw() +
                            Link("The A11Y Project", "http://a11yproject.com/"),
                    Link("CodePen", "https://codepen.io/") + ", ".raw() +
                            Link("JSFiddle", "https://jsfiddle.net/") + ", ".raw() +
                            Link("Dabblet", "http://dabblet.com/") + ",...".raw(),
                    Link("CSS Tricks", "https://css-tricks.com/") + ", ".raw() +
                            Link("Smashing Magazine", "https://www.smashingmagazine.com/"),
                    Link("CSS Flags", "https://pixelastic.github.io/css-flags/") + ",".raw() +
                            Link("A Single Div", "http://a.singlediv.com/")
            )
        }
        .slide(title = "🦄 rocks !", styleClass = setOf("hide-title")) {
            ExternalHtmlContent(ExternalResource("/cssIsAwesome/08_conclusion/end.html"))
        }