public static int factorialStream(int n) {
    return IntStream.rangeClosed(1, n)
            .reduce(1, (acc, i) -> acc * i);
}