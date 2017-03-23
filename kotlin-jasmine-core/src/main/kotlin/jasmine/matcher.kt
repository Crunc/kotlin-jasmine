package jasmine

external interface Matchers

typealias MatcherFactory = (util: MatcherUtils, customTesters: CustomEqualityTesters) -> dynamic

class Result(val pass: Boolean, val message: String? = null)

external interface MatcherUtils {

    fun equals(actual: Any?, expected: Any?, customTesters: CustomEqualityTesters = definedExternally): Boolean
    fun contains(haystack: Any?, needle: Any?, customTesters: CustomEqualityTesters = definedExternally): Boolean
    fun buildFailureMessage(matcherName: String, isNot: Boolean, actual: Any?, vararg expected: Any?): String
}

external interface CustomEqualityTesters

fun matchers(matcherFactories: MatcherDefinitions.() -> Unit): Matchers {

    val definitions = DynamicMatcherDefinitions()
    definitions.matcherFactories()

    @Suppress("UnsafeCastFromDynamic")
    val matchers: Matchers = definitions.matchers
    return matchers
}

interface MatcherDefinitions {
    fun <T> matcher(name: String, compare: (actual: T, expected: T, util: MatcherUtils, customTesters: CustomEqualityTesters) -> Result): Unit
    fun <T> matcher(name: String, compare: (actual: T, util: MatcherUtils, customTesters: CustomEqualityTesters) -> Result): Unit
    fun <T> matcher(name: String, compare: (actual: T, expected: T) -> Result): Unit
    fun <T> matcher(name: String, compare: (actual: T) -> Result): Unit
}

private class DynamicMatcherDefinitions : MatcherDefinitions {

    val matchers: dynamic = js("({})")

    override fun <T> matcher(name: String, compare: (actual: T, expected: T, util: MatcherUtils, customEqualityTesters: CustomEqualityTesters) -> Result): Unit {
        matchers[name] = { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->

            val matcher = js("({})")

            matcher.compare = { actual: T, expected: T ->
                compare(actual, expected, util, customEqualityTesters)
            }

            matcher
        }
    }

    override fun <T> matcher(name: String, compare: (actual: T, util: MatcherUtils, customEqualityTesters: CustomEqualityTesters) -> Result): Unit {
        matchers[name] = { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->

            val matcher = js("({})")

            matcher.compare = { actual: T ->
                compare(actual, util, customEqualityTesters)
            }

            matcher
        }
    }

    override fun <T> matcher(name: String, compare: (actual: T, expected: T) -> Result): Unit {
        matchers[name] = { _, _ ->

            val matcher = js("({})")

            matcher.compare = { actual: T, expected: T ->
                compare(actual, expected)
            }

            matcher
        }
    }

    override fun <T> matcher(name: String, compare: (actual: T) -> Result): Unit {
        matchers[name] = { _, _ ->

            val matcher = js("({})")

            matcher.compare = { actual: T ->
                compare(actual)
            }

            matcher
        }
    }
}

//@Suppress("UnsafeCastFromDynamic")
//fun <T> matcher(compare: (actual: T, expected: T, util: MatcherUtils, customEqualityTesters: CustomEqualityTesters) -> Result): MatcherFactory {
//
//    return { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->
//
//        val matcher = js("({})")
//
//        matcher.compare = { actual: T, expected: T ->
//            compare(actual, expected, util, customEqualityTesters)
//        }
//
//        matcher
//    }
//}

//@Suppress("UnsafeCastFromDynamic")
//fun <T> matcher(compare: (actual: T, util: MatcherUtils, customEqualityTesters: CustomEqualityTesters) -> Result): MatcherFactory {
//
//    return { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->
//
//        val matcher = js("({})")
//
//        matcher.compare = { actual: T ->
//            compare(actual, util, customEqualityTesters)
//        }
//
//        matcher
//    }
//}

//@Suppress("UnsafeCastFromDynamic")
//fun <T> matcher(compare: (actual: T, expected: T) -> Result): MatcherFactory {
//
//    return { _, _ ->
//
//        val matcher = js("({})")
//
//        matcher.compare = { actual: T, expected: T ->
//            compare(actual, expected)
//        }
//
//        matcher
//    }
//}

//@Suppress("UnsafeCastFromDynamic")
//fun <T> matcher(compare: (actual: T) -> Result): MatcherFactory {
//
//    return { _, _ ->
//
//        val matcher = js("({})")
//
//        matcher.compare = { actual: T ->
//            compare(actual)
//        }
//
//        matcher
//    }
//}
