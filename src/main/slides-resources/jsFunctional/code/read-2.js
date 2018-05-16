speakers.filter(speaker => speaker.xp > 10) // is experimented
        // is love JS
        .filter(speaker => speaker.languages.some(lang => lang === 'JavaScript'));
