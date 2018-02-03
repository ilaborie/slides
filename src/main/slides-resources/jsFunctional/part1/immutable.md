

const

Object.freeze

referentially transparent


avec [ImmutableJS](https://facebook.github.io/immutable-js/)

Comment fait-on ?

```typescript
class List<T> {
    private array: T[];
    
    constructor(elements: T[] = []) {
        this.array = [... elements]; 
    }
    
    add(element: T) : List<T> {
        return new [...this.array, element];
    }
    
}
```


