speakers
    .filter(speaker => speaker.xp > 10 &&
        speaker.languages.some(lang => lang === 'JavaScript'));