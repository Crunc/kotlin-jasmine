package jasmine

@JsName("describe")
external fun describe(description: String, tests: () -> Unit)

@JsName("xdescribe")
external fun xdescribe(description: String, tests: () -> Unit)
