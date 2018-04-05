package _03_fun

fun buildString2(prefix: String = "Hello", who: String, enhanced: Boolean = true): String {
    var msg = "$prefix $who"
    if (enhanced) {
        msg += '!'
    }
    return msg
}

fun greetings2(): String =
    buildString(enhanced = true, who = "Devoxx", prefix = "Hello")