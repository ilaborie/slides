const factorial = (n) => {
    const aux = (n, acc) =>
        (n < 2) ? acc : aux(n - 1, acc * n);
    return aux(n, 1);
};