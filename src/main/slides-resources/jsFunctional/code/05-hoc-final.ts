const digits = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
type Predicate<T> = (T) => boolean;


const isEven = (n: number): boolean => (n % 2 === 0);

const not = <T>(fun: Predicate<T>): Predicate<T> =>
    (n: T) => !fun(n);

const isOdd = not(isEven);


const evenDigits = digits.filter(isEven);

const oddSquared = digits
    .filter(isOdd)
    .map(n => n ** 2);

console.log(evenDigits);
console.log(oddSquared);
