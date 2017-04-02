package com.github.crunc.kotlin.jasmine.maven

import com.github.crunc.kotlin.jasmine.maven.conf.KarmaConfJs
import junit.framework.TestCase
import java.io.StringWriter

class KarmaConfJsTest : TestCase() {

    fun testJs() {

        // given
        val conf = KarmaConfJs()

        // when
        val out = StringWriter()
        out.use(conf::writeTo)
        val js = out.toString()

        //then
        println(js)
        assertNotNull(js)
    }
}