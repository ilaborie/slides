






```typescript
Some(1)
    .map { it + 1 }
    .flatMap { Some("4" + it) }
    .map { it }
```