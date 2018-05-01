// let sum = 0;
// [1, 2, 3, 4, 5].forEach(elt => sum += elt);

const sum = [1, 2, 3, 4, 5]
    .reduce((acc, elt) => acc + elt);

console.log({ sum });