package jasmine

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

fun Expectations.toBeFoo(): Unit {
    this.asDynamic().toBeFoo()
}

fun Expectations.toBeBar(): Unit {
    this.asDynamic().toBeBar()
}

@Suppress("unused")
private val spec = describe("matcher") {

    beforeEach {
        jasmine.addMatchers(toBeFoo)
        jasmine.addMatchers(toBeBar)
    }

    it("should work") { ->
        expect("foo").toBeFoo()
        expect("bar").not.toBeFoo()

        expect("foo").not.toBeBar()
        expect("BAR").toBeBar()
    }
}
