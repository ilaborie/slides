

 
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

