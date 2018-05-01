const factorial = (n: number): number => {
    const aux = (n: number, acc: number): number =>
        (n < 2) ? acc : aux(n - 1, acc * n);
    return aux(1, n);
};