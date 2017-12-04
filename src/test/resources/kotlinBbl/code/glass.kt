data class Glass(val capacity: Int, val current: Int = 0) {
    init {
        require(capacity > 0)
        require(current in 0..capacity)
    }

    val isEmpty: Boolean = (current == 0)
    val isFull: Boolean = (current == capacity)
    val remainingVolume: Int  by lazy { capacity - current }
    fun empty(): Glass = copy(current = 0)
    fun fill(): Glass = copy(current = capacity)
    operator fun plus(value: Int): Glass =
        copy(current = (current + value).coerceAtMost(capacity))
    operator fun minus(value: Int): Glass =
        copy(current = (current - value).coerceAtLeast(0))
    override fun toString() = "$current/$capacity"
}

typealias State = List<Glass>