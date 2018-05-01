const isEven = (n: number): boolean => {
    if (n % 2 === 0) {
        return true;
    } else {
        return false;
    }
};

[2, 3, 4, 5]
    .forEach(elt => console.log(elt, 'even?', isEven(elt)));