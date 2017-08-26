@file:JvmName("Main")

package org.ilaborie.slides


fun main(args: Array<String>) {

    val titleLeatPower = Link("The Rule of Least Power", "https://www.w3.org/2001/tag/doc/leastPower.html")
    val cssIsAwesome = Presentation(
            "CSS is Awesome !",
            Group("Introduction",
                  BasicSlide(title = Code(code = "$ whoami"), id = "whoami"),
                  BasicSlide(title = titleLeatPower, id = "least-power"),
                  BasicSlide(title = "Règles du jeu"),
                  BasicSlide(title = "Le CSS c'est vaste"),
                  BasicSlide(title = "Plan")),
            Group("Utiliser un pré-processeur ?",
                  BasicSlide(title = "LiveCoding: boutons", styleClass = setOf("hide-title")),
                  BasicSlide(title = "Alors on utilise un pré-processeurs ?")),
            Group("Unités",
                  BasicSlide(title = "Une histoire d’unités CSS"),
                  BasicSlide(title = "Les unités de longueur"),
                  BasicSlide(title = "LiveCoding: Holy Grail Layout avec calc", styleClass = setOf("hide-title")),
                  BasicSlide(title = "Bilan unités")),
            Group("Flexbox et Grid",
                  BasicSlide(title = "LiveCoding: Holy Grail Layout avec flexbox", styleClass = setOf("hide-title")),
                  BasicSlide(title = "LiveCoding: Holy Grail Layout avec grid", styleClass = setOf("hide-title")),
                  BasicSlide(title = "Bilan Flexbox & Grid")),
            Group("Pseudo éléments",
                  BasicSlide(title = "LiveCoding: le dinner d'un philosophe", styleClass = setOf("hide-title")),
                  BasicSlide(title = "LiveCoding: Triangle avec des bordures", styleClass = setOf("hide-title")),
                  BasicSlide(title = "LiveCoding: Info-bulle", styleClass = setOf("hide-title")),
                  BasicSlide(title = "Bilan pseudo éléments")),
            Group("Animations",
                  BasicSlide(title = "LiveCoding: texte de chargement", styleClass = setOf("hide-title")),
                  BasicSlide(title = "Bilan animations")),
            Group("Pseudo classes d'état",
                  BasicSlide(title = "Usage des info-bulles"),
                  BasicSlide(title = "LiveCoding: Checkbox", styleClass = setOf("hide-title")),
                  BasicSlide(title = "LiveCoding: Switch", styleClass = setOf("hide-title")),
                  BasicSlide(title = "LiveCoding: Collapsible panel", styleClass = setOf("hide-title")),
                  BasicSlide(title = "Principe pour les onglets"),
                  BasicSlide(title = "Démo des onglets"),
                  BasicSlide(title = "Bilan Pseudo classes")),
            Group("Compatibilité des navigateurs",
                  BasicSlide(title = "Partie 1", styleClass = setOf("hide-title")),
                  BasicSlide(title = "Partie 2", styleClass = setOf("hide-title"))),
            Group("Conclusion",
                  BasicSlide(title = "Bilan", styleClass = setOf("hide-title")),
                  BasicSlide(title = "Traitez le CSS comme du code"),
                  BasicSlide(title = "Liens"),
                  BasicSlide(title = "Pour apprendre"),
                  BasicSlide(title = "🦄 rocks !"))
    )

    println(cssIsAwesome.renderAsMarkdown())
}