import * as marked from 'marked';
import {createInterface} from 'readline';

// marked.setOptions({
//     highlight: code => highlightAuto(code).value
// });

export function markdown(md: string): Promise<string> {
    return new Promise((resolve, reject) => {
        marked(md, (err, result) => {
            if (err) {
                return reject(err);
            }
            resolve(result);
        })
    });
}

const rl = createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false
});

let stack: string[] = [];

rl.on('line', line => stack.push(line));
rl.on('close', () => {
    const md = stack.join('\n');
    markdown(md)
        .then(result => process.stdout.write(result));
});
