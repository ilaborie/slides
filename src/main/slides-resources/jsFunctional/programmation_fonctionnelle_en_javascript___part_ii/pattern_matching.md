Un exemple:

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

Pour implémenter du pattern-matching il faut de la déconstruction.
On l'a déjà sur les `{}` et les `[]`.

```java
const myPoint = { x: 14, y: 3 };
const {x, y} = myPoint; // x === 14, y === 3

const tab = [1, 2, 3, 4];
const [head, ...tail] = tab; // head === 1, tail === [ 2, 3, 4]

```


C'est une [Stage 0 Proposal](https://github.com/tc39/proposal-pattern-matching)

