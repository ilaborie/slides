interface Monad<A> extends Functor<A> {
    flatMap(mapper: (A) => Monad<B>): Monad<B>;
}