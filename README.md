# Kotlin Jasmine

Kotlin JS Jasmine Bindings

## [Guide](./docs/guide.md)

## Setup

TODO


### Karma, Maven, frontend-maven-plugin

`pom.xml`:
```XML
<todo/>
```

`src/test/resources/package.json`:
```JSON
{ "todo": true }
```

`src/test/resources/karma.conf.js`:
```JS
module.exports = function (config) {
    config.set({
        basePath: '',
        frameworks: ['jasmine'],
        plugins: [
            require('karma-jasmine'),
            require('karma-chrome-launcher')
        ],
        files: [
            {pattern: './kotlin.js', watched: false},
            {pattern: './kotlin-jasmine-core.js', watched: false},
            {pattern: './path/to/my/spec.js', watched: false}
        ],
        proxies: {
            '/': '/base'
        },
        client: {
            clearContext: false
        },
        port: 8765,
        logLevel: config['LOG_INFO'],
        browsers: ['Chrome'],
        colors: false,
        singleRun: true
    })
};
```

