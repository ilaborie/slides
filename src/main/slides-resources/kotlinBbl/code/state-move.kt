fun State.move(move: Move): State =
    mapIndexed { index, glass ->
        when (move) {
            is Empty -> if (index == move.index) glass.empty() else glass
            is Fill  -> if (index == move.index) glass.fill() else glass
            is Pour  -> when (index) {
                move.from -> glass - get(move.to).remainingVolume
                move.to   -> glass + get(move.from).current
                else      -> glass
            }
        }
    }
