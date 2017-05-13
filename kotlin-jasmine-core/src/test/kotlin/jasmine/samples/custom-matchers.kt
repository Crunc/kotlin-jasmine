package jasmine.samples

import jasmine.*

private const val toBeFoo = "toBeFoo"
private const val toSharePrefixWith = "toSharePrefixWith"

private val customMatchers = matchers {
    matcher(toBeFoo) { actual: String ->
        when (equals(actual, "Foo")) {
            true -> pass()
            else -> fail(actual, "Foo")
        }
    }

    matcher(name = toSharePrefixWith,
            compare = { actual: String, expected: String, context: Int ->

                console.log("compare")

                val actualToken = actual.substring(0, context)
                val expectedToken = expected.substring(0, context)

                when (equals(actualToken, expectedToken)) {
                    true -> pass()
                    else -> fail("$actual doesn't share prefix with $expected of length $context")
                }
            },
            negativeCompare = { actual: String, expected: String, context: Int ->

                console.log("negativeCompare")

                val actualToken = actual.substring(0, context)
                val expectedToken = expected.substring(0, context)

                when (equals(actualToken, expectedToken)) {
                    true -> fail("$actual shares prefix with $expected of length $context")
                    else -> pass()
                }
            }
    )
}

private fun Expectations<String>.toBeFoo() = match(toBeFoo)
private fun Expectations<String>.toSharePrefixWith(expected:String, length:Int) =
        match(toSharePrefixWith, expected, length)

@Suppress("unused")
private val spec = describe("custom matchers") {

    beforeEach({ ->
        jasmine.addMatchers(customMatchers)
    })

    it("Foo should be Foo") { ->
        expect("Foo").toBeFoo()
    }

    it("Bar should not be Foo") { ->
        expect("Bar").not.toBeFoo()
    }

    it("compare") { ->
        expect("Foo").toSharePrefixWith("Foobar", 3)
    }

    it("negativeCompare") { ->
        expect("Bar").not.toSharePrefixWith("Foobar", 3)
    }
}
