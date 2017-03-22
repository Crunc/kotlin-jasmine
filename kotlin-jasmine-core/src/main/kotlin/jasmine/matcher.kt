package jasmine

external interface Matchers

external interface Matcher<in T> {
    fun compare(actual: T, expected: T): Result
}

class Result(val pass: Boolean, val message: String? = null)

external interface MatcherUtils {

    fun equals(actual: Any?, expected: Any?, customEqualityTesters: CustomEqualityTesters): Boolean
}

external interface CustomEqualityTesters

typealias PartialCompareFunction<T> = (actual: T,
                                       expected: T) -> Result

typealias FullCompareFunction<T> = (actual: T,
                                    expected: T,
                                    util: MatcherUtils,
                                    customEqualityTesters: CustomEqualityTesters) -> Result

@Suppress("UnsafeCastFromDynamic")
fun <T> matcher(name: String, compare: FullCompareFunction<T>): Matchers {

    val matchers = js("({})")
    matchers[name] = { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->

        val matcher = js("({})")

        matcher.compare = { actual: T, expected: T ->
            compare(actual, expected, util, customEqualityTesters)
        }

        matcher
    }
    return matchers
}

@Suppress("UnsafeCastFromDynamic")
fun <T> matcher(name: String, compare: PartialCompareFunction<T>): Matchers {

    val matchers = js("({})")
    matchers[name] = {

        val matcher = js("({})")

        matcher.compare = { actual: T, expected: T ->
            compare(actual, expected)
        }

        matcher
    }
    return matchers
}
