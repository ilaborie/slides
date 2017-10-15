package css_awesome

import org.ilaborie.slides.Group
import org.ilaborie.slides.content.*
import org.ilaborie.slides.content.Language.*
import org.ilaborie.slides.content.web.CssCompatibility

fun flexgrid(country:String, group: Group) = group
        .slide(title = "Holy Grail avec <code>flexbox</code>".html(), id = "holy-grail-flexbox") {
                    ExternalHtmlContent(ExternalResource("/cssIsAwesome/02_unites/holy_grail-inner.html")) +
                            ExternalCodeContent(HTML, holyGrail)
        }
        .slide(title = "Holy Grail avec <code>grid</code>".html(), id = "holy-grail-grid") {
                    ExternalHtmlContent(ExternalResource("/cssIsAwesome/02_unites/holy_grail-inner.html")) +
                            ExternalCodeContent(HTML, holyGrail)
        }
        .slide(title = "Bilan Flexbox & Grid") {
            Block(
                    "Flexbox".h4() +
                            UnorderedList(
                                    Link("Flexbox, et le CSS redevient fun ! (Hubert SABLONNIÈRE)", "https://www.youtube.com/watch?v=5F_ngjHDcJQ"),
                                    Link("Solved by Flexbox", "https://philipwalton.github.io/solved-by-flexbox/"),
                                    Link("Flexbox Froggy", "https://flexboxfroggy.com/")
                            )) +
                    Block(
                            "Grid".h4() +
                                    UnorderedList(
                                            Link("<code>@supports</code>".html(), "https://developer.mozilla.org/fr/docs/Web/CSS/@supports"),
                                            Link("Grid by examples", "https://gridbyexample.com/"),
                                            Link("CSS Grid Changes Everything (About Web Layouts) by Morten Rand-Hendriksen", "https://www.youtube.com/watch?v=txZq7Laz7_4"),
                                            Link("Grid Garden", "http://cssgridgarden.com/")
                                    ))
        }
        .slide(title = "Compatibilité", id = "compat-3") {
            CssCompatibility(country, browsersThreshold, listOf("flexbox", "css-grid", "css-featurequeries"))
        }