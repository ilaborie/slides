IntBinaryOperator mult = (a, b) -> a * b;

// Curry
IntFunction<IntFunction> curriedMult = b -> a -> a * b;

// Usage
IntFunction identity = a -> mult.applyAsInt(a, 1);

IntFunction dbl = curriedMult.apply(2);