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
 * Receiver for matcher compare functions. Provides utilities that can be used from within a matcher's compare
 * function.
 *
 * @author Hauke Jaeger, hauke.jaeger@yegair.io
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

/**
 * Basic implementation of [Comparison].
 */
internal sealed class BaseComparison(private val matcherName: String,
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

/**
 * Implementation of [Comparison] that is used as receiver for matcher `compare` functions.
 */
internal class PositiveComparison(
        matcherName: String,
        util: MatcherUtils,
        customTesters: CustomEqualityTesters) : BaseComparison(matcherName, util, customTesters, false)

/**
 * Implementation of [Comparison] that is used as receiver for matcher `negativeCompare` functions.
 */
internal class NegativeComparison(
        matcherName: String,
        util: MatcherUtils,
        customTesters: CustomEqualityTesters) : BaseComparison(matcherName, util, customTesters, true)