# Custom matchers

## Built in matchers

TODO

## Defining custom matchers with the `matchers` DSL

There is no way to provide type safe Kotlin bindings for the Jasmine way of defining custom matchers. Hence it is necessary to have some abstractions on top of the Jasmine framework for defining matchers: The [`matchers`][jasmine.matchers] DSL.

### Basic usage

[`matchers`][jasmine.matchers] takes a function with receiver type [`MatcherDefinitions`][jasmine.MatcherDefinitions] as it's only argument and returns a [`MatcherRegistrations`][jasmine.MatcherRegistrations]. This makes it possible to call one of the `matcher` functions from within a [`matchers`][jasmine.matchers] block, to define new matchers.

```Kotlin
import jasmine.*

val customMatchers = matchers {

  matcher(/* ... */) // first matcher
  matcher(/* ... */) // second matcher
  matcher(/* ... */) // and so on ...

}
```

The resulting [`MatcherRegistrations`][jasmine.MatcherRegistrations] can then be added to Jasmine.

```Kotlin
import jasmine.*

val customMatchers = matchers { /* ... */ }

val spec = describe("something") {

  beforeEach({ ->
    jasmine.addMatchers(customMatchers)
  })
  
  // Tests using the custom matchers ...
}
```
  
### Defining the simplest possible custom matcher

The simplest possible matcher is one which takes the actual value that has been passed to [`expect`][jasmine.expect] and checks whether it meets the matcher's requirements. As an example we will define a matcher `toBeFoo` that checks whether the actual value is a string equal to `"Foo"`.

```Kotlin
import jasmine.*

val customMatchers = matchers {

    matcher(
        name = "toBeFoo",
        compare = { actual: String ->
            when (actual == "Foo") {
                true -> pass()
                else -> fail(actual, "Foo")
            }
        }
    )
    
}
```

Alternatively you can write the matcher definition in a more DSL-like way, the functionality stays the same (This is basic Kotlin syntax, nothing more to say about it).

```Kotlin
import jasmine.*

val customMatchers = matchers {

    matcher("toBeFoo") { actual: String ->
        when (actual == "Foo") {
            true -> pass()
            else -> fail(actual, "Foo")
        }
    }
    
}
```

As you can see the matcher is given a name `"toBeFoo"` and a compare function with a single argument `actual`. What's not so obvious is the fact, that the `compare` functions receiver type is [`Comparison`][jasmine.Comparison]. It provides some very helpful utility functions we can use from within `compare`:

* `pass()` returns a [`Result`][jasmine.Result] which indicates the value was matched successfully.
* `fail(actual: Any? [, expected: Any?])` returns a [`Result`][jasmine.Result] which indicates the value did not match and provides a readable error message (internally it delegates to Jasmine's `buildFailureMessage` function from `matcherUtils`).
* `failWithMessage(message:String)` returns a [`Result`][jasmine.Result] which indicates the value did not match and provides the given error message.

Additionally it exposes Jasmine's `matcherUtils`:

* `equals(actual: Any?, expected: Any?)` compares the given values using [custom equality testers](./custom-equality-testers.md).
* `contains(haystack: Any?, needle: Any?)` checks whether the given haystack contains the given needle, where haystack can be an `Array` or a `String`.

So in order to use the custom equality testers we can refactor the matcher to use `equals` from [`Comparison`][jasmine.Comparison].

```Kotlin
import jasmine.*

val customMatchers = matchers {

    matcher("toBeFoo") { actual: String ->
        when (equals(actual, "Foo")) {
            true -> pass()
            else -> fail(actual, "Foo")
        }
    }
    
}
```

### Using custom matchers in tests

When we try to call our custom matcher `toBeFoo` the Jasmine way, we get a compile error.

```Kotlin
import jasmine.*

val customMatchers = matchers { 
  matcher("toBeFoo") { /* ... */ }
}

val spec = describe("custom matchers") {

  beforeEach({ ->
    jasmine.addMatchers(customMatchers)
  })
  
  it("should be foo") { ->
    expect("Foo").toBeFoo() // COMPILE ERROR 
  }
}
```

We have to extend the [`expect`][jasmine.expect] function to be able to call our matcher. [`expect`][jasmine.expect] returns an instance of [`Expectations<T>`][jasmine.Expectations] where `T` is the type of the actual value. So we can just write an extension function for `Expectations<String>` to make the Kotlin compiler recognize our new matcher.

```Kotlin
import jasmine.*

val customMatchers = matchers { 
  matcher("toBeFoo") { /* ... */ }
}

fun Expectations<String>.toBeFoo() = match("toBeFoo")
```

To keep the developers from having to deal with `dynamic` types we provide the `Expectations<T>.match` function. It takes the exact name of the matcher as it's first argument and optionally up to three more values (actual, expected, context but more to that later).

It is a good practice to make the matcher name a constant value to avoid typos when referencing the matcher name in the `match` function.

```Kotlin
import jasmine.*

private const val toBeFoo = "toBeFoo"

val customMatchers = matchers { 
  matcher(toBeFoo) { /* ... */ }
}

fun Expectations<String>.toBeFoo() = match(toBeFoo)
```

Here is the [full example](../kotlin-jasmine-core/src/test/kotlin/jasmine/samples/custom-matchers.kt) of defining and using a simple custom matcher. Note that we made everything private in order to not leak into other tests.

```Kotlin
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
```

[jasmine.Comparison]: ../kotlin-jasmine-core/src/main/kotlin/jasmine/Comparison.kt
[jasmine.expect]: ../kotlin-jasmine-core/src/main/kotlin/jasmine/expect.kt
[jasmine.Expectations]: ../kotlin-jasmine-core/src/main/kotlin/jasmine/Expectations.kt
[jasmine.matchers]: ../kotlin-jasmine-core/src/main/kotlin/jasmine/matchers.kt
[jasmine.MatcherDefinitions]: ../kotlin-jasmine-core/src/main/kotlin/jasmine/MatcherDefinitions.kt
[jasmine.MatcherRegistrations]: ../kotlin-jasmine-core/src/main/kotlin/jasmine/MatcherRegistrations.kt
[jasmine.Result]: ../kotlin-jasmine-core/src/main/kotlin/jasmine/Result.kt