// var webpackConfig = require('./webpack.config.js');
// webpackConfig.resolve.modules.push('./js-tests/kotlin-jasmine-core-tests.js');

module.exports = function (config) {
    config.set({
        basePath: './',
        frameworks: [
            'jasmine'
        ],
        plugins: [
            'karma-jasmine',
            'karma-phantomjs-launcher',
            // 'karma-webpack'
        ],
        files: [
            'node_modules_imported/kotlin/kotlin.js',
            'js/kotlin-jasmine-core.js',
            'js-tests/kotlin-jasmine-core-tests.js'
        ],
        client: {
            clearContext: false
        },
        port: 9876,
        runnerPort: 9100,
        logLevel: config['LOG_INFO'],
        browsers: [
            'PhantomJS'
        ],
        // preprocessors: {
        //     'js-tests/kotlin-jasmine-core-tests.js': ['webpack']
        // },
        colors: false,
        singleRun: false
    });
};