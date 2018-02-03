
## Function is First-class citizen

### Basique

```javascript
function mult(a, b) {
    return a * b;
}
```

```javascript
typeof mult; // "function"
```

### Anonymous

```javascript
var mult = function (a, b) {
    return a * b;
}
```

### ES2015

```javascript
const mult = (a, b) => a * b;
```

```typescript
const mult = (a: number, b: number) => a * b;
```

