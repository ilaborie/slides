const compose = (g , f) => x => g(f(x));

const composeAll = (...functions) =>
    x => functions.reduceRight((value, fun) => fun(value), x);