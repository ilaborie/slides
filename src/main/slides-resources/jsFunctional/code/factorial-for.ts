const factorial = (n: number): number => {
    let acc = 1;
    for (let i = 2; i <= n; i++) { // 🤢
        acc *= i;
    }
    return acc;
};