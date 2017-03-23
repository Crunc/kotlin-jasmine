package jasmine

import kotlin.js.RegExp

external interface Expectations<in V, out E : Expectations<V, E>> {

    val not: E

    fun toBe(expected: Any?): Unit

    fun toEqual(expected: Any?): Unit

    fun toBeDefined(): Unit

    fun toBeUndefined(): Unit

    fun toBeNull(): Unit

    fun toBeTruthy(): Unit

    fun toBeFalsy(): Unit

    fun toThrow(): Unit
}

external interface AnyExpectations : Expectations<Any, AnyExpectations>

external interface NumberExpectations<in V : Number, out E : NumberExpectations<V, E>> : Expectations<V, E> {

    fun toBeLessThan(value: Number): Unit

    fun toBeGreaterThan(value: Number): Unit

    fun toBeNaN(): Unit
}

external interface IntExpectations : NumberExpectations<Int, IntExpectations>

external interface FloatExpectations : NumberExpectations<Float, FloatExpectations> {

    fun toBeCloseTo(value: Number, precision: Int): Unit
}

external interface DoubleExpectations : NumberExpectations<Double, DoubleExpectations> {

    fun toBeCloseTo(value: Number, precision: Int): Unit
}

external interface StringExpectations : Expectations<String, StringExpectations> {

    fun toMatch(pattern: String): Unit

    fun toMatch(regex: RegExp): Unit

    fun toContain(value: String?): Unit

}

external interface ArrayExpectations<in T> : Expectations<Array<out T>, ArrayExpectations<T>> {

    fun toContain(value: T?): Unit
}

@JsName("expect")
external fun expect(actual: Any?): AnyExpectations

@JsName("expect")
external fun expect(actual: Nothing?): AnyExpectations

@JsName("expect")
external fun expect(actual: Unit?): AnyExpectations

@JsName("expect")
external fun expect(actual: Int?): IntExpectations

@JsName("expect")
external fun expect(actual: Float?): FloatExpectations

@JsName("expect")
external fun expect(actual: Double?): DoubleExpectations

@JsName("expect")
external fun expect(actual: String?): StringExpectations

@JsName("expect")
external fun <T> expect(actual: Array<out T>?): ArrayExpectations<T>

//@JsName("expect")
//external fun expect(actual: dynamic): AnyExpectations