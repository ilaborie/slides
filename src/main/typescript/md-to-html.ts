import * as marked from 'marked';
import {highlightAuto} from 'highlight.js';
import {createInterface} from 'readline';

marked.setOptions({
    highlight: code => highlightAuto(code).value
});

const rl = createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false
});

let stack = [];

rl.on('line', line => stack.push(line));

rl.on('close', () => {
    const md = stack.join('\n');
    marked(md, (err, result) => process.stdout.write(result));
});



