package jasmine

import kotlin.js.RegExp

@JsName("expect")
external fun <E> expect(actual: E?): Expectations<E>

external class Expectations<in E> {

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

fun <E : Any> Expectations<E>.toBe(expected: Any?): Unit
        = this._toBe(expected)

fun <E : Any> Expectations<E>.toEqual(expected: Any?): Unit
        = this._toEqual(expected)

fun <E : Any> Expectations<E>.toBeDefined(): Unit
        = this._toBeDefined()

fun <E : Any> Expectations<E>.toBeUndefined(): Unit
        = this._toBeUndefined()

fun <E : Any> Expectations<E>.toBeNull(): Unit
        = this._toBeNull()

fun <E : Any> Expectations<E>.toBeTruthy(): Unit
        = this._toBeTruthy()

fun <E : Any> Expectations<E>.toBeFalsy(): Unit
        = this._toBeFalsy()

fun <E : Function<Any>> Expectations<E>.toThrow(): Unit
        = this._toThrow()

fun <E : Number> Expectations<E>.toBeLessThan(expected: Number): Unit
        = this._toBeLessThan(expected)

fun <E : Number> Expectations<E>.toBeGreaterThan(expected: Number): Unit
        = this._toBeGreaterThan(expected)

fun <E : Number> Expectations<E>.toBeNaN(): Unit
        = this._toBeNaN()

fun <E : Number> Expectations<E>.toBeCloseTo(expected: Number, precision: Int): Unit
        = this._toBeCloseTo(expected, precision)

fun Expectations<String>.toMatch(pattern: String): Unit
        = this._toMatch(pattern)

fun Expectations<String>.toMatch(regex: RegExp): Unit
        = this._toMatch(regex)

fun Expectations<String>.toContain(value: String): Unit
        = this._toContain(value)

fun <E : Array<out Any>> Expectations<E>.toContain(value: Any?): Unit
        = this._toContain(value)

@Suppress("UnsafeCastFromDynamic")
fun <E> Expectations<E>.match(matcherName: String): Unit
        = (this.asDynamic()[matcherName])()

@Suppress("UnsafeCastFromDynamic")
fun <E> Expectations<E>.match(matcherName: String, expected: Any?): Unit
        = (this.asDynamic()[matcherName])(expected)

@Suppress("UnsafeCastFromDynamic")
fun <E> Expectations<E>.match(matcherName: String, expected: Any?, context: Any?): Unit
        = (this.asDynamic()[matcherName])(expected, context)