package jasmine.samples

import jasmine.*

private const val toBeFoo = "toBeFoo"

private val customMatchers = matchers {
    matcher(toBeFoo) { actual: String ->
        when (equals(actual, "Foo")) {
            true -> pass()
            else -> fail(actual, "Foo")
        }
    }
}

private fun Expectations<String>.toBeFoo() = match(toBeFoo)

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
}
