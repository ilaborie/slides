speakers
    // is experimented
    .filter(speaker => speaker.xp > 10)
    // is love JS
    .filter(speaker => speaker.languages.some(lang => lang === 'JavaScript'));
