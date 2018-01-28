require.config({paths: {'vs': '../scripts/monaco-editor/min/vs'}});

// Before loading vs/editor/editor.main, define a global MonacoEnvironment that overwrites
// the default worker url location (used when creating WebWorkers). The problem here is that
// HTML5 does not allow cross-domain web workers, so we need to proxy the instantiation of
// a web worker through a same-domain script
// window.MonacoEnvironment = {
//     getWorkerUrl: function (workerId, label) {
//         return 'monaco-editor-worker-loader-proxy.js';
//     }
// };

require(['vs/editor/editor.main'], function () {

    // Editor options
    const defaultEditorOptions = {
        // automaticLayout: true,
        lineNumbers: false,
        theme: 'vs-dark',
        fontFamily: 'Fira Code, monospace',
        fontSize: 24,
        fontLigatures: true

        // FIXME keybinding: format, fix
    };

    // Data
    const extractData = node => ({
        parent: node,
        host: node.querySelector('.editor-panel'),
        consolePanel: node.querySelector('.console-panel'),
        language: node.getAttribute('data-lang'),
        code: node.querySelector('.initialCode').textContent,
        finalCode: node.querySelector('.finalCode').textContent
    });

    // Editor
    const createEditor = ({parent, host, consolePanel, language, code, finalCode}) => {
        const editor = monaco.editor.create(host, {
            ...defaultEditorOptions,
            language,
            value: code,
        });

        return {parent, editor, consolePanel, code, finalCode};
    };

    // register actions
    const actions = {
        'full-screen': {
            label: 'Toggle Full Screen',
            keyBinding: '',
            run: ({parent, editor}) => () => {
                parent.parentElement.classList.toggle('full-screen');
                editor.layout();
            }
        },
        'toggle-console': {
            label: 'Toggle Console',
            keyBinding: '',
            run: ({parent, editor}) => () => {
                parent.classList.toggle('hide-console');
                editor.layout();
            }
        },
        'reset': {
            label: 'Reset',
            keyBinding: '',
            run: ({editor, code}) => () => {
                editor.setValue(code);
            }
        },
        'load-final': {
            label: 'Load final',
            keyBinding: '',
            run: ({editor, finalCode}) => () => {
                editor.setValue(finalCode);
            }
        },
        'clear-console': {
            label: 'Clear Console',
            keyBinding: '',
            run: ({consolePanel}) => () => {
                const logEntry = consolePanel.querySelector('ul');
                logEntry.innerHTML = '';
            }
        },
        run: {
            label: 'Run',
            keyBinding: '',
            run: ({editor, consolePanel}) => () => {
                const logEntry = consolePanel.querySelector('ul');
                const log = message => {
                    const li = document.createElement('li');
                    li.innerHTML = message;
                    logEntry.appendChild(li);
                    li.scrollIntoView();
                };

                const value = editor.getValue();
                const hack = value.replace(/console\.log/g, 'log');

                const result = eval(hack);
                log('result', result);
            }
        }
    };

    const registerActions = params =>
        Object.keys(actions)
            .forEach(key => {
                const btn = params.parent.querySelector(`button.${key}`);
                if (btn) {
                    btn.addEventListener('click', () => {
                        const {label, run} = actions[key];
                        console.log(label);
                        run(params)();
                    })
                }
            });

    Array.from(document.querySelectorAll('.code-editor'))
        .map(extractData)
        .map(createEditor)
        .map(registerActions)
// TODO register command
    ;

});
