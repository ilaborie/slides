case class Point(x: Int, y: Int) {

    def translateX(offset: Int): Point =
        this.copy(x = x + offset)
    
    // generated: Getters, equals & hashCode, toString, ...
}