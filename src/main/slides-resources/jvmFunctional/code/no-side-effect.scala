val sum = List(1, 2, 3, 4, 5)
    .foldLeft(0) { (acc, i) => acc + i } // or .sum
println(sum)