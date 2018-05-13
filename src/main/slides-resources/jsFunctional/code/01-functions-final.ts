// Function is First-class citizen
function mult(a, b) {
    return a * b;
}

console.log(typeof mult); // function
console.log(mult(3, 14));

// Variable
const mult2 = function(a, b) {
    return a * b;
};

// ES2015+
const mult3 = (a, b) => a * b;

// TypeScript
const mult4 = (a: number, b: number): number => a * b;
