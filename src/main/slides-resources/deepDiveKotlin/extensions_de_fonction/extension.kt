val AstronomicalBody.size: Int
    get() = name.length

fun AstronomicalBody.display() = "Planet $name $size"

fun main(args: Array<String>) {
    SolarSystem.bodies
        .forEach { println(it.display()) }
}
