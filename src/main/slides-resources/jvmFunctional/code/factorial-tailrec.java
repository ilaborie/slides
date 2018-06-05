public static int factorialTailRec(int n) {
    return factorialTailRecAux(1, n);
}
private static int factorialTailRecAux(int acc, int n) {
    return (n <= 1) ? acc : factorialTailRecAux(acc * n, n - 1);
}