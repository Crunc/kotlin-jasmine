module.exports = function (config) {
    config.set({
        basePath: '',
        frameworks: ['jasmine'],
        plugins: [
            require('karma-jasmine'),
            require('karma-chrome-launcher'),
            require('karma-firefox-launcher'),
            require('karma-safari-launcher'),
            require('karma-ie-launcher'),
            require('karma-phantomjs-launcher')
        ],
        files: [
            {pattern: './kotlin.js', watched: false},
            {pattern: '../js/kotlin-jasmine-core.js', watched: false},
            {pattern: './kotlin-jasmine-core-test.js', watched: false}
        ],
        proxies: {
            '/': '/base'
        },
        client: {
            clearContext: false
        },
        port: 8765,
        logLevel: config['LOG_INFO'],
        browsers: ['PhantomJS'],
        colors: false,
        singleRun: true
    })
};