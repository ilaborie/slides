(() => {

    // Navigation
    const clickOn = selector => {
        const btn = document.querySelector(selector);
        if (btn) {
            btn.click();
        }
    };
    const previous = () => clickOn("section:target .previous");
    const next = () => clickOn("section:target .next");
    const home = () => clickOn(".slides-nav .cover");

    // Register handlers
    const keys = {
        ArrowRight: next,
        ArrowLeft: previous,
        Space: next,
        Home: home
    };

    // listen events
    document.addEventListener('keydown', event => {
        const {code} = event;
        if (keys[code]) {
            keys[code](event);
            event.stopPropagation()
        }
    });
})();