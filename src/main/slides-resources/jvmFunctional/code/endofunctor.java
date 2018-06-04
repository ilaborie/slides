interface EndoFunctor<A> {
    EndoFunctor<A> map(mapper: UnaryOperator<A>);
}