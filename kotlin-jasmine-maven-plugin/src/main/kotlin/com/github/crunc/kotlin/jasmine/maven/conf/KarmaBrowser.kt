package com.github.crunc.kotlin.jasmine.maven.conf

import com.github.crunc.kotlin.jasmine.maven.node.PackageDependency

enum class KarmaBrowser(val browser: String, val dependency: PackageDependency) : JsValue {

    Chrome("Chrome", PackageDependency.KarmaChromeLauncher),

    Firefox("Firefox", PackageDependency.KarmaFirefoxLauncher),

    Safari("Safari", PackageDependency.KarmaSafariLauncher),

    InternetExplorer("IE", PackageDependency.KarmaIeLauncher),

    PhantomJS("PhantomJS", PackageDependency.KarmaPhantomJSLauncher),

    Unknown("unknown", PackageDependency.None);

    override fun toJs() = "'$browser'"

    val plugin: KarmaPlugin get() = KarmaPlugin(dependency.name)

    companion object {
        fun parse(value: String): KarmaBrowser {

            val normalized = value.trim().toLowerCase()

            return values().firstOrNull {
                normalized == it.browser.toLowerCase()
            } ?: Unknown
        }
    }
}