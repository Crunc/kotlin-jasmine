package com.github.crunc.kotlin.jasmine.maven.conf

class KarmaPlugin(val plugin: String) : JsValue {
    override fun toJs() = "require('$plugin')"
}