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
            compare = { actual: String, util: MatcherUtils, customTesters: CustomEqualityTesters ->

                val expected = "baz"
                val pass = util.equals(actual, expected, customTesters)

                when (pass) {
                    true -> Result(pass = true)
                    else -> Result(pass = false, message = util.buildFailureMessage("toBeBaz", false, actual, expected))
                }
            })

    matcher(name = "times42ToEqual",
            compare = { actual: Int, expected: Int, util: MatcherUtils, customTesters: CustomEqualityTesters ->

                val pass = util.equals(actual * 42, expected, customTesters)

                when (pass) {
                    true -> Result(pass = true)
                    else -> Result(pass = false, message = util.buildFailureMessage("times42ToEqual", false, actual, expected))
                }
            })

    matcher(name = "toBeProductOf",
            compare = { actual: Double, expected: Double, context: Double ->

                val pass = actual == expected * context

                when (pass) {
                    true -> Result(pass = true)
                    else -> Result(pass = false, message = "$actual is not the product of $expected * $context")
                }
            })

    matcher(name = "toBeSumOf",
            compare = { actual: Int, expected: Int, context: Int, util, customTesters ->

                val pass = util.equals(actual, expected + context, customTesters)

                when (pass) {
                    true -> Result(pass = true)
                    else -> Result(pass = false, message = util.buildFailureMessage("toBeSumOf", false, actual, expected, context))
                }
            })
}

fun Expectations<String>.toBeFoo(): Unit
        = match("toBeFoo")

fun Expectations<String>.toBeBar(): Unit
        = match("toBeBar")

fun Expectations<String>.toBeFoobar(): Unit
        = match("toBeFoobar")

fun Expectations<String>.toBeBaz(): Unit
        = match("toBeBaz")

fun Expectations<Int>.times42ToEqual(expected: Int): Unit
        = match("times42ToEqual", expected)

fun Expectations<Double>.toBeProductOf(factor1: Double, factor2: Double): Unit
        = match("toBeProductOf", factor1, factor2)

fun Expectations<Int>.toBeSumOf(op1: Int, op2: Int): Unit
        = match("toBeSumOf", op1, op2)

@Suppress("unused")
private val spec = describe("matcher") {

    beforeEach {
        jasmine.addMatchers(matchers)
    }

    describe("toBeFoo") {

        it("should work") { ->
            expect("Foo").toBeFoo()
        }
    }

    describe("not.toBeFoo") {

        it("should work") { ->
            expect("bar").not.toBeFoo()
        }
    }

    describe("toBeBar") {

        it("should work") { ->
            expect("BAR").toBeBar()
        }
    }

    describe("not.toBeBar") {

        it("should work") { ->
            expect("foo").not.toBeBar()
        }
    }

    describe("toBeFoobar") {

        it("should work") { ->
            expect("FooBar").toBeFoobar()
        }
    }

    describe("not.toBeFoobar") {

        it("should work") { ->
            expect("baz").not.toBeFoobar()
        }
    }

    describe("toBeBaz") {

        it("should work") { ->
            expect("baz").toBeBaz()
        }
    }

    describe("not.toBeBaz") {

        it("should work") { ->
            expect("foobar").not.toBeBaz()
        }
    }

    describe("times42ToEqual") {

        it("should work") { ->
            expect(2).times42ToEqual(84)
        }
    }

    describe("not.times42ToEqual") {

        it("should work") { ->
            expect(3).not.times42ToEqual(84)
        }
    }

    describe("toBeProductOf") {

        it("should work") { ->
            expect(16.0).toBeProductOf(4.0, 4.0)
        }
    }

    describe("not.toBeProductOf") {

        it("should work") { ->
            expect(16.1).not.toBeProductOf(4.0, 4.0)
        }
    }

    describe("toBeSumOf") {

        it("should work") { ->
            expect(42).toBeSumOf(40, 2)
        }
    }

    describe("not.toBeSumOf") {

        it("should work") { ->
            expect(42).not.toBeSumOf(40, 1)
        }
    }
}
