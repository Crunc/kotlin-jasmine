package com.github.crunc.kotlin.jasmin.maven

import junit.framework.TestCase

class KarmaConfJsTest : TestCase() {

    fun testJs() {

        // given
        val conf = KarmaConfJs()

        // when
        val js = conf.toScript()

        //then
        println(js)
        assertNotNull(js)
    }
}