const digits = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];



function isEvenOrOdd(even: boolean) {
    const isEven = (n: number): boolean => (n % 2 === 0);
    return (n: number) => even ? isEven(n) : !isEven(n);
}

const evenDigits = digits.filter(isEvenOrOdd(true));

const oddSquared = digits
    .filter(isEvenOrOdd(false))
    .map(n => n ** 2);

console.log(evenDigits);
console.log(oddSquared);
