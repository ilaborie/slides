const {launch} = require('puppeteer');

const from = process.argv[2];
const to = process.argv[3];

const options = {
    waitUntil: 'networkidle'
};

(async () => {
    const browser = await launch();
    const page = await browser.newPage();
    await page.goto(from, options);
    await page.pdf({
        path: to,
        format: 'A4',
        printBackground: true,
        landscape: true
    });
    browser.close();
})();