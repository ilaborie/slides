import {highlight} from 'highlight.js';
import {createInterface} from 'readline';

const lang = process.argv[2];

const rl = createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false
});

let stack: string[] = [];

rl.on('line', line => stack.push(line));

rl.on('close', () => {
    const code = stack.join('\n');
    const result = highlight(lang, code);
    process.stdout.write(result.value);
});