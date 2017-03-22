import jasmine.*

private val toBeFoo = matcher<String>("toBeFoo") { actual, expected, util, customEqualityTesters ->

    val pass = util.equals(actual, "foo", customEqualityTesters)

    when (pass) {
        true -> Result(pass = true)
        else -> Result(pass = false, message = "$actual is not Foo")
    }
}

private val toBeBar = matcher<String>("toBeBar") { actual, expected ->

    val pass = actual.equals("bar", ignoreCase = true)

    when (pass) {
        true -> Result(pass = true)
        else -> Result(pass = false, message = "$actual is not Bar")
    }
}

private val toBeFoobar = matcher<String>("toBeFoobar") { actual ->

    val pass = actual.equals("foobar", ignoreCase = true)

    when (pass) {
        true -> Result(pass = true)
        else -> Result(pass = false, message = "$actual is not Foobar")
    }
}

private val toBeBaz = matcher<String>("toBeBaz") { actual, util, customEqualityTesters ->

    val pass = util.equals(actual, "baz", customEqualityTesters)

    when (pass) {
        true -> Result(pass = true)
        else -> Result(pass = false, message = "$actual is not Baz")
    }
}

fun Expectations<String>.toBeFoo(): Unit {
    this.asDynamic().toBeFoo()
}

fun Expectations<String>.toBeBar(): Unit {
    this.asDynamic().toBeBar()
}

fun Expectations<String>.toBeFoobar(): Unit {
    this.asDynamic().toBeFoobar()
}

fun Expectations<String>.toBeBaz(): Unit {
    this.asDynamic().toBeBaz()
}

@Suppress("unused")
private val spec = describe("matcher") {

    beforeEach {
        jasmine.addMatchers(toBeFoo)
        jasmine.addMatchers(toBeBar)
        jasmine.addMatchers(toBeFoobar)
        jasmine.addMatchers(toBeBaz)
    }

    it("should work") { ->
        expect("foo").toBeFoo()
        expect("bar").not.toBeFoo()

        expect("foo").not.toBeBar()
        expect("BAR").toBeBar()

        expect("baz").not.toBeFoobar()
        expect("FooBar").toBeFoobar()

        expect("foobar").not.toBeBaz()
        expect("baz").toBeBaz()
    }
}
