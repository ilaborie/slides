const myPoint = { x: 14, y: 3 };
const {x, y} = myPoint; // x === 14, y === 3

const tab = [1, 2, 3, 4];
const [head, ...tail] = tab; // head === 1, tail === [ 2, 3, 4]