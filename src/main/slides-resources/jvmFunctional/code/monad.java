interface Monad<A> extends Functor<A> {
    Monad<B> flatMap(mapper: Function<A, Monad<B>>);
}