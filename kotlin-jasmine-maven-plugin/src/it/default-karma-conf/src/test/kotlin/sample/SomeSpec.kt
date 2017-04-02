package sample

import jasmine.*

private val spec = describe("something") {

    it("should run") { ->

        val something = Something()
        val message = something.sayHello()

        expect(message).toContain("Hello")
    }
}