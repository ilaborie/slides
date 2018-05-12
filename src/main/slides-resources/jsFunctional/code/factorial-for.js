const factorial = (n) => {
    let acc = 1;
    for (let i = 2; i <= n; i++) { // 🤢
        acc *= i;
    }
    return acc;
};