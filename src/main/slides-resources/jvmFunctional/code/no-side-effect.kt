val sum = listOf(1, 2, 3, 4, 5)
    .fold(0) { acc, i -> acc + i } // or .sum()
println(sum)