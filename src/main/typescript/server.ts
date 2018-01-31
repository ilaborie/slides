import * as  express from 'express';
import {Request, Response} from 'express';
import * as morgan from 'morgan';

import {code} from './code-to-html';
import {compatibility} from './compat';
import {markdown} from './md-to-html';
import {renderPdf} from './html-to-pdf';
import {transpileToJS} from './transpile-to-js';

const app = express();

app.use(morgan('dev'));

// body parser as string
app.use((req: Request, res: Response, next) => {
    req.setEncoding('utf8');
    let rawBody = '';
    req.on('data', chunk => rawBody += chunk);
    req.on('end', () => {
        req.body = rawBody;
        next();
    });
});


// static
app.use(express.static('src/main/web'));


// Routes

// Code to HTML (syntax highlighting)
app.post('/code', (req: Request, res: Response) => {
    const lang = req.query['lang'];
    const body = req.body as string;
    console.log(`code [${lang}]`);
    code(lang, body)
        .then(result => result.value)
        .then(html => res.send(html));
});

// get Browser Compatibility
app.get('/compatibility', (req: Request, res: Response) => {
    const country = req.query['country'];
    const threshold = parseFloat(req.query['threshold']);
    const features = req.query['features'].split(',');
    console.log(`compatibility ${country} ${threshold}: ${features.join(', ')}`);
    compatibility(country, threshold, features)
        .then(html => res.send(html));
});

// Markdown to HTML, with syntax highlighting
app.post('/markdown', (req: Request, res: Response) => {
    console.log(`markdown`);
    const body = req.body as string;
    markdown(body)
        .then(html => res.send(html));
});

// Generate PDF
app.post('/pdf', (req: Request, res: Response) => {
    const from = req.query['from'];
    const to = req.query['to'];
    console.log(`pdf ${from} -> ${to}`);
    renderPdf(from, to)
        .then(() => res.send('ok'));
});

// Markdown to HTML, with syntax highlighting
app.post('/tojs', (req: Request, res: Response) => {
    const language = req.query['language'];
    console.log(`transform ${language} to JS`);
    const body = req.body as string;
    transpileToJS(body, language)
        .then(js => res.send(js))
        .catch(error => res.status(400).send(error));
});

// Run
const port = 5000;
app.listen(port, () =>
    console.log(`Server listening on port ${port}!`));

