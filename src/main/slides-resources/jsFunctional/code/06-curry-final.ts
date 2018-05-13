// type: number => number => number
const mult = a => b => a * b;

const identity = mult(1);

const double = mult(2);

const triple = mult(3);

[identity, double, triple]
    .map(fun => fun(42))
    .forEach(x => console.log(x));