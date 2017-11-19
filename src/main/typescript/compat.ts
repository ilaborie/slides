import * as request from 'request-promise-native';

const url = 'https://raw.githubusercontent.com/Fyrd/caniuse/master';

interface BrowserVersions {
    [index: string]: number // version: usage
}

interface RegionUsage {
    id: string;
    name: string;
    month: string;
    access_date: string;
    data: {
        [index: string]: BrowserVersions
    }
}

interface BrowserVersion {
    key: string;
    version: string;
    usage: number;
    mobile: boolean;
}

interface Browser {
    key: string
    versions: string[]
    usage: number;
    mobile: boolean
}

interface BrowserFeature {
    [index: string]: string; // version: support
}

interface Feature {
    key: string
    title: string;
    description: string;
    spec: string;
    status: string;
    stats: {
        [index: string]: BrowserFeature;
    }
    notes_by_num: {
        [index: string]: string; // num: description
    }
}

interface BrowserFeatureStat {
    version: string;
    status: string
}

interface CompatibilityBrowserFeature {
    browser: string;
    feature: string;
    stats: BrowserFeatureStat[];
}

interface CompatibilityFeature {
    key: string
    title: string;
    description: string;
    spec: string;
    notes_by_num: {
        [index: string]: string; // num: description
    }
}

interface CompatibilityResult {
    browsers: Browser[];
    total: number;
    features: CompatibilityFeature[];
    values: CompatibilityBrowserFeature[];
}


export function compatibility(country: string, threshold: number, features: string[]): Promise<CompatibilityResult> {

    const extractBrowser = (browser: string, data: BrowserVersions): Browser[] =>
        Object.keys(data)
            .map(version => ({
                key: browser,
                version,
                usage: data[version],
                mobile: browser.startsWith('and') || browser.startsWith('ios')
            }) as BrowserVersion) // Browser[]
            .filter(({usage}) => usage >= threshold) // Browser[]
            .map(browser => {
                if (browser.key === 'and_chr' && browser.version === '0') {
                    browser.version = '61';
                } else if (browser.version === '0') {
                    browser.version = 'all';
                }
                return browser;
            }) // Browser[]
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
            }, [] as Browser[]);

    const browsers: Promise<Browser[]> = request({uri: `${url}/region-usage-json/${country}.json`, json: true})
        .then(body => body as RegionUsage)
        .then(body => body.data)
        .then(data =>
            Object.keys(data)
                .reduce((acc, key) => acc.concat(extractBrowser(key, data[key])), [] as Browser[]))
        .then(browsers => browsers.sort((a, b) => b.usage - a.usage));
    // .catch(err => console.error(err));

// browsers.then(browsers => console.log(browsers));

// feature
    const extractFeature = (feature: string): Promise<Feature> =>
        request({uri: `${url}/features-json/${feature}.json`, json: true})
            .then(({title, description, spec, stats, notes_by_num}) =>
                ({key: feature, title, description, spec, stats, notes_by_num}) as Feature);
    // .catch(err => console.error(`Oops, ${feature}`, err));

    const allFeatures: Promise<Feature[]> = Promise.all(features.map(extractFeature));

    const buildStats = (browser: Browser, feature: Feature): BrowserFeatureStat[] => browser.versions
        .map(version => {
            const browserStats = feature.stats[browser.key];
            const status = browserStats[version];
            return {version, status};
        });

    return Promise.all([browsers, allFeatures])
        .then(([bs, fs]) => {
            const browsers = bs as Browser[];
            const allFeatures = fs as Feature[];
            const total = browsers.reduce((acc, elt) => acc + elt.usage, 0);
            const features: CompatibilityFeature[] = allFeatures.map(({key, title, description, spec, notes_by_num}) => ({
                key,
                title,
                description,
                spec,
                notes_by_num
            }));
            const values = browsers.reduce((acc, browser) => {
                const v: CompatibilityBrowserFeature[] = allFeatures.map(feature => ({
                    browser: browser.key,
                    feature: feature.key,
                    stats: buildStats(browser, feature)
                }));
                return acc.concat(v);
            }, [] as CompatibilityBrowserFeature[]);
            return {browsers, total, features, values};
        })
}


const country = process.argv[2];
const threshold = parseFloat(process.argv[3]);
const features = process.argv[4];

if (country && threshold && features) {
    compatibility(country, threshold, features.split(','))
        .then(result => console.log(JSON.stringify(result, null, 2))) // output
        .catch(err => console.error(err));
}

