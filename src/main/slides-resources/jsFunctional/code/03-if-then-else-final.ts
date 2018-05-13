const isEven = (n: number): boolean =>
    (n % 2 === 0);

[2, 3, 4, 5]
    .forEach(elt => console.log(elt, 'even?', isEven(elt)));