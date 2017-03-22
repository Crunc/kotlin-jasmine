package jasmine

@JsName("it")
external fun it(description: String, spec: () -> Unit): Unit

@JsName("it")
external fun it(description: String, asyncSpec: (done: () -> Unit) -> Unit): Unit