package com.github.crunc.kotlin.jasmine.maven.conf

class KarmaFramework(val framework: String) : JsValue {

    override fun toJs() = "'$framework'"

    companion object {

        val Jasmine = KarmaFramework("jasmine")
    }
}