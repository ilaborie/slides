interface Monad<A> extends Functor<A> {
    flatMap(mapper: (V) => Monad<W>): Monad<W>;
}