fun main(args: Array<String>) {

    val earthMoon = listOf(Moon("moon"))
    val add = earthMoon + Moon("moon 2")

    println("earthMoon: $earthMoon")
    println("add: $add")
    println("reference equality: ${earthMoon === add}")

    println("\n")
    val earthMoon2 = mutableListOf(Moon("moon"))
    val add2 = earthMoon2.add(Moon("moon 2"))

    println("earthMoon2: $earthMoon2")
    println("add2: $add2")
}

