UnaryOperator<IntPredicate> not = predicate -> i -> !predicate.test(i);
IntPredicate isOdd = not.apply(isEven); // isEven.negate()
var oddDigits = // ...