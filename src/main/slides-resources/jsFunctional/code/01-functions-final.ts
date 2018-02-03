// Function is First-class citizen


function mult(a, b) {
    return a * b;
}

console.log(typeof mult); // function
console.log(mult.name); // mult

// Variable

const mult2 = function(a, b) {
    return a * b;
};

console.log(mult2.name); // mult2

// ES2015+

const mult3 = (a, b) => a * b;

// TypeScript

const mult4 = (a: number, b: number): number => a * b;

const mult5: (a: number, b: number) => number =
    (a, b) => a * b;


// Anomynous

(function() {
    // scoped to the function
})();

