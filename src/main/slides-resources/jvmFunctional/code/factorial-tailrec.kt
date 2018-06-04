tailrec fun factorialTailRec(n: Int): Int {
    fun aux(acc: Int, n: Int): Int =
        if (n <= 1) acc else aux(acc * n, n - 1)
    return aux(1, n)
}