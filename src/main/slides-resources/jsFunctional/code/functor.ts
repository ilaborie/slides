interface Functor<V> {
    map(mapper: (V) => W): Functor<W>;
}