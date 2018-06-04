int sum = List.of(1, 2, 3, 4, 5)
    .stream() // Stream<Integer>
    .reduce(0, (acc, i) -> acc + i);
System.out.println(sum);