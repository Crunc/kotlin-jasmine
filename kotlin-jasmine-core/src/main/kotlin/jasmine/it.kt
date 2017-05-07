package jasmine

/**
 * Defines a test. Must be called from within a `describe` block.
 */
@JsName("it")
external fun it(description: String, test: () -> Unit): Unit

/**
 * Defines an asynchronous test. Must be called from within a `describe` block.
 */
@JsName("it")
external fun it(description: String, asyncTest: (done: () -> Unit) -> Unit): Unit

/**
 * Disables a test (note the `x`). Must be called from within a `describe` block.
 */
@JsName("xit")
external fun xit(description: String, test: () -> Unit): Unit

/**
 * Disables an asynchronous test (note the `x`). Must be called from within a `describe` block.
 */
@JsName("xit")
external fun xit(description: String, asyncTest: (done: () -> Unit) -> Unit): Unit