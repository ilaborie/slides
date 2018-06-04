type IntFun = (number) => number;

const stupidMemoizer = (fun: IntFun): IntFun => {
    const cache: number[] = [];
    return (n: number) => {
        const cached = cache[n];
        if (typeof cached === 'number') {
            return cached;
        }
        return (cache[n] = fun.call(null, n));
    }
};

const fibonacci: IntFun = n => {
    switch (n) {
        case 1 :
            return 1;
        case 2 :
            return 1;
        default:
            return fibonacci(n - 2) + fibonacci(n - 1);
    }
};

console.log('fibonacci(15)', fibonacci(15));