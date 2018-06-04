Predicate<Speaker> isExperimented = speaker -> speaker.xp > 10;
Predicate<Speaker> isLoveJS = speaker -> speaker.getLanguages().contains("Java");

speakers.filter(isExperimented)
        .filter(isLoveJS);