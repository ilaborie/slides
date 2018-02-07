
### Currying

The act of transforming a function of several arguments into a chain of functions of one argument that will yield the same result when called in sequence with the same arguments.

`f(x, y, z) = g(x)(y)(z)`


```typescript
const mult = (a: number, b: number) => a * b;

// idea: identity = mult(1, _)
const identity = (a: number) => mult(1, a);

// better
const multCurry = (a: number) => {
    return (b: number) => a * b;
}

// Type: number => number => number
const multCurry2 = a => b => a * b;

// Idea: géné
```


[Partial Application Syntax, stage 1](https://github.com/tc39/proposal-partial-application)

```typescript
const addOne = add(1, ?); // apply from the left
const addTen = add(?, 10); // apply from the right
const maxGreaterThanZero = Math.max(0, ...); // remaining arguments placeholder

```
Also usable with [The Pipeline Operator, stage 1](https://github.com/tc39/proposal-pipeline-operator)
