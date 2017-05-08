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
    fun <T, C> matcher(name: String, compare: Comparison.(actual: T, expected: T, context: C) -> Result): Unit

    /**
     * Defines a new matcher that compares an `actual` value to an `expected` value.
     */
    fun <T> matcher(name: String, compare: Comparison.(actual: T, expected: T) -> Result): Unit

    /**
     * Defines a new matcher that checks an `actual` value.
     */
    fun <T> matcher(name: String, compare: Comparison.(actual: T) -> Result): Unit
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

    override fun <T, C> matcher(name: String, compare: Comparison.(actual: T, expected: T, context: C) -> Result): Unit {

        matcherDefinitions[name] = { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->

            val matcher = js("({})")

            matcher.compare = { actual: T, expected: T, context: C ->
                PositiveComparison(name, util, customEqualityTesters).compare(actual, expected, context)
            }

            matcher
        }
    }

    override fun <T> matcher(name: String, compare: Comparison.(actual: T, expected: T) -> Result): Unit {

        matcherDefinitions[name] = { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->

            val matcher = js("({})")

            matcher.compare = { actual: T, expected: T ->
                PositiveComparison(name, util, customEqualityTesters).compare(actual, expected)
            }

            matcher
        }
    }

    override fun <T> matcher(name: String, compare: Comparison.(actual: T) -> Result): Unit {

        matcherDefinitions[name] = { util: MatcherUtils, customEqualityTesters: CustomEqualityTesters ->

            val matcher = js("({})")

            matcher.compare = { actual: T ->
                PositiveComparison(name, util, customEqualityTesters).compare(actual)
            }

            matcher
        }
    }
}