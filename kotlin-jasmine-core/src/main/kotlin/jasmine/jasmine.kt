package jasmine

external interface Jasmine {

    fun addMatchers(matchers: Matchers)
}

@JsName("jasmine")
external val jasmine: Jasmine
