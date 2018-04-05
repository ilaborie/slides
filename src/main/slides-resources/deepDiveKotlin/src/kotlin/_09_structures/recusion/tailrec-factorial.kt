package _09_structures.recusion

// Note: assert(n >= 0)
fun tailRecFactorial(n: Int): Int {
    tailrec fun aux(n: Int, acc: Int): Int =
        if (n < 1) 1 else aux(n - 1, acc * n)
    return aux(n, 1)
}


