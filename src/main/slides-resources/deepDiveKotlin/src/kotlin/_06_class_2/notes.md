
## Generics

<https://kotlinlang.org/docs/reference/generics.html>

covariant: `out`
contravariant: `in`

Consumer `in`, Producer `out`

###  Star

`interface Function<in T, out U>`

`Function<*, String>` correspond à `Function<in Nothing, String>`
`Function<Int, *>` correspond à `Function<Int, out Any?>`
`Function<*, *>` correspond à `Function<in Nothing, out Any?>`

### UpperBound

```kotlin
fun <T : Comparable<T>> sort(list: List<T>): List<T>
```

<!> Les contrôles de types générics ne sont fait qu'au moment de la compilation

## Typealias

* utiliser un nom plus explicite qu'un type standard
* rendre plus lisible du code

```kotlin
fun getAllEntities(): Map<Pair<String, Int>, List<Entity>> = TODO()

typealias Id = String
typealias Version = Int
typealias EntityKey = Pair<Id, Version>

fun getAllEntities(): Map<EntityKey, List<Entity>> = TODO()
```