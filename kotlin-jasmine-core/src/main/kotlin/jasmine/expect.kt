package jasmine

import kotlin.js.RegExp

/**
 * Creates a new expectation.
 */
@JsName("expect")
external fun <E> expect(actual: E?): Expectations<E>

/**
 * Fluent API for describing expectations
 */
external class Expectations<in E> {

    /**
     * Expects the following expectation to fail.
     */
    val not: Expectations<E>

    // --- Any

    @JsName("toBe")
    internal fun _toBe(expected: Any?): Unit

    @JsName("toEqual")
    internal fun _toEqual(expected: Any?): Unit

    @JsName("toBeDefined")
    internal fun _toBeDefined(): Unit

    @JsName("toBeUndefined")
    internal fun _toBeUndefined(): Unit

    @JsName("toBeNull")
    internal fun _toBeNull(): Unit

    @JsName("toBeTruthy")
    internal fun _toBeTruthy(): Unit

    @JsName("toBeFalsy")
    internal fun _toBeFalsy(): Unit

    // --- Functions

    @JsName("toThrow")
    internal fun _toThrow(): Unit

    // --- Numbers

    @JsName("toBeLessThan")
    internal fun _toBeLessThan(value: Number): Unit

    @JsName("toBeGreaterThan")
    internal fun _toBeGreaterThan(value: Number): Unit

    @JsName("toBeNaN")
    internal fun _toBeNaN(): Unit

    // --- Floats

    @JsName("toBeCloseTo")
    internal fun _toBeCloseTo(value: Number, precision: Int): Unit

    // --- Strings

    @JsName("toMatch")
    internal fun _toMatch(pattern: String): Unit

    @JsName("toMatch")
    internal fun _toMatch(regex: RegExp): Unit

    // --- Arrays

    @JsName("toContain")
    internal fun _toContain(value: Any?): Unit
}

/**
 * Expects the given value to be the same as the actual value.
 */
fun <E : Any> Expectations<E>.toBe(expected: Any?): Unit
        = this._toBe(expected)

/**
 * Expects the given value to be equal to the actual value.
 */
fun <E : Any> Expectations<E>.toEqual(expected: Any?): Unit
        = this._toEqual(expected)

/**
 * Expects the actual value to be defined.
 */
fun <E : Any> Expectations<E>.toBeDefined(): Unit
        = this._toBeDefined()

/**
 * Expects the actual value not to be defined.
 */
fun <E : Any> Expectations<E>.toBeUndefined(): Unit
        = this._toBeUndefined()

/**
 * Expects the actual value not to be `null`.
 */
fun <E : Any> Expectations<E>.toBeNull(): Unit
        = this._toBeNull()

/**
 * Expects the actual value to be [truthy](https://developer.mozilla.org/en/docs/Glossary/Truthy).
 */
fun <E : Any> Expectations<E>.toBeTruthy(): Unit
        = this._toBeTruthy()

/**
 * Expects the actual value to be [falsy](https://developer.mozilla.org/en/docs/Glossary/Falsy).
 */
fun <E : Any> Expectations<E>.toBeFalsy(): Unit
        = this._toBeFalsy()

/**
 * Expects an error to be thrown when the actual function is invoked.
 */
fun <E : Function<Any>> Expectations<E>.toThrow(): Unit
        = this._toThrow()

/**
 * Expects the actual value to be less than the given value (`actual < expected`).
 */
fun <E : Number> Expectations<E>.toBeLessThan(expected: Number): Unit
        = this._toBeLessThan(expected)

/**
 * Expects the actual value to be greater than the given value (`actual > expected`).
 */
fun <E : Number> Expectations<E>.toBeGreaterThan(expected: Number): Unit
        = this._toBeGreaterThan(expected)

/**
 * Expects the actual value to be [NaN](https://developer.mozilla.org/en/docs/Glossary/NaN).
 */
fun <E : Number> Expectations<E>.toBeNaN(): Unit
        = this._toBeNaN()

/**
 * Expects the actual value to be close to the given value, allowing it to differ within the given precision.
 */
fun <E : Number> Expectations<E>.toBeCloseTo(expected: Number, precision: Int): Unit
        = this._toBeCloseTo(expected, precision)

/**
 * Expects the actual value to match the given pattern.
 */
fun Expectations<String>.toMatch(pattern: String): Unit
        = this._toMatch(pattern)

/**
 * Expects the actual value to match the given regex.
 */
fun Expectations<String>.toMatch(regex: RegExp): Unit
        = this._toMatch(regex)

/**
 * Expects the actual value to contain the given value as a substring.
 */
fun Expectations<String>.toContain(value: String): Unit
        = this._toContain(value)

/**
 * Expects the actual value to contain the given value as an element.
 */
fun <E : Array<out Any>> Expectations<E>.toContain(value: Any?): Unit
        = this._toContain(value)

/**
 * Expects the actual value to fulfill the expectations of the matcher with the given name.
 */
@Suppress("UnsafeCastFromDynamic")
fun <E> Expectations<E>.match(matcherName: String): Unit
        = (this.asDynamic()[matcherName])()

/**
 * Expects the actual value to fulfill the expectations of the matcher with the given name,
 * compared to the given expected value.
 */
@Suppress("UnsafeCastFromDynamic")
fun <E> Expectations<E>.match(matcherName: String, expected: Any?): Unit
        = (this.asDynamic()[matcherName])(expected)

/**
 * Expects the actual value to fulfill the expectations of the matcher with the given name,
 * compared to the given expected value, considering the given context.
 */
@Suppress("UnsafeCastFromDynamic")
fun <E> Expectations<E>.match(matcherName: String, expected: Any?, context: Any?): Unit
        = (this.asDynamic()[matcherName])(expected, context)