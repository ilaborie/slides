class List<T> {
    private array: T[];

    constructor(elements: T[] = []) {
        this.array = [... elements];
    }

    add(element: T) : List<T> {
        return new List([...this.array, element]);
    }
}