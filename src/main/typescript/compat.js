const caniuse = require('caniuse-api');

const browsers = process.argv[2];
const features = process.argv[3].split(',');

caniuse.setBrowserScope(browsers);

const extractInfo = param => param.y || null;

const toInfo = (feature, obj) => Object.keys(obj)
    .map(key => ({key, value: extractInfo(obj[key])}))
    .reduce((acc, elt) => {
        acc.push({feature, browser: elt.key, status: elt.value});
        return acc;
    }, []);

const data = features.reduce((acc, feature) =>
    acc.concat(toInfo(feature, caniuse.getSupport(feature))), []);

data.forEach(({feature, browser, status}) =>
    console.log(`${feature}\t${browser}\t${status}`));





