package _05_class_1

class Point(x: Int, y: Int) {
    val x = x
    val y = x
}


class Point2(val x: Int, var y: Int)


class Point3(val x: Int, val y: Int) {
    constructor(pair: Pair<Int, Int>) : this(pair.first, pair.second)
}

class Point4(val x: Int, val y: Int) {
    constructor(pair: Pair<Int, Int>) : this(pair.first, pair.second)

    init {
        println("($x, $y)")
    }
}


fun main(args: Array<String>) {
    val p1 = Point(2, 4)
    val p2 = Point2(2, 4)

    val p3 = Point3(Pair(2, 4))

    val p4 = Point3(Pair(2, 4))
}
