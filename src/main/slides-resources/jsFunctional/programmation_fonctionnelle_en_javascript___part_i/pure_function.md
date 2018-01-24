

### Eviter les fonctions avec effet de bord

```typescript
var sum = 0;

[1, 2, 3, 4, 5]
    .forEach(elt => sum += elt);
console.log({sum});
```

⚠️ danger, c'est un nid à bugs.

=> Éviter les fonctions qui n'ont pas de paramètres, ou retournent `void`

 
```typescript
const sum = [1, 2, 3, 4, 5]
    .reduce((acc, elt) => acc += elt);
console.log({sum});
```
