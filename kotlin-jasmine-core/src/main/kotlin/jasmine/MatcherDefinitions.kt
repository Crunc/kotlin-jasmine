package jasmine

/*
 * MIT License
 *
 * Copyright (c) 2017 Hauke Jaeger http://yegair.io
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * A function that compares an actual to an expected value, considering the given context.
 */
private typealias CompareWithContext<T, C> = Comparison.(actual: T, expected: T, context: C) -> Result

/**
 * A function that compares an actual to an expected value.
 */
private typealias Compare<T> = Comparison.(actual: T, expected: T) -> Result

/**
 * A function that checks an actual value.
 */
private typealias Check<T> = Comparison.(actual: T) -> Result

/**
 * DSL for defining custom Jasmine matchers.
 */
interface MatcherDefinitions {

    /**
     * All defined matchers
     */
    val matcherRegistrations: MatcherRegistrations

    /**
     * Defines a new matcher that compares an `actual` value to an `expected` value using the given `context` value.
     */
    fun <T, C> matcher(name: String, compare: CompareWithContext<T, C>): Unit

    /**
     * Defines a new matcher that compares an `actual` value to an `expected` value using the given `context` value.
     * If the matcher is chained with a `not` expectation, the `negativeCompare` function is used.
     */
    fun <T, C> matcher(name: String, compare: CompareWithContext<T, C>, negativeCompare: CompareWithContext<T, C>): Unit

    /**
     * Defines a new matcher that compares an `actual` value to an `expected` value.
     */
    fun <T> matcher(name: String, compare: Compare<T>): Unit

    /**
     * Defines a new matcher that compares an `actual` value to an `expected` value.
     * If the matcher is chained with a `not` expectation, the `negativeCompare` function is used.
     */
    fun <T> matcher(name: String, compare: Compare<T>, negativeCompare: Compare<T>): Unit

    /**
     * Defines a new matcher that checks an `actual` value.
     */
    fun <T> matcher(name: String, compare: Check<T>): Unit

    /**
     * Defines a new matcher that checks an `actual` value.
     * If the matcher is chained with a `not` expectation, the `negativeCompare` function is used.
     */
    fun <T> matcher(name: String, compare: Check<T>, negativeCompare: Check<T>): Unit
}

/**
 * Implementation of [MatcherDefinitions] that collects all matcher definitions in a simple (dynamic) JS object
 * with the matcher names as keys (see [MatcherRegistrations]).
 */
internal class DynamicMatcherDefinitions : MatcherDefinitions {


    /**
     * Simple JS object containing all matcher definitions with the matcher names as keys.
     */
    private val matcherDefinitions: dynamic = js("({})")

    @Suppress("UnsafeCastFromDynamic")
    override val matcherRegistrations: MatcherRegistrations
        get() = matcherDefinitions

    override fun <T, C> matcher(name: String, compare: CompareWithContext<T, C>): Unit {
        defineMatcher(name, compare)
    }

    override fun <T, C> matcher(name: String, compare: CompareWithContext<T, C>, negativeCompare: CompareWithContext<T, C>) {
        defineMatcher(name, compare, negativeCompare)
    }

    override fun <T> matcher(name: String, compare: Compare<T>): Unit {
        defineMatcher(name, compare)
    }

    override fun <T> matcher(name: String, compare: Compare<T>, negativeCompare: Compare<T>) {
        defineMatcher(name, compare, negativeCompare)
    }

    override fun <T> matcher(name: String, compare: Check<T>): Unit {
        defineMatcher(name, compare)
    }

    override fun <T> matcher(name: String, compare: Check<T>, negativeCompare: Check<T>) {
        defineMatcher(name, compare, negativeCompare)
    }

    private fun <T, C> defineMatcher(name: String, compare: CompareWithContext<T, C>, negativeCompare: CompareWithContext<T, C>? = null) {

        matcherDefinitions[name] = { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->

            val matcher = js("({})")

            matcher["compare"] = { actual: T, expected: T, context: C ->
                PositiveComparison(name, util, customEqualityTesters).compare(actual, expected, context)
            }

            if (negativeCompare != null) {

                matcher["negativeCompare"] = { actual: T, expected: T, context: C ->
                    NegativeComparison(name, util, customEqualityTesters).negativeCompare(actual, expected, context)
                }
            }

            matcher
        }
    }

    private fun <T> defineMatcher(name: String, compare: Compare<T>, negativeCompare: Compare<T>? = null) {

        matcherDefinitions[name] = { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->

            val matcher = js("({})")

            matcher["compare"] = { actual: T, expected: T ->
                PositiveComparison(name, util, customEqualityTesters).compare(actual, expected)
            }

            if (negativeCompare != null) {

                matcher["negativeCompare"] = { actual: T, expected: T ->
                    NegativeComparison(name, util, customEqualityTesters).negativeCompare(actual, expected)
                }
            }

            matcher
        }
    }

    private fun <T> defineMatcher(name: String, compare: Check<T>, negativeCompare: Check<T>? = null): Unit {

        matcherDefinitions[name] = { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->

            val matcher = js("({})")

            matcher["compare"] = { actual: T ->
                PositiveComparison(name, util, customEqualityTesters).compare(actual)
            }

            if (negativeCompare != null) {

                matcher["negativeCompare"] = { actual: T ->
                    NegativeComparison(name, util, customEqualityTesters).negativeCompare(actual)
                }
            }

            matcher
        }
    }
}