data class Point(val x: Int, val y: Int) {

    fun translateX(offset: Int): Point =
        this.copy(x = x + offset)
    
    // generated: Getters, equals & hashCode, toString, ...
}