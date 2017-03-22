package jasmine

external interface Expectations {

    val not: Expectations

    fun toBe(expected: Any?): Unit

    fun toEqual(expected: Any?): Unit

    fun toMatch(expected: String): Unit

    fun toBeNull(): Unit

    fun toBeDefined(): Unit

    fun toBeTruthy(): Unit

    fun toBeFalsy(): Unit
}

@JsName("expect")
external fun expect(actual: Any?): Expectations
