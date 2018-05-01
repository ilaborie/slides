interface Event { error: boolean; /* ...*/}

const funErrors = (events: Event[], size = 10): Event[] =>
    events
        .filter(evt => evt.error)
        .slice(0, size);

const notFunErrors = (events: Event[], size = 10): Event[] => {
    const result: Event[] = [];
    for (let i = 0; i < events.length; i++) { // 🤮
        const evt = events[i];
        if (evt.error) {
            result.push(evt);
        }
        if (result.length >= size) {
            return result;
        }
    }
    return result;
};