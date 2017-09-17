const request = require('request-promise-native');

const url = 'https://raw.githubusercontent.com/Fyrd/caniuse/master';

const threshold = parseFloat(process.argv[2]);
const features = process.argv[3].split(',');

const extractBrowser = (browser, data) => Object.keys(data)
    .map(version => ({key: browser, version, usage: data[version], mobile: browser.indexOf('_') >= 0}))
    .filter(({usage}) => usage >= threshold)
    .map(browser => {
        if (browser.key === 'and_chr' && browser.version === "0") {
            browser.version = "61";
        } else if (browser.version === "0") {
            browser.version = "all";
        }
        return browser;
    })
    .reduce((acc, elt) => {
        const found = acc.find(browser => browser.key === elt.key);
        if (found) {
            found.versions.push(elt.version);
            found.usage += elt.usage;
        } else {
            const {key, version, usage, mobile} = elt;
            acc.push({key, versions: [version], usage, mobile});
        }
        return acc;
    }, []);

const browsers = request({uri: `${url}/region-usage-json/FR.json`, json: true})
    .then(body => body.data)
    .then(data =>
        Object.keys(data)
            .reduce((acc, key) => acc.concat(extractBrowser(key, data[key])), []))
    .then(browsers => browsers.sort((a, b) => b.usage - a.usage))
    .catch(err => console.error(err));

// browsers.then(browsers => console.log(browsers));

// feature
const extractFeature = feature => request({uri: `${url}/features-json/${feature}.json`, json: true})
    .then(({title, description, spec, stats, notes_by_num}) =>
        ({key: feature, title, description, spec, stats, notes_by_num}))
    .catch(err => console.error(`Oops, ${feature}`, err));

const allFeatures = Promise.all(features.map(extractFeature));

const buildStats = (browser, feature) => browser.versions
    .map(version => {
        const browserStats = feature.stats[browser.key];
        const status = browserStats[version];
        return {version, status};
    });

Promise.all([browsers, allFeatures])
    .then(([browsers, allFeatures]) => {
        const total = browsers.reduce((acc, elt) => acc + elt.usage, 0);
        const features = allFeatures.map(({key, title, description, spec, notes_by_num}) =>
            ({key, title, description, spec, notes_by_num}));
        const values = browsers.reduce((acc, browser) => {
            const v = allFeatures.map(feature => ({
                browser: browser.key,
                feature: feature.key,
                stats: buildStats(browser, feature)
            }));
            return acc.concat(v);
        }, []);
        return {browsers, total, features, values};
    })
    .then(result => console.log(JSON.stringify(result, null, 2))) // output
    .catch(err => console.error(err));