interface EndoFunctor<V> {
    map(mapper: (V) => V): Functor<V>;
}