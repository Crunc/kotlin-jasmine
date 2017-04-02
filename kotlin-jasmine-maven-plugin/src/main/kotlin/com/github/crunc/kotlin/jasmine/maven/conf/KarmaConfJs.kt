package com.github.crunc.kotlin.jasmine.maven.conf

import com.github.crunc.kotlin.jasmine.maven.Writable
import java.io.Writer

class KarmaConfJs(
        val basePath: String = "",
        val frameworks: List<KarmaFramework> = emptyList(),
        val plugins: List<KarmaPlugin> = emptyList(),
        val files: List<KarmaFile> = emptyList(),
        val port: Int = 9876,
        val browsers: List<KarmaBrowser> = emptyList()
) : Writable {

    private val allPlugins get() = plugins.plus(browsers.map { it.plugin })

    private val script: String get() = """
module.exports = function (config) {
  config.set({
    basePath: '$basePath',
    frameworks: ${jsArrayOf(frameworks)},
    plugins: ${jsArrayOf(allPlugins)},
    files: ${jsArrayOf(files)},
    proxies: {
      '/': '/base'
    },
    client: {
      clearContext: false
    },
    port: $port,
    logLevel: config['LOG_INFO'],
    browsers: ${jsArrayOf(browsers)},
    colors: false,
    singleRun: true
  });
}
""".trim()

    override fun writeTo(writer: Writer) =
            writer.write(script)

    private fun jsArrayOf(elements: Iterable<JsValue>, indentation: String = "\n      ", beforeFirst: String = indentation, afterLast: String = "\n    ") =
            "[$beforeFirst${elements.map { it.toJs() }.joinToString(separator = ",$indentation")}$afterLast]"
}