package jasmine

private open class Foo(val bar: Int)

private class Bar(bar: Int, val foo: String) : Foo(bar)

private class Quux(val q: Double)

@Suppress("unused")
private val spec = describe("custom equalities") {

    describe("string tester") {

        it("should be called for strings") { ->

            // given
            val first = "Foobar"
            val second = "Foobaz"
            val tester = customEquality { first: String, second: String ->
                first.take(5) == second.take(5)
            }

            // when
            val result = tester(first, second)

            // then
            expect(result).toBe(true)
        }

        it("should not be called for numbers") { ->

            // given
            val first = 5
            val second = 5
            val tester = customEquality { first: String, second: String ->
                first.take(5) == second.take(5)
            }

            // when
            val result = tester(first, second)

            // then
            expect(result).toBeUndefined()
        }

        it("should not be called for booleans") { ->

            // given
            val first = true
            val second = false
            val tester = customEquality { first: String, second: String ->
                first.take(5) == second.take(5)
            }

            // when
            val result = tester(first, second)

            // then
            expect(result).toBeUndefined()
        }
    }

    describe("number tester") {

        it("should be called for integers") { ->

            // given
            val first = 42
            val second = 21
            val tester = customEquality { first: Number, second: Number ->
                first.toInt() > second.toInt()
            }

            // when
            val result = tester(first, second)

            // then
            expect(result).toBe(true)
        }

        it("should be called for doubles") { ->

            // given
            val first = 23.2
            val second = 23.25
            val tester = customEquality { first: Number, second: Number ->
                first.toInt() > second.toInt()
            }

            // when
            val result = tester(first, second)

            // then
            expect(result).toBe(false)
        }

        it("should not be called for strings") { ->

            // given
            val first = "13"
            val second = "23"
            val tester = customEquality { first: Number, second: Number ->
                first.toInt() > second.toInt()
            }

            // when
            val result = tester(first, second)

            // then
            expect(result).toBeUndefined()
        }

        it("should not be called for booleans") { ->

            // given
            val first = true
            val second = true
            val tester = customEquality { first: Number, second: Number ->
                first.toInt() > second.toInt()
            }

            // when
            val result = tester(first, second)

            // then
            expect(result).toBeUndefined()
        }
    }

    describe("type safe tester") {

        it("should be called for values of same type") { ->

            // given
            val foo1 = Foo(1)
            val foo2 = Foo(2)
            val tester = customEquality(Foo::class) { first, second ->
                first.bar == second.bar
            }

            // when
            val result = tester(foo1, foo2)

            // then
            expect(result).toBe(false)
        }

        it("should be called for values of subtype") { ->

            // given
            val foo = Foo(1)
            val bar = Bar(1, "Foobar")
            val tester = customEquality(Foo::class) { first, second ->
                first.bar == second.bar
            }

            // when
            val result = tester(foo, bar)

            // then
            expect(result).toBe(true)
        }

        it("should ne be called for values of super type") { ->

            // given
            val foo1 = Foo(1)
            val foo2 = Foo(2)
            val tester = customEquality(Bar::class) { first, second ->
                first.bar == second.bar && first.foo == second.foo
            }

            // when
            val result = tester(foo1, foo2)

            // then
            expect(result).toBeUndefined()
        }

        it("should not be called for values of different type") { ->

            // given
            val foo = Foo(1)
            val quux = Quux(1.0)
            val tester = customEquality(Foo::class) { first, second ->
                first.bar == second.bar
            }

            // when
            val result = tester(foo, quux)

            // then
            expect(result).toBeUndefined()
        }

        it("should not be called for values of dynamic type") { ->

            // given
            val foo = js("({bar: 42})")
            val bar = js("({bar: 42, foo: 'Foobar'})")
            val tester = customEquality(Foo::class) { first, second ->
                first.bar == second.bar
            }

            // when
            @Suppress("UnsafeCastFromDynamic")
            val result = tester(foo, bar)

            // then
            expect(result).toBeUndefined()
        }
    }

    describe("string tester registered with Jasmine") {

        beforeEach { ->
            Jasmine.addCustomEqualityTester { first:String, second:String ->
                first.take(5) == second.take(5)
            }
        }

        it("should be used") { ->

            // given
            val first = "Foobar"
            val second = "Foobaz"

            // then
            expect(first).toEqual(second)
        }
    }

    describe("number tester registered with Jasmine") {

        beforeEach { ->
            Jasmine.addCustomEqualityTester { first:Number, second:Number ->
                first.toDouble() < second.toDouble()
            }
        }

        it("should be used") { ->

            // given
            val first = 3.141
            val second = 3.14

            // then
            expect(first).not.toEqual(second)
        }
    }

    describe("type safe tester registered with Jasmine") {

        beforeEach { ->
            Jasmine.addCustomEqualityTester(Bar::class) { first, second ->
                first.bar == second.bar
            }
        }

        it("should be used") { ->

            // given
            val bar1 = Bar(42, "Foobar")
            val bar2 = Bar(42, "Quux")

            // then
            expect(bar1).toEqual(bar2)
        }
    }

    describe("unsafe tester registered with Jasmine") {

        beforeEach { ->
            Jasmine.addCustomEqualityTester { first: Any?, second: Any? ->
                when {
                    first !is String -> undefined
                    second !is String -> undefined
                    else -> first.take(3) == second.take(3)
                }
            }
        }

        it("should be used") { ->

            // given
            val first = "Foobar"
            val second = "Foobaz"

            // then
            expect(first).toEqual(second)
        }

        it("should detect mismatch") { ->

            // given
            val first = "Foobar"
            val second = "Quux"

            // then
            expect(first).not.toEqual(second)
        }
    }
}
