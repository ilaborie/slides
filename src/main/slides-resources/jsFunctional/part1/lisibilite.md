

```typescript
speakers
   .filter(speaker => speaker.xp > 10 &&
        speaker.some(lang => lang === 'JavaScript'))
```

```typescript
speakers
   .filter(speaker => speaker.xp > 10) // is experimented
   .filter(speaker => speaker.some(lang => lang === 'JavaScript')) // is love JS
```

```typescript
const isExperimented = speaker => speaker.xp > 10; 
const isLoveJS = speaker => speaker.loves.some(lang => lang === 'JavaScript'); 

speakers
   .filter(isExperimented)
   .filter(isLoveJS)
```
    