package com.github.crunc.kotlin.jasmine.maven.node

data class PackageDependency(val name: String, val version: String) {

    fun toJsonProperty(): String = "\"$name\": \"$version\""

    companion object {
        val None = PackageDependency("", "")
        val KarmaJasmine = PackageDependency("karma-jasmine", "1.1.0")
        val Karma = PackageDependency("karma", "1.4.1")
        val KarmaRequireJS = PackageDependency("karma-requirejs", "1.1.0")
        val JasmineCore = PackageDependency("jasmine-core", "2.5.2")
        val RequireJS = PackageDependency("requirejs", "2.3.3")
        val KarmaChromeLauncher = PackageDependency("karma-chrome-launcher", "2.0.0")
        val KarmaFirefoxLauncher = PackageDependency("karma-firefox-launcher", "1.0.1")
        val KarmaSafariLauncher = PackageDependency("karma-safari-launcher", "1.0.0")
        val KarmaIeLauncher = PackageDependency("karma-ie-launcher", "1.0.0")
        val KarmaPhantomJSLauncher = PackageDependency("karma-phantomjs-launcher", "1.0.4")
    }
}