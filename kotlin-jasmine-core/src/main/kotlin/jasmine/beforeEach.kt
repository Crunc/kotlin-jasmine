package jasmine

/**
 * Runs before each test.
 */
@JsName("beforeEach")
external fun beforeEach(setup: () -> Unit)

/**
 * Runs asynchronously before each test.
 */
@JsName("beforeEach")
external fun beforeEach(asyncSetup: (done: () -> Unit) -> Unit)
