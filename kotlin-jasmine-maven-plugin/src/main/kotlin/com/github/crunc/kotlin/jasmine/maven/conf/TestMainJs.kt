package com.github.crunc.kotlin.jasmine.maven.conf

import com.github.crunc.kotlin.jasmine.maven.Writable
import java.io.Writer

class TestMainJs(
        val karmaGlobal: String = "window['__karma__']",
        val requireGlobal: String = "require",
        val testFilePattern: String = "(spec|test|tests)\\.js\$",
        val karmaBaseUrl: String = "/base"
) : Writable {

    private val script: String get() = """
(function (karma, require) {

    var TEST_REGEXP = /$testFilePattern/i;
    var allTestFiles = [];

    // Get a list of all the test files to include
    Object.keys(karma.files).forEach(function (file) {

        if (TEST_REGEXP.test(file)) {

            // Normalize paths to RequireJS module names.
            // If you require sub-dependencies of test files to be loaded as-is (requiring file extension) then do not normalize the paths
            var normalizedTestModule = file.replace(/^\$karmaBaseUrl\/|\.js$/g, '');
            allTestFiles.push(normalizedTestModule);
        }
    });

    require.config({
        baseUrl: '/',
        deps: allTestFiles,
        callback: karma.start
    });

})($karmaGlobal, $requireGlobal);
""".trim()

    override fun writeTo(writer: Writer) =
            writer.write(script)
}