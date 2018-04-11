import {createInterface} from 'readline';
import * as Prism from 'prismjs';
const loadLanguages = require('prismjs/components/index');

loadLanguages(['kotlin', 'bash', 'java']);

// HACK

Prism.languages['kotlin']['keyword']['pattern'] = /(^|[^.])\b(?:abstract|annotation|as|break|by|catch|class|companion|const|constructor|continue|crossinline|data|do|else|enum|final|finally|for|fun|get|if|import|in|init|inline|inner|interface|internal|is|lateinit|noinline|null|object|open|out|override|package|private|protected|public|reified|return|sealed|set|super|tailrec|this|throw|to|try|typealias|val|var|when|where|while)\b/;


export function code(lang: string, code: string): Promise<string> {
    const html = Prism.highlight(code, Prism.languages[lang]);
    return Promise.resolve(html);
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
            .then(result => process.stdout.write(result));
    });
}