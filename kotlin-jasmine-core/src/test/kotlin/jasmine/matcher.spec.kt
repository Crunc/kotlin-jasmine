import jasmine.*

private const val toBeFoo = "toBeFoo"

private val matchers = matchers {

    matcher(toBeFoo) { actual: String ->

        val expected = "Foo"

        when (equals(actual, expected)) {
            true -> pass()
            else -> fail(actual, expected)
        }
    }

    matcher(name = "toBeBar",
            compare = { actual: String ->

                when (actual.equals("bar", ignoreCase = true)) {
                    true -> pass()
                    else -> failWithMessage("$actual is not Bar")
                }
            })

    matcher(name = "toBeFoobar",
            compare = { actual: String ->

                when (actual.equals("foobar", ignoreCase = true)) {
                    true -> pass()
                    else -> failWithMessage("$actual is not Foobar")
                }
            })

    matcher(name = "toBeBaz",
            compare = { actual: String ->

                val expected = "baz"

                when (equals(actual, expected)) {
                    true -> pass()
                    else -> fail(actual, expected)
                }
            })

    matcher(name = "times42ToEqual",
            compare = { actual: Int, expected: Int ->

                when (equals(actual * 42, expected)) {
                    true -> pass()
                    else -> fail(actual, expected)
                }
            })

    matcher(name = "toBeProductOf",
            compare = { actual: Double, expected: Double, context: Double ->

                when (actual == expected * context) {
                    true -> pass()
                    else -> failWithMessage("$actual is not the product of $expected * $context")
                }
            })

    matcher(name = "toBeSumOf",
            compare = { actual: Int, expected: Int, context: Int ->

                when (equals(actual, expected + context)) {
                    true -> pass()
                    else -> fail(actual, expected, context)
                }
            })
}

private fun Expectations<String>.toBeFoo(): Unit
        = match(toBeFoo)

private fun Expectations<String>.toBeBar(): Unit
        = match("toBeBar")

private fun Expectations<String>.toBeFoobar(): Unit
        = match("toBeFoobar")

private fun Expectations<String>.toBeBaz(): Unit
        = match("toBeBaz")

private fun Expectations<Int>.times42ToEqual(expected: Int): Unit
        = match("times42ToEqual", expected)

private fun Expectations<Double>.toBeProductOf(factor1: Double, factor2: Double): Unit
        = match("toBeProductOf", factor1, factor2)

private fun Expectations<Int>.toBeSumOf(op1: Int, op2: Int): Unit
        = match("toBeSumOf", op1, op2)

@Suppress("unused")
private val spec = describe("matcher") {

    beforeEach { ->
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
