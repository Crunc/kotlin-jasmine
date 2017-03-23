import jasmine.*

private val matchers = matchers {

    matcher(name = "toBeFoo",
            compare = { actual: String, _: String, util, customTesters ->

                val expected = "Foo"
                val pass = util.equals(actual, expected, customTesters)

                when (pass) {
                    true -> Result(pass = true)
                    else -> Result(pass = false, message = util.buildFailureMessage("toBeFoo", false, actual, expected))
                }
            })

    matcher(name = "toBeBar",
            compare = { actual: String, _: String ->

                val pass = actual.equals("bar", ignoreCase = true)

                when (pass) {
                    true -> Result(pass = true)
                    else -> Result(pass = false, message = "$actual is not Bar")
                }
            })

    matcher(name = "toBeFoobar",
            compare = { actual: String ->

                val pass = actual.equals("foobar", ignoreCase = true)

                when (pass) {
                    true -> Result(pass = true)
                    else -> Result(pass = false, message = "$actual is not Foobar")
                }
            })

    matcher(name = "toBeBaz",
            compare = { actual: String, util, customTesters ->

                val expected = "baz"
                val pass = util.equals(actual, expected, customTesters)

                when (pass) {
                    true -> Result(pass = true)
                    else -> Result(pass = false, message = util.buildFailureMessage("toBeBaz", false, actual, expected))
                }
            })

    matcher(name = "times42ToEqual",
            compare = { actual: Int, expected: Int, util, customTesters ->

                val pass = util.equals(actual * 42, expected, customTesters)

                when (pass) {
                    true -> Result(pass = true)
                    else -> Result(pass = false, message = util.buildFailureMessage("times42ToEqual", false, actual, expected))
                }
            })
}

fun StringExpectations.toBeFoo(): Unit {
    this.asDynamic().toBeFoo()
}

fun StringExpectations.toBeBar(): Unit {
    this.asDynamic().toBeBar()
}

fun StringExpectations.toBeFoobar(): Unit {
    this.asDynamic().toBeFoobar()
}

fun StringExpectations.toBeBaz(): Unit {
    this.asDynamic().toBeBaz()
}

fun IntExpectations.times42ToEqual(expected: Int): Unit {
    this.asDynamic().times42ToEqual(expected)
}

@Suppress("unused")
private val spec = describe("matcher") {

    beforeEach {
        jasmine.addMatchers(matchers)
    }

    it("should work") { ->
        expect("Foo").toBeFoo()
        expect("bar").not.toBeFoo()

        expect("foo").not.toBeBar()
        expect("BAR").toBeBar()

        expect("baz").not.toBeFoobar()
        expect("FooBar").toBeFoobar()

        expect("foobar").not.toBeBaz()
        expect("baz").toBeBaz()

        expect(2).times42ToEqual(84)
        expect(3).not.times42ToEqual(84)
    }
}
