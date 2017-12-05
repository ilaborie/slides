private typealias StateWithHistory = Pair<State, List<Move>>

fun solve(from: State, to: State): List<Move> {
    tailrec fun solveAux(states: List<StateWithHistory>, visitedStates: Set<State>) {
        val solution: StateWithHistory? = states.find { (state, _) -> state == to }
        if (solution != null) { return solution.second }

        val next = states
            .flatMap { (state, history) ->
                state.availableMoves()
                    .map { move -> state.move(move) to (history + move) }
            }
            .filterNot { (state, _) -> visitedStates.contains(state) }
        val nextVisited = visitedStates + next.map { it.first }
        return solveAux(next, nextVisited)
    }
    return solveAux(listOf(from to emptyList()), setOf(from))
}