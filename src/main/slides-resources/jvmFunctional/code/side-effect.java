class Test{
    int sum = 0;

    public void compute() {
        var arr = List.of(1, 2, 3, 4, 5);
        for (int i : arr) {
            sum += i;
        }
        System.out.println(sum);
    }
}