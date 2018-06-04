public static int factorialRec(int n) {
    return (n <= 1) ? 1 : n * factorialRec(n - 1);
}