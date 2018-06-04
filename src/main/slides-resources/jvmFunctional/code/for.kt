fun notFunErrors(events: List<Event>, size: Int): List<Event> {
    val result = mutableListOf<Event>()
    for (event in events) {
        if (event.isError) {
            result.add(event)
        }
        if (result.size >= size) {
            return result
        }
    }
    return result
}

fun funErrors(events: List<Event>, size: Int): List<Event> =
    events
        .filter { it.isError }
        .take(size)
