package com.github.crunc.kotlin.jasmine.maven

import java.io.Writer

interface Writable {
    fun writeTo(writer: Writer): Unit
}
