package jasmine

/**
 * An object that contains matcher names as property keys and matcher factory functions as values.
 */
external interface Matchers

/**
 * Jasmine matcher utils.
 */
external interface MatcherUtils {

    /**
     * Compares two values for equality using custom equality testers.
     */
    fun equals(actual: Any?, expected: Any?, customTesters: CustomEqualityTesters = definedExternally): Boolean

    /**
     * Checks whether the haystack contains the needle. Works for Sets, Arrays and Strings.
     */
    fun contains(haystack: Any?, needle: Any?, customTesters: CustomEqualityTesters = definedExternally): Boolean

    /**
     * Creates a failure message in the form `expected <actual> <text(matcherName)> [<expected>, ...]`
     */
    fun buildFailureMessage(matcherName: String, isNot: Boolean, actual: Any?, vararg expected: Any?): String
}

/**
 * Jasmine custom equality testers.
 */
external interface CustomEqualityTesters

/**
 * DSL entry point for defining custom matchers.
 */
fun matchers(matcherFactories: MatcherDefinitions.() -> Unit): Matchers {
    val definitions = DynamicMatcherDefinitions()
    definitions.matcherFactories()
    return definitions.matchers
}

/**
 * The result of a matcher compare function.
 */
@Suppress("unused")
class Result(val pass: Boolean, val message: String? = null)

/**
 * DSL for defining custom Jasmine matchers.
 */
interface MatcherDefinitions {

    /**
     * All defined matchers
     */
    val matchers: Matchers

    /**
     * Defines a new matcher that compares an actual value to an expected value  using the given context value, [MatcherUtils] and [CustomEqualityTesters].
     */
    fun <T, C> matcher(name: String, compare: (actual: T, expected: T, context: C, util: MatcherUtils, customTesters: CustomEqualityTesters) -> Result): Unit

    /**
     * Defines a new matcher that compares an actual value to an expected value using [MatcherUtils] and [CustomEqualityTesters].
     */
    fun <T> matcher(name: String, compare: (actual: T, expected: T, util: MatcherUtils, customTesters: CustomEqualityTesters) -> Result): Unit

    /**
     * Defines a new matcher that checks an actual value using [MatcherUtils] and [CustomEqualityTesters].
     */
    fun <T> matcher(name: String, compare: (actual: T, util: MatcherUtils, customTesters: CustomEqualityTesters) -> Result): Unit

    /**
     * Defines a new matcher that compares an actual value to an expected value using the given context value.
     */
    fun <T, C> matcher(name: String, compare: (actual: T, expected: T, context: C) -> Result): Unit

    /**
     * Defines a new matcher that compares an actual value to an expected value.
     */
    fun <T> matcher(name: String, compare: (actual: T, expected: T) -> Result): Unit

    /**
     * Defines a new matcher that checks an actual value.
     */
    fun <T> matcher(name: String, compare: (actual: T) -> Result): Unit
}

/**
 * Implementation of [MatcherDefinitions] that collects all matcher definitions in a simple JS object with the matcher names as keys.
 */
private class DynamicMatcherDefinitions : MatcherDefinitions {

    /**
     * Simple JS object containing all matcher definitions with the matcher names as keys.
     */
    private val matcherDefinitions: dynamic = js("({})")

    @Suppress("UnsafeCastFromDynamic")
    override val matchers: Matchers
        get() = matcherDefinitions

    override fun <T, C> matcher(name: String, compare: (actual: T, expected: T, context: C, util: MatcherUtils, customEqualityTesters: CustomEqualityTesters) -> Result): Unit {
        matcherDefinitions[name] = { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->

            val matcher = js("({})")

            matcher.compare = { actual: T, expected: T, context: C ->
                compare(actual, expected, context, util, customEqualityTesters)
            }

            matcher
        }
    }

    override fun <T> matcher(name: String, compare: (actual: T, expected: T, util: MatcherUtils, customEqualityTesters: CustomEqualityTesters) -> Result): Unit {
        matcherDefinitions[name] = { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->

            val matcher = js("({})")

            matcher.compare = { actual: T, expected: T ->
                compare(actual, expected, util, customEqualityTesters)
            }

            matcher
        }
    }

    override fun <T> matcher(name: String, compare: (actual: T, util: MatcherUtils, customEqualityTesters: CustomEqualityTesters) -> Result): Unit {
        matcherDefinitions[name] = { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->

            val matcher = js("({})")

            matcher.compare = { actual: T ->
                compare(actual, util, customEqualityTesters)
            }

            matcher
        }
    }

    override fun <T, C> matcher(name: String, compare: (actual: T, expected: T, context: C) -> Result): Unit {
        matcherDefinitions[name] = { _, _ ->

            val matcher = js("({})")

            matcher.compare = { actual: T, expected: T, context: C ->
                compare(actual, expected, context)
            }

            matcher
        }
    }

    override fun <T> matcher(name: String, compare: (actual: T, expected: T) -> Result): Unit {
        matcherDefinitions[name] = { _, _ ->

            val matcher = js("({})")

            matcher.compare = { actual: T, expected: T ->
                compare(actual, expected)
            }

            matcher
        }
    }

    override fun <T> matcher(name: String, compare: (actual: T) -> Result): Unit {
        matcherDefinitions[name] = { _, _ ->

            val matcher = js("({})")

            matcher.compare = { actual: T ->
                compare(actual)
            }

            matcher
        }
    }
}