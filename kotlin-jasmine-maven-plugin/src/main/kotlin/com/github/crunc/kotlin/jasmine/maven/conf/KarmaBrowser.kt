package com.github.crunc.kotlin.jasmine.maven.conf

enum class KarmaBrowser(val browser: String, val plugin: String) : JsValue {

    Chrome("Chrome", "karma-chrome-launcher"),

    Firefox("Firefox", "karma-firefox-launcher"),

    Safari("Safari", "karma-safari-launcher'"),

    InternetExplorer("IE", "karma-ie-launcher"),

    PhantomJS("PhantomJS", "karma-phantomjs-launcher");

    override fun toJs() = "'$browser'"

    fun toPlugin() = KarmaPlugin(plugin)
}