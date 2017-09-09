package css_awesome

import org.ilaborie.slides.Group
import org.ilaborie.slides.content.Link
import org.ilaborie.slides.content.UnorderedList
import org.ilaborie.slides.content.html

fun animations(group: Group) = group
        .slide(title = "Texte de chargement") {
            cssLiveCode("/cssIsAwesome/05_animations/loader")
        }
        .slide(title = "Dessiner") {
            cssLiveCode("/cssIsAwesome/05_animations/draw")
        }
        .slide(title = "Bilan animations") {
            UnorderedList(
                    Link("Utiliser les animations CSS", "https://developer.mozilla.org/fr/docs/Web/CSS/Animations_CSS/Utiliser_les_animations_CSS"),
                    Link("Text Spinner", "http://tawian.io/text-spinners/"),
                    Link("CSS only loader", "https://www.pexels.com/blog/css-only-loaders/"),
                    Link("Animate.css", "https://daneden.github.io/animate.css/"),
                    Link("How SVG Line Animation Works", "https://css-tricks.com/svg-line-animation-works/"),
                    Link("<code>&lt;progress&gt;</code>".html(), "https://developer.mozilla.org/fr/docs/Web/HTML/Element/Progress")
            )
        }