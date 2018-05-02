


#### functor: 

`map(f: F<A>)(f: (A) -> B): F<B>`

#### applicative functor:
`apply(f: F<A>)(f: F<(A) -> B>): F<B>`

#### monad:
`flatMap(f: F<[A>])(f: A → F[B]): F[B]`


```typescript
Some(1)
    .map { it + 1 }
    .flatMap { Some("4" + it) }
    .map { it }
```