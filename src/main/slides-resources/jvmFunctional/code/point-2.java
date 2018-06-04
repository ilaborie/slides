public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point translateX(int offset) {
        return new Point(this.x + offset, this.y);
    }

    // + Getters
    // + equals & hashCode
    // + toString
}