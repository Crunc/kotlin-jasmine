package jasmine

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

            val something = Any()
            val other = Any()

            expect(something).toEqual(other)
        }

        it("should compare large number references") { ->

            expect(2438934893428903411).toEqual(2438934893428903411)
            expect(-5490548743893489348).toEqual(-5490548743893489348)
        }
    }
}
