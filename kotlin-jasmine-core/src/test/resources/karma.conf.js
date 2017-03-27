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
            {pattern: './node_modules/requirejs/require.js', watched: false},
            {pattern: './node_modules/karma-requirejs/lib/adapter.js', watched: false},
            {pattern: './../js/**/!(*.meta).js', watched: false},
            {pattern: './!(test-main|karma.conf|*.meta).js', watched: false},
            {pattern: './!(node_modules|node)/!(*.meta).js', watched: false},
            {pattern: './test-main.js', watched: false}
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