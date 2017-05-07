package jasmine

/**
 * Defines a test suite. Can be nested inside another `describe` block.
 */
@JsName("describe")
external fun describe(description: String, tests: () -> Unit)

/**
 * Disables a test suite (note the `x`). Can be nested inside another `describe` block.
 */
@JsName("xdescribe")
external fun xdescribe(description: String, tests: () -> Unit)
