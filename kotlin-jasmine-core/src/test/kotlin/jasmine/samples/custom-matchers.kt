package jasmine.samples

import jasmine.*

private const val toBeFoo = "toBeFoo"
private const val toSharePrefixWith = "toSharePrefixWith"

/**
 * A matcher registration object that can be used with `jasmine.addMatchers`.
 */
private val customMatchers = matchers {

    /*
     * A simple matcher with a compare function that checks the actual value.
     */
    matcher(toBeFoo) { actual: String ->
        when (equals(actual, "Foo")) {
            true -> pass()
            else -> fail(actual, "Foo")
        }
    }

    /*
     * A complex matcher with a `negativeCompare` function that is called then `not` is used.
     */
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

/**
 * Registers the simple matcher by name with an extension function so the fluent `expect` API
 * can be used in a type safe way.
 */
private fun Expectations<String>.toBeFoo() = match(toBeFoo)

/**
 * Registers the complex matcher by name with an extension function, so the fluent `expect` API
 * can be used in a type safe way.
 */
private fun Expectations<String>.toSharePrefixWith(expected: String, context: Int) = match(toSharePrefixWith, expected, context)

@Suppress("unused")
private val spec = describe("custom matchers") {

    beforeEach({ ->

        /*
         * Registers the custom matchers with Jasmine so they can be used from tests.
         */
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
