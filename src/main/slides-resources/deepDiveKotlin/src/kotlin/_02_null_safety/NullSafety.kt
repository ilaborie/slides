package `_02_null_safety`


fun nullsafety(){

    val somethingNotNull: String = "aString"
    // somethingNotNull: String = null => compilation error

    var length = somethingNotNull.length

    var something : String? = null
    length = something?.length ?: 0
    // Optimisation : something?.length

    length = something()?.length ?: 0


    // SmartCast
    something = "aString"
    length = something.length

}

fun something():String? = null

