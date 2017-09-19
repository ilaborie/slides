package css_awesome

import org.ilaborie.slides.Group
import org.ilaborie.slides.content.CssCompatibility
import org.ilaborie.slides.content.Link
import org.ilaborie.slides.content.UnorderedList

fun animations(group: Group) = group
        .slide(title = "Texte de chargement", styleClass = setOf("live-code")) {
            cssLiveCode("/cssIsAwesome/05_animations/loader")
        }
        .slide(title = "Dessiner", styleClass = setOf("live-code")) {
            cssLiveCode("/cssIsAwesome/05_animations/draw")
        }
        .slide(title = "Bilan animations") {
            UnorderedList(
                    Link("Utiliser les animations CSS", "https://developer.mozilla.org/fr/docs/Web/CSS/Animations_CSS/Utiliser_les_animations_CSS"),
                    Link("Text Spinner", "http://tawian.io/text-spinners/"),
                    Link("CSS only loader", "https://www.pexels.com/blog/css-only-loaders/"),
                    Link("Animate.css", "https://daneden.github.io/animate.css/"),
                    Link("How SVG Line Animation Works", "https://css-tricks.com/svg-line-animation-works/"),
                    Link("Animated line drawing in SVG", "https://jakearchibald.com/2013/animated-line-drawing-svg/")
//                    Link("<code>&lt;progress&gt;</code>".html(), "https://developer.mozilla.org/fr/docs/Web/HTML/Element/Progress")
            )
        }
        .slide(title = "Compatibilité", id = "compat-5") {
            CssCompatibility(browsersThreshold, listOf("css-animation", "svg"))
        }