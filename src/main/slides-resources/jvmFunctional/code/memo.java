public static int fibonacci(int n) {
    switch (n) {
        case 1 return 1;
        case 2: return 1;
        default:
            return fibonacci(n - 2) + fibonacci(n - 1);
    }
}

public static IntUnaryOperator stupidMemoizer(IntUnaryOperator func) {
    Map<Integer, Integer> cache = new HashMap<>();
    return n -> cache.computeIfAbsent(n, func::applyAsInt);
}

public static void main(String[] args) {
    var fibo = stupidMemoizer(Memo::fibonacci);
    System.out.println(fibo.applyAsInt(15));
}