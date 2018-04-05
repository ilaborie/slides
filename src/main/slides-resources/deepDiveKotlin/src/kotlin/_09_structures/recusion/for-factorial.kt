package _09_structures.recusion

// Note: assert(n >= 0)
fun forFactorial(n: Int): Int {
    var acc = 1
    for (i in 1..n) {
        acc *= i
    }
    return acc
}


