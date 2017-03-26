(function (karma, require) {

    var TEST_REGEXP = /(spec|test)\.js$/i;
    var allTestFiles = [];

// Get a list of all the test files to include
    Object.keys(karma.files).forEach(function (file) {
        if (TEST_REGEXP.test(file)) {
            // Normalize paths to RequireJS module names.
            // If you require sub-dependencies of test files to be loaded as-is (requiring file extension)
            // then do not normalize the paths
            var normalizedTestModule = file.replace(/^\/base\/|\.js$/g, '');
            allTestFiles.push(normalizedTestModule);
        }
    });

    require.config({
        baseUrl: '/base',
        deps: allTestFiles,
        callback: karma.start
    });
})(window['__karma__'], require);
