interface Functor<A> {
    map(mapper: (A) => B): Functor<B>;
}