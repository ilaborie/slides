
⚠️ choisisons `n` comme un `number` `>` à `0`

```typescript
const factorial1 = n => {
    if (n<=1) {
        return 1;
    }
    return n * factorial1(n - 1);
};

const factorial2 = n => 
    (n<=1) ? 1 : n * factorial2(n - 1);
```

### Tail Recursion

```typescript
const factorial3 = (n, acc = 1) =>
    (n<=1) ? acc : factorial3(n - 1, acc * ); 
```

[Support de la tail-rec](http://kangax.github.io/compat-table/es6/#test-proper_tail_calls_(tail_call_optimisation))

###
⚠️ Éviter les recusions entre plusieurs fonctions:

```typescript
const a = n => {
    if (n > 42) {
        return b(n - 42);
    }
    return 2;
}

const b = n => {
    if (n < 123) {
        return a(n + 42);
    }
    return 3;
}
```

> Recursion is the GOTO of functional programming
-- Erik Meijer ("Functional Programming with Bananas, Lenses, Envelopes and Barbed Wire", 1991) 