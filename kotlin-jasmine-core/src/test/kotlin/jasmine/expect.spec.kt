package jasmine

import kotlin.js.RegExp

@Suppress("unused")
private val spec = describe("expect") {

    describe("toBe") {

        it("should compare booleans") { ->
            expect(true).toBe(true)
            expect(false).toBe(false)
        }

        it("should compare small numbers") { ->
            expect(255).toBe(255)
            expect(-254).toBe(-254)
        }

        it("should compare object references") { ->
            val something = Any()
            expect(something).toBe(something)
        }
    }

    describe("not.toBe") {

        it("should compare booleans") { ->
            expect(true).not.toBe(false)
            expect(false).not.toBe(true)
        }

        it("should compare small numbers") { ->
            expect(255).not.toBe(256)
            expect(-254).not.toBe(-253)
        }

        it("should compare object references") { ->
            expect(Any()).not.toBe(Any())
        }
    }

    describe("toEqual") {

        it("should compare booleans") { ->
            expect(true).toEqual(true)
            expect(false).toEqual(false)
        }

        it("should compare small numbers") { ->
            expect(255).toEqual(255)
            expect(-254).toEqual(-254)
        }

        it("should compare object references") { ->
            expect(Any()).toEqual(Any())
        }

        it("should compare large number references") { ->
            expect(2438934893428903411).toEqual(2438934893428903411)
            expect(-5490548743893489348).toEqual(-5490548743893489348)
        }
    }

    describe("not.toEqual") {

        it("should compare booleans") { ->
            expect(true).not.toEqual(false)
            expect(false).not.toEqual(true)
        }

        it("should compare small numbers") { ->
            expect(255).not.toEqual(-255)
            expect(-254).not.toEqual(254)
        }

        it("should compare object references") { ->
            val something = js("({ x: 42 })")
            val other = js("({ x: 21 })")
            expect(something).not.toEqual(other)
        }

        it("should compare large number references") { ->
            expect(2438934893428903411).not.toEqual(-2438934893428903411)
            expect(-5490548743893489348).not.toEqual(5490548743893489348)
        }
    }

    describe("toMatch") {

        it("should support strings") { ->
            expect("Foobar").toMatch(".*bar$")
        }

        it("should support RegExp") { ->
            expect("Foobar").toMatch(RegExp("^Foo.*"))
        }
    }

    describe("not.toMatch") {

        it("should support strings") { ->
            expect("Foobar").not.toMatch(".*baz$")
        }

        it("should support RegExp") { ->
            expect("Foobar").not.toMatch(RegExp("^Baz.*"))
        }
    }

    describe("toBeDefined") {

        it("should recognize null") { ->
            expect(null).toBeDefined()
        }

        it("should recognize any") { ->
            expect(Any()).toBeDefined()
        }
    }

    describe("not.toBeDefined") {

        it("should recognize void 0") { ->
            expect(js("(void 0)")).not.toBeDefined()
        }

        it("should recognize undefined") { ->
            expect(js("(undefined)")).not.toBeDefined()
        }
    }

    describe("toBeUndefined") {

        it("should recognize void 0") { ->
            expect(js("(void 0)")).toBeUndefined()
        }

        it("should recognize undefined") { ->
            expect(js("(undefined)")).toBeUndefined()
        }
    }

    describe("not.toBeUndefined") {

        it("should recognize null") { ->
            expect(null).not.toBeUndefined()
        }

        it("should recognize any") { ->
            expect(Any()).not.toBeUndefined()
        }
    }

    describe("toBeNull") {

        it("should recognize null") { ->
            expect(null).toBeNull()
        }

        it("should recognize references") { ->
            val foo: Any? = null
            expect(foo).toBeNull()
        }
    }

    describe("not.toBeNull") {

        it("should recognize boolean") { ->
            expect(true).not.toBeNull()
            expect(false).not.toBeNull()
        }

        it("should recognize numbers") { ->
            expect(0).not.toBeNull()
            expect(1).not.toBeNull()
            expect(-1).not.toBeNull()
        }

        it("should recognize strings") { ->
            expect("").not.toBeNull()
            expect("Foobar").not.toBeNull()
        }

        it("should recognize objects") { ->
            expect(Any()).not.toBeNull()
            expect(js("({})")).not.toBeNull()
        }
    }
}
