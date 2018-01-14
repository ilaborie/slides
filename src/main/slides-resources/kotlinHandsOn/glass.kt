data class Glass(val capacity: Int, val current: Int = 0) {

    init {
        require(capacity > 0) {
            "Capacity: $capacity should be > 0"
        }
        require(current in 0..capacity) {
            "Current: $current should be into [0, $capacity]"
        }
    }
}

typealias State = List<Glass>
