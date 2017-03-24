package jasmine

@JsName("afterEach")
external fun afterEach(tearDown: () -> Unit): Unit

@JsName("afterEach")
external fun afterEach(asyncTearDown: (done: () -> Unit) -> Unit): Unit
