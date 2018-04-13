fun nullsafety() {

    val somethingNotNull: String = "aString"
    // somethingNotNull: String = null => compilation error

    var length = somethingNotNull.length

    var something : String? = null
    length = something?.length ?: 0

    length = something()?.length ?: 0

    // SmartCast
    something = "aString"
    length = something.length

    fun sumf (x:Int, y:Int) = x + y

    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
}

fun something():String? = null
