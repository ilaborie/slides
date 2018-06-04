def factorialTailRec(n: Int): Int = {
    @tailrec
    def aux(acc: Int, n: Int): Int =
      if (n <= 1) acc else aux(acc * n, n - 1)

    aux(1, n)
  }