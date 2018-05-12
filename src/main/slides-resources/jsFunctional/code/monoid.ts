interface Monoid<V> {
    flatMap(mapper: (V) => Monoid<W>): Monoid<W>;
}