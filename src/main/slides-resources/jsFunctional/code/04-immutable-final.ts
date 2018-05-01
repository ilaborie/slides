// let & const
let oLet = {a: 1, b: 2};
oLet.a = 3;


// Transparence référentielle ?
const oConst = Object.freeze({a: 10, b: 20});
oConst.a = 30;

// Deconstruction
const oConst2 = {...oConst, a: 30};

console.info({oLet});
console.info({oConst});
console.info({oConst2});