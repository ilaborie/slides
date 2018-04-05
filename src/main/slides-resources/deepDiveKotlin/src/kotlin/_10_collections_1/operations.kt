package _10_collections_1

import astronomy.Planet
import astronomy.SolarSystem


fun main(args: Array<String>) {

    val s = SolarSystem.bodies
        .filterIsInstance<Planet>()
        .flatMap { planet -> planet.moons }
        .filterNot { it.name.startsWith("S/") }
        .sortedBy { it.name }
//        .fold("") { acc, moon ->
//            (if (acc == "") "" else "$acc,\n") + moon.name
//        }
        .joinToString(",\n") { it.name }

    println(s)
}