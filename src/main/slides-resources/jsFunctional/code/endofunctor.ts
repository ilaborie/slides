class AnEndoFunctor implements Functor<V> {
    map(mapper: (V) => W): AnEndoFunctor<W> {
        // ...
    }
}