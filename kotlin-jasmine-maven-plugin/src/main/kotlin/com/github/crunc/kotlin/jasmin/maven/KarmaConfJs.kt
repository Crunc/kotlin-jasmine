package com.github.crunc.kotlin.jasmin.maven

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
        val browsers: List<Browser> = listOf(Browser.PhantomJS)

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

interface JsValue {
    fun toJs(): String
}

class KarmaFramework(val framework: String) : JsValue {
    override fun toJs() = "'$framework'"
}

class KarmaPlugin(val plugin: String) : JsValue {
    override fun toJs() = "require('$plugin')"
}

class KarmaFile(val pattern: String,
                val watched: Boolean = false,
                val included: Boolean = true,
                val served: Boolean = true,
                val nocache: Boolean = false) : JsValue {

    override fun toJs() = "{ pattern: '$pattern', watched: $watched, included: $included, served: $served , nocache: $nocache }"
}

enum class Browser(val browser: String, val plugin: String) : JsValue {

    Chrome("Chrome", "karma-chrome-launcher"),

    Firefox("Firefox", "karma-firefox-launcher"),

    Safari("Safari", "karma-safari-launcher'"),

    InternetExplorer("IE", "karma-ie-launcher"),

    PhantomJS("PhantomJS", "karma-phantomjs-launcher");

    override fun toJs() = "'$browser'"

    fun toPlugin() = KarmaPlugin(plugin)
}
