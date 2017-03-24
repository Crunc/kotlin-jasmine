package jasmine

external interface Jasmine {

    fun addMatchers(matcherRegistrations: MatcherRegistrations)
}

@JsName("jasmine")
external val jasmine: Jasmine
