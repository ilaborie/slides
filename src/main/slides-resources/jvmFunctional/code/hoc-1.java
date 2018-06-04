var digits = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

IntPredicate isEven = n -> (n % 2 == 0);

List<Integer> evenDigits = digits.stream()
        .mapToInt(i -> i)
        .filter(isEven)
        .boxed()
        .collect(Collectors.toList());