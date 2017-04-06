package com.github.crunc.kotlin.jasmine.maven.conf

import com.github.crunc.kotlin.jasmine.maven.Writable
import java.io.Writer

class TestMainJs(
        val karmaGlobal: String = "window['__karma__']",
        val requireGlobal: String = "require",
        val testFilePattern: String = "(spec|test|tests)\\.js\$",
        val karmaBaseUrl: String = "/base",
        val requireBaseUrl: String = "/"
) : Writable {

    private val script: String get() = """
(function (karma, require) {

    var TEST_REGEXP = /$testFilePattern/i;
    var deps = [];

    function normalize(file) {
      return file.replace(/^\$karmaBaseUrl\/|\.js$/g, '');
    }

    Object.keys(karma.files).forEach(function (file) {
        if (TEST_REGEXP.test(file)) {
            deps.push(normalize(file));
        }
    });

    require.config({
        baseUrl: '$requireBaseUrl',
        deps: deps,
        callback: karma.start
    });

})($karmaGlobal, $requireGlobal);
""".trim()

    override fun writeTo(writer: Writer) =
            writer.write(script)
}