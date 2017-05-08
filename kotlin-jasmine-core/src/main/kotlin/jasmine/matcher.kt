package jasmine

/**
 * An object that contains matcher names as property keys and matcher factory functions as values.
 */
external interface MatcherRegistrations

/**
 * Jasmine matcher utils.
 */
external internal interface MatcherUtils {

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
fun matchers(matcherFactories: MatcherDefinitions.() -> Unit): MatcherRegistrations {
    val definitions = DynamicMatcherDefinitions()
    definitions.matcherFactories()
    return definitions.matcherRegistrations
}

/**
 * The result of a matcher compare function.
 *
 * @property pass Indicates whether the match was positive (`true`) or negative/mismatch (`false`).
 * @property message The error message describing the mismatch in case `pass` is `false`.
 */
@Suppress("unused")
class Result(val pass: Boolean, val message: String? = null)

/**
 * Receiver for matcher compare functions.
 */
interface Comparison {

    /**
     * Creates a positive matching result.
     */
    fun pass(): Result

    /**
     * Creates a negative matching result for the given actual value.
     */
    fun fail(actual: Any?): Result

    /**
     * Creates a negative matching result for the given actual and expected values.
     */
    fun fail(actual: Any?, vararg expected: Any?): Result

    /**
     * Creates a negative matching result with the given message.
     */
    fun failWithMessage(message: String): Result

    /**
     * Compares the given actual and expected values for equality.
     */
    fun equals(actual: Any?, expected: Any?): Boolean

    /**
     * Determines whether the given needle is contained in the given haystack.
     */
    fun contains(haystack: Any?, needle: Any?): Boolean
}

private sealed class BaseComparison(private val matcherName: String,
                                    private val util: MatcherUtils,
                                    private val customTesters: CustomEqualityTesters,
                                    private val isNot: Boolean) : Comparison {

    override fun pass(): Result =
            Result(pass = true)

    override fun fail(actual: Any?): Result =
            Result(pass = false, message = util.buildFailureMessage(matcherName, isNot, actual))

    override fun fail(actual: Any?, vararg expected: Any?): Result =
            Result(pass = false, message = util.buildFailureMessage(matcherName, isNot, actual, expected))

    override fun failWithMessage(message: String): Result =
            Result(pass = false, message = message)

    override fun equals(actual: Any?, expected: Any?): Boolean =
            util.equals(actual, expected, customTesters)

    override fun contains(haystack: Any?, needle: Any?): Boolean =
            util.contains(haystack, needle, customTesters)

}

private class PositiveComparison(
        matcherName: String,
        util: MatcherUtils,
        customTesters: CustomEqualityTesters) : BaseComparison(matcherName, util, customTesters, false)

/**
 * DSL for defining custom Jasmine matchers.
 */
interface MatcherDefinitions {

    /**
     * All defined matchers
     */
    val matcherRegistrations: MatcherRegistrations

    /**
     * Defines a new matcher that compares an actual value to an expected value using the given context value.
     */
    fun <T, C> matcher(name: String, compare: Comparison.(actual: T, expected: T, context: C) -> Result): Unit

    /**
     * Defines a new matcher that compares an actual value to an expected value.
     */
    fun <T> matcher(name: String, compare: Comparison.(actual: T, expected: T) -> Result): Unit

    /**
     * Defines a new matcher that checks an actual value.
     */
    fun <T> matcher(name: String, compare: Comparison.(actual: T) -> Result): Unit
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
    override val matcherRegistrations: MatcherRegistrations
        get() = matcherDefinitions

    override fun <T, C> matcher(name: String, compare: Comparison.(actual: T, expected: T, context: C) -> Result): Unit {

        matcherDefinitions[name] = { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->

            val matcher = js("({})")

            matcher.compare = { actual: T, expected: T, context: C ->
                PositiveComparison(name, util, customEqualityTesters)
                        .compare(actual, expected, context)
            }

            matcher
        }
    }

    override fun <T> matcher(name: String, compare: Comparison.(actual: T, expected: T) -> Result): Unit {

        matcherDefinitions[name] = { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->

            val matcher = js("({})")

            matcher.compare = { actual: T, expected: T ->
                PositiveComparison(name, util, customEqualityTesters)
                        .compare(actual, expected)
            }

            matcher
        }
    }

    override fun <T> matcher(name: String, compare: Comparison.(actual: T) -> Result): Unit {

        matcherDefinitions[name] = { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->

            val matcher = js("({})")

            matcher.compare = { actual: T ->
                PositiveComparison(name, util, customEqualityTesters)
                        .compare(actual)
            }

            matcher
        }
    }
}