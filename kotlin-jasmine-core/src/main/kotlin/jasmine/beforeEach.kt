package jasmine

@JsName("beforeEach")
external fun beforeEach(setup: () -> Unit)

@JsName("beforeEach")
external fun beforeEach(asyncSetup: (done: () -> Unit) -> Unit)
