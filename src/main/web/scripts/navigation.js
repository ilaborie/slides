(() => {

    // Navigation
    const clickOn = selector => {
        const btn = document.querySelector(selector);
        if (btn) {
            btn.click();
        }
    };
    const previousSlide = () => clickOn("section:target .previous");
    const nextSlide = () => clickOn("section:target .next");
    const home = () => clickOn(".slides-nav .cover");

    // Register handlers
    const keys = {
        ArrowRight: nextSlide,
        ArrowLeft: previousSlide,
        Space: nextSlide,
        Home: home
    };

    // listen events
    document.addEventListener('keydown', event => {
        if (event.target.type !== 'textarea') {
            const {code} = event;
            if (keys[code]) {
                keys[code](event);
                event.stopPropagation()
            }
        }
    });
})();