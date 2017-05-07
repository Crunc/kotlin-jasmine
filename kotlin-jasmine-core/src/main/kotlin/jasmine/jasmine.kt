package jasmine

/**
 * Type of the global Jasmine object.
 */
external interface Jasmine {

    /**
     * Registers (custom) matchers with Jasmine.
     */
    fun addMatchers(matcherRegistrations: MatcherRegistrations)
}

/**
 * Global Jasmine object.
 */
@JsName("jasmine")
external val jasmine: Jasmine
