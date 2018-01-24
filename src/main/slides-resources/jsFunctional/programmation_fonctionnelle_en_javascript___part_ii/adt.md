
### Abstract Data Type

```sml
datatype rational = Whole of int
                  | Frac  of int*int
```

### Pattern matching

Pas encore, mais

```javascript
let getLength = vector => match (vector) {
    { x, y, z }: Math.sqrt(x ** 2 + y ** 2 + z ** 2),
    { x, y }:    Math.sqrt(x ** 2 + y ** 2),
    [...]:       vector.length,
    else: {
        throw new Error("Unknown vector type");
    }
}
```

[ECMAScript Pattern Matching Syntax - TC39 stage 0 proposal](https://github.com/tc39/proposal-pattern-matching)