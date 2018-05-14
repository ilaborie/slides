import {launch, NavigationOptions, PDFOptions} from 'puppeteer';

export function renderPdf(from: string, to: string): Promise<void> {
    const options: Partial<NavigationOptions> = {
        waitUntil: 'networkidle0'
    };

    // const wait = <T>(value: T) => new Promise<T>(resolve => {
    //     setTimeout(() => resolve(value), 1000);
    // });

    const pdfOptions: Partial<PDFOptions> = {
        path: to,
        format: 'A4',
        printBackground: true,
        landscape: true
    };

    return launch()
        .then(browser => browser.newPage()
            .then(page => ({browser, page})))
        .then(({browser, page}) => page.goto(from, options)
            .then(() => ({browser, page})))
        // .then(value => wait(value))
        .then(({browser, page}) => page.pdf(pdfOptions)
            .then(() => browser))
        .then(browser => browser.close());
}

const from = process.argv[2];
const to = process.argv[3];
if (from && to) {
    renderPdf(from, to);
}
