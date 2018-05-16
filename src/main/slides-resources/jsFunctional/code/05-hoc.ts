const digits = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
type Predicate<T> = (T) => boolean;

const isEven = (n: number): boolean => (n % 2 === 0);

const evenDigits = []; // FIXME
const oddSquared = [];// FIXME

console.log(evenDigits);
console.log(oddSquared);