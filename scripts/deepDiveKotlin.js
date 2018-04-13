(() => {

    function cleanClass() {
        this.parentElement.classList.remove('live-code');
        this.parentElement.classList.remove('play');
    }

    Array.from(document.querySelectorAll('section.code.live-code article'))
        .forEach(elt => elt.addEventListener('click', cleanClass));

    Array.from(document.querySelectorAll('section.code.play article'))
        .forEach(elt => elt.addEventListener('click', cleanClass));

    // FIXME Countdown

})();
