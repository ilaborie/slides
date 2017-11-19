import {highlight, IHighlightResult} from 'highlight.js';
import {createInterface} from 'readline';

export function code(lang: string, code: string): Promise<IHighlightResult> {
    return Promise.resolve(highlight(lang, code));
}

const lang = process.argv[2];
if (lang) {
    const rl = createInterface({
        input: process.stdin,
        output: process.stdout,
        terminal: false
    });

    let stack: string[] = [];
    rl.on('line', line => stack.push(line));
    rl.on('close', () => {
        const data = stack.join('\n');
        code(lang, data)
            .then(result => process.stdout.write(result.value));
    });
}