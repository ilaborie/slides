type Predicate[E] = E => Boolean

def not[E](p: Predicate[E]): Predicate[E] = e => !p(e)

val isEven: Predicate[Int] = n => (n % 2 == 0)
val isOdd = not(isEven)

var evenDigits = digits.filter(isEven)
val oddDigits = digits.filter(isOdd)