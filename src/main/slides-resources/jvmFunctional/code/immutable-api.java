public class List<T> {
    private final T[] array;

    public List(T[] elements) {
        this.array = Arrays.copyOf(elements, elements.length);
    }

    public List<T> add(T element) {
        var newElts = Arrays.copyOf(this.array, this.array.length + 1);
        newElts[this.array.length] = element;
        return new List<>(newElts);
    }
}