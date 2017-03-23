package jasmine

@JsName("it")
external fun it(description: String, test: () -> Unit): Unit

@JsName("it")
external fun it(description: String, asyncTest: (done: () -> Unit) -> Unit): Unit

@JsName("xit")
external fun xit(description: String, test: () -> Unit): Unit

@JsName("xit")
external fun xit(description: String, asyncTest: (done: () -> Unit) -> Unit): Unit