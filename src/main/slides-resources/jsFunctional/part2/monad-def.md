
* [Category Theory]()
* <https://medium.com/@tzehsiang/javascript-functor-applicative-monads-in-pictures-b567c6415221>


 
### Monoïd

```typescript
interface Monoid<V> {
    flatMap(mapper: (V) -> Monoid<W>): Monoid<W>; 
}
```

#### monads:
```typescript
interface Monad<V> extends Monoid<V>, Functor<V> {  
}
```

