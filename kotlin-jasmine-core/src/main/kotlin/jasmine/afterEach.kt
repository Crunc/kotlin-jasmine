package jasmine

/**
 * Runs after each test.
 */
@JsName("afterEach")
external fun afterEach(tearDown: () -> Unit): Unit

/**
 * Runs asynchronously after each test.
 */
@JsName("afterEach")
external fun afterEach(asyncTearDown: (done: () -> Unit) -> Unit): Unit
