import * as ts from 'typescript';
import {ScriptTarget} from 'typescript';

// TypeScript
const tsOption: ts.TranspileOptions = {
    compilerOptions: {
        module: ts.ModuleKind.None,
        target: ScriptTarget.ES2016
    }
};
const transpileTypeScript = (body: string) => {
    const {outputText, diagnostics} = ts.transpileModule(body, tsOption);
    console.info('result', {outputText, diagnostics});
    if (!diagnostics || diagnostics.length === 0) {
        return Promise.resolve(outputText);
    } else {
        console.warn('diagnostics', diagnostics);
        return Promise.reject(diagnostics);
    }
};


// Transpile
export const transpileToJS = (body: string, language: string): Promise<any> =>
    Promise.resolve()
        .then((): Promise<any> => {
            switch (language) {
                case 'js':
                    return Promise.resolve(body); // TODO maybe babel
                case 'typescript':
                    return transpileTypeScript(body);
                default:
                    return Promise.reject(`Cannot transpile ${language} !`);
            }
        });
