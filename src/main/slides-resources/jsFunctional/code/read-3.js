const isExperimented = speaker => speaker.xp > 10;
const isLoveJS = speaker => speaker.languages.some(lang => lang === 'JavaScript');

speakers.filter(isExperimented)
        .filter(isLoveJS);