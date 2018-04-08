class Point4(val x: Int, val y: Int) {
    constructor(pair: Pair<Int, Int>) : this(pair.first, pair.second)

    init {
        println("($x, $y)")
    }
}

// val p4 = Point4(Pair(2, 4))