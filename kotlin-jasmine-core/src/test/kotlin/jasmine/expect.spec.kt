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
            expect(undefined).not.toBeDefined()
        }
    }

    describe("toBeUndefined") {

        it("should recognize void 0") { ->
            expect(js("(void 0)")).toBeUndefined()
        }

        it("should recognize undefined") { ->
            expect(undefined).toBeUndefined()
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

    describe("toBeTruthy") {

        it("should recognize booleans") { ->
            expect(true).toBeTruthy()
        }

        it("should recognize integers") { ->
            expect(1).toBeTruthy()
            expect(Int.MAX_VALUE).toBeTruthy()
            expect(Int.MIN_VALUE).toBeTruthy()
        }

        it("should recognize floats") { ->
            expect(0.1).toBeTruthy()
        }

        it("should recognize strings") { ->
            expect("0").toBeTruthy()
        }

        it("should recognize objects") { ->
            expect(Any()).toBeTruthy()
        }
    }

    describe("not.toBeTruthy") {

        it("should recognize null") { ->
            expect(null).not.toBeTruthy()
        }

        it("should recognize undefined") { ->
            expect(undefined).not.toBeTruthy()
        }

        it("should recognize booleans") { ->
            expect(false).not.toBeTruthy()
        }

        it("should recognize integers") { ->
            expect(0).not.toBeTruthy()
        }

        it("should recognize floats") { ->
            expect(0.0).not.toBeTruthy()
        }

        it("should recognize strings") { ->
            expect("").not.toBeTruthy()
        }

        it("should recognize NaN") { ->
            expect(Float.NaN).not.toBeTruthy()
        }
    }

    describe("toBeFalsy") {

        it("should recognize null") { ->
            expect(null).toBeFalsy()
        }

        it("should recognize undefined") { ->
            expect(undefined).toBeFalsy()
        }

        it("should recognize booleans") { ->
            expect(false).toBeFalsy()
        }

        it("should recognize integers") { ->
            expect(0).toBeFalsy()
        }

        it("should recognize floats") { ->
            expect(0.0).toBeFalsy()
        }

        it("should recognize strings") { ->
            expect("").toBeFalsy()
        }

        it("should recognize NaN") { ->
            expect(Float.NaN).toBeFalsy()
        }
    }

    describe("not.toBeFalsy") {

        it("should recognize booleans") { ->
            expect(true).not.toBeFalsy()
        }

        it("should recognize integers") { ->
            expect(1).toBeTruthy()
            expect(Int.MAX_VALUE).not.toBeFalsy()
            expect(Int.MIN_VALUE).not.toBeFalsy()
        }

        it("should recognize floats") { ->
            expect(0.1).not.toBeFalsy()
        }

        it("should recognize strings") { ->
            expect("0").not.toBeFalsy()
        }

        it("should recognize objects") { ->
            expect(Any()).not.toBeFalsy()
        }
    }

    describe("toContain") {

        it("should recognize strings") { ->
            expect("Foobar").toContain("bar")
        }

        it("should recognize arrays") { ->
            expect(arrayOf(1, 2, 3)).toContain(3)
        }
    }

    describe("not.toContain") {

        it("should recognize strings") { ->
            expect("Foobar").not.toContain("baz")
        }

        it("should recognize arrays") { ->
            expect(arrayOf(1, 2, 3)).not.toContain(4)
        }
    }

    describe("toBeLessThan") {

        it("should recognize integers") { ->
            expect(42).toBeLessThan(43)
            expect(-100).toBeLessThan(-90)
        }

        it("should recognize floats") { ->
            expect(42.0).toBeLessThan(42.01)
            expect(-0.1).toBeLessThan(0.0)
        }
    }

    describe("not.toBeLessThan") {

        it("should recognize integers") { ->
            expect(43).not.toBeLessThan(42)
            expect(-100).not.toBeLessThan(-101)
        }

        it("should recognize floats") { ->
            expect(42.01).not.toBeLessThan(42.0)
            expect(0).not.toBeLessThan(-0.1)
        }
    }

    describe("toBeGreaterThan") {

        it("should recognize integers") { ->
            expect(43).toBeGreaterThan(42)
            expect(-100).toBeGreaterThan(-101)
        }

        it("should recognize floats") { ->
            expect(42.01).toBeGreaterThan(42.0)
            expect(0).toBeGreaterThan(-0.1)
        }
    }

    describe("not.toBeGreaterThan") {

        it("should recognize integers") { ->
            expect(42).not.toBeGreaterThan(43)
            expect(-100).not.toBeGreaterThan(-90)
        }

        it("should recognize floats") { ->
            expect(42.0).not.toBeGreaterThan(42.01)
            expect(-0.1).not.toBeGreaterThan(0.0)
        }
    }

    describe("toBeNaN") {

        it("should recognize Float.NaN") { ->
            expect(Float.NaN).toBeNaN()
        }

        it("should recognize Double.NaN") { ->
            expect(Double.NaN).toBeNaN()
        }

        it("should recognize calculated NaN") { ->
            expect(js("(0/0)")).toBeNaN()
        }
    }

    describe("not.toBeNaN") {

        it("should recognize integers") { ->
            expect(0).not.toBeNaN()
            expect(42).not.toBeNaN()
        }

        it("should recognize floats") { ->
            expect(0.0).not.toBeNaN()
            expect(-0.1).not.toBeNaN()
        }

        it("should recognize strings") { ->
            expect("").not.toBeNaN()
        }

        it("should recognize booleans") { ->
            expect(true).not.toBeNaN()
            expect(false).not.toBeNaN()
        }

        it("should recognize objects") { ->
            expect(Any()).not.toBeNaN()
        }
    }

    describe("toBeCloseTo") {

        it("should recognize precision 0") { ->
            expect(42.0).toBeCloseTo(42.4, 0)
        }

        it("should recognize precision 1") { ->
            expect(42.0).toBeCloseTo(42.05, 1)
        }
    }

    describe("not.toBeCloseTo") {

        it("should recognize precision 0") { ->
            expect(42.0).not.toBeCloseTo(43.0, 0)
        }

        it("should recognize precision 1") { ->
            expect(42.0).not.toBeCloseTo(42.5, 1)
        }
    }

    describe("toThrow") {

        it("should recognize exceptions") { ->
            expect({ throw Exception("42") }).toThrow()
        }

        it("should recognize errors") { ->
            expect({ throw Error("foobar") }).toThrow()
        }
    }

    describe("not.toThrow") {

        it("should recognize exceptions") { ->
            expect({ 42 }).not.toThrow()
        }
    }
}
