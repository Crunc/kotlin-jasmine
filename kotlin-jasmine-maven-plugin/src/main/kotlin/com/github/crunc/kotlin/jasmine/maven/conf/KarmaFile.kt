package com.github.crunc.kotlin.jasmine.maven.conf

import java.nio.file.Path

class KarmaFile(val pattern: Path,
                val watched: Boolean = false,
                val included: Boolean = true,
                val served: Boolean = true,
                val nocache: Boolean = false) : JsValue {

    override fun toJs() = "{ pattern: '$pattern', watched: $watched, included: $included, served: $served , nocache: $nocache }"
}