package css_awesome

import org.ilaborie.slides.Group
import org.ilaborie.slides.content.*

fun flexgrid(group: Group) = group
        .slide(title = "Avec flexbox & Grid", styleClass = setOf("hide-title")) {
            ExternalCodeContent(Language.HTML, ExternalResource("/cssIsAwesome/03_flexbox_et_grid/holy_grail.html")) +
                    Link("Exemple Flexbox", "./holy-grail-flexbox.html") +
                    Link("Exemple Grid", "./holy-grail-grid.html")
        }
        .slide(title = "Bilan Flexbox & Grid") {
            Block(
                    "Flexbbox".h4() +
                            UnorderedList(
                                    "Si on est sur une seule ligne ou colonne".raw(),
                                    Link("Flexbox, et le CSS redevient fun ! (Hubert SABLONNIÈRE)", "https://www.youtube.com/watch?v=5F_ngjHDcJQ"),
                                    Link("Solved by Flexbox", "https://philipwalton.github.io/solved-by-flexbox/"),
                                    Link("Flexbox Froggy", "https://flexboxfroggy.com/")
                            )) +
                    Block(
                            "Grid".h4() +
                                    UnorderedList(
                                            "Si plusieurs lignes et colonnes".raw(),
                                            Link("Grid by exemples", "https://gridbyexample.com/"),
                                            Link("Grid Garden", "http://cssgridgarden.com/")
                                    ))
        }