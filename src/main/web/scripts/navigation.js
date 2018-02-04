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
    const nextStep = () => {
        const currentStepSlide = document.querySelector('section.steps:target');
        if (currentStepSlide) {
            const current = currentStepSlide.querySelector('.step-current');
            const next = currentStepSlide.querySelector(!current ? '.step' : '.step-current ~ .step');
            if (next) {
                if (current) {
                    current.classList.toggle('step-current');
                    current.classList.toggle('step-done');
                }
                next.classList.toggle('step-current');
            } else {
                // no more step, go to next slide
                clickOn("section:target .next")
            }
        } else {
            // no step, go to next slide
            clickOn("section:target .next")
        }
    };

    // Register handlers
    const keys = {
        ArrowRight: nextSlide,
        ArrowLeft: previousSlide,
        Space: nextStep,
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