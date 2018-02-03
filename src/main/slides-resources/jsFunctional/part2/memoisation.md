
High Order Function

https://medium.freecodecamp.org/understanding-memoize-in-javascript-51d07d19430e
https://www.sitepoint.com/implementing-memoization-in-javascript/

```typescript

const fibonacci = function(n) {
    switch (n) {
        case 1 : return 1;
        case 2 : return 1;
        default:
            return fibonacci(n-2) + fibonacci(n-1);
    }  
}
```