* Covariant (consome): `out`
* Contravariant (produit): `in`


* Borne supérieur :

```kotlin
fun <T : Comparable<T>> sort(list: List<T>): List<T>
```

⚠️ Les contrôles de types générics ne sont fait qu'au moment de la compilation

* Les détails: <https://kotlinlang.org/docs/reference/generics.html>