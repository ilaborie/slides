interface Functor<A> {
    Functor<B> map(mapper: Function<A, B>);
    // avec associativité
}