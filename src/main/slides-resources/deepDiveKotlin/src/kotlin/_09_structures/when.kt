package _09_structures

import astronomy.Planet
import astronomy.SolarSystem
import astronomy.Star


fun main(args: Array<String>) {

    for (body in SolarSystem.bodies) {

        val message = when (body) {
            is Planet -> "Planet ${body.name}"
            is Star   -> "Star ${body.name}"
            else      -> null
        }

        if (message != null) {
            println(message)
        }
    }
}