package com.github.crunc.kotlin.jasmine.maven.conf

class KarmaConfJs(
        val basePath: String = "",
        val frameworks: List<KarmaFramework> = listOf(
                KarmaFramework("jasmine")
        ),
        val plugins: List<KarmaPlugin> = listOf(
                KarmaPlugin("karma-jasmine")
        ),
        val files: List<KarmaFile> = listOf(
                KarmaFile(pattern = "./node_modules/requirejs/require.js", watched = false),
                KarmaFile(pattern = "./node_modules/karma-requirejs/lib/adapter.js", watched = false),
                KarmaFile(pattern = "./../js/**/!(*.meta).js", watched = false),
                KarmaFile(pattern = "./!(test-main|karma.conf|*.meta).js", watched = false),
                KarmaFile(pattern = "./!(node_modules|node)/!(*.meta).js", watched = false),
                KarmaFile(pattern = "./test-main.js", watched = false)
        ),
        val port: Int = 9876,
        val browsers: List<KarmaBrowser> = listOf(KarmaBrowser.PhantomJS)
) {

    fun toScript(): String = """module.exports = function (config) {
  config.set({
    ${configProperties()
            .flatMap { (key, value) -> listOf("$key: $value") }
            .joinToString(separator = ",\n    ")}
  });
}"""

    private val allPlugins get() = plugins.plus(browsers.map { it.toPlugin() })

    private fun configProperties(): Map<String, String> = mapOf(
            "basePath" to "'$basePath'",
            "frameworks" to jsArrayOf(frameworks),
            "plugins" to jsArrayOf(allPlugins),
            "files" to jsArrayOf(files),
            "proxies" to "{\n      '/': '/base'\n    }",
            "client" to "{\n      clearContext: false\n    }",
            "port" to "$port",
            "logLevel" to "config['LOG_INFO']",
            "browsers" to jsArrayOf(browsers),
            "colors" to "false",
            "singleRun" to "true"
    )

    private fun jsArrayOf(elements: Iterable<JsValue>, indentation: String = "\n      ", beforeFirst: String = indentation, afterLast: String = "\n    ") =
            "[$beforeFirst${elements.map { it.toJs() }.joinToString(separator = ",$indentation")}$afterLast]"
}