const {launch} = require('puppeteer');

const from = process.argv[2];
const to = process.argv[3];

const options = {
    waitUntil: 'networkidle'
};

const pdfOptions = {
    path: to,
    format: 'A4',
    printBackground: true,
    landscape: true
};

launch()
    .then(browser => browser.newPage()
        .then(page => ({browser, page})))
    .then(({browser, page}) => page.goto(from, options)
        .then(() => ({browser, page})))
    .then(({browser, page}) => page.pdf(pdfOptions)
        .then(() => browser))
    .then(browser => browser.close());
