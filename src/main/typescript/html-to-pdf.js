const puppeteer = require('puppeteer');

const from = process.argv[2];
const to = process.argv[3];

(async () => {
    const browser = await puppeteer.launch();
    const page = await browser.newPage();
    await page.goto(from, {waitUntil: 'networkidle'});
    await page.pdf({path: to, format: 'A4'});
    browser.close();
})();