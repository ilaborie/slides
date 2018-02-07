### High Order function

map, reduce, filter, sort

```typescript

// f: X => Y
// g: Y => Z
// compose: (Y => Z, X => Y) => X => Z
const compose = (g, f) => x => g(f(x))
  
```

[Function Composition](https://github.com/isiahmeadows/function-composition-proposal)
[Function.prototype.compose](https://github.com/simonstaton/Function.prototype.compose-TC39-Proposal)