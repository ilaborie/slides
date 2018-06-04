IntPredicate isEven = n -> {
    if (n % 2 == 0) {
        return true;
    } else {
        return false;
    }
};

List.of(1, 2, 3, 4, 5)
    .forEach(i ->
        System.out.println("" + i + " is event? " + isEven.apply(i))
    );