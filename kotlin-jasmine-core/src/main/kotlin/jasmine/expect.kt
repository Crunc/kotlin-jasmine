package jasmine

import kotlin.js.RegExp

external interface Expectations<in T> {

    val not: Expectations<T>

    fun toBe(expected: Any?): Unit

    fun toEqual(expected: Any?): Unit

    fun toMatch(pattern: String): Unit

    fun toMatch(regex: RegExp): Unit

    fun toBeDefined(): Unit

    fun toBeUndefined(): Unit

    fun toBeNull(): Unit

    fun toBeTruthy(): Unit

    fun toBeFalsy(): Unit

    fun toContain(value: Any?): Unit

    fun toBeLessThan(value: Number): Unit

    fun toBeGreaterThan(value: Number): Unit

    fun toBeNaN(): Unit

    fun toBeCloseTo(value: Number, precision: Int): Unit

    fun toThrow(): Unit
}

@JsName("expect")
external fun <T> expect(actual: T?): Expectations<T>
