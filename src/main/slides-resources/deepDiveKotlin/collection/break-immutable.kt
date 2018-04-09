fun main(args: Array<String>) {
    val moons = (1..9).map { Moon("Moon #$it") }.toList()

    println(moons.javaClass)

    moons.javaClass.methods
        .find { it.name == "add" && it.parameterCount == 1 }
        ?.invoke(moons, Moon("XXX"))

    println(moons.joinToString("\n"))
}