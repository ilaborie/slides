
## Boucles `for`

[For Loops](https://kotlinlang.org/docs/reference/control-flow.html#for-loops) 

```kotlin
for (item in collection) print(item)
```

## Boucles `while`, `do`

[While Loops](https://kotlinlang.org/docs/reference/control-flow.html#while-loops)

```kotlin
while (x > 0) {
    x--
}

do {
    val y = retrieveData()
} while (y != null) // y is visible here!

```

Il y a aussi des `break` et `continue`, label pour les boucles

## When

[Expression `when`](https://kotlinlang.org/docs/reference/control-flow.html#when-expression)

Constante, plusieurs valeurs avec `,`, une expression, ou avec `is` ou `in`.


Tips

- privilégier les `when` si vous avec plus de 2 cas, le code est plus lisible
- si vous faites des fonctions recursives, faites les `tailrec`