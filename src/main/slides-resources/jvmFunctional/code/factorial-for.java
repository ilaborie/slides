public static int factorialFor(int n) {
    int acc = 1;
    for (int i = 2; i <= n; i++) {
        acc *= i;
    }
    return acc;
}