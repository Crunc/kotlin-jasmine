package jasmine

import kotlin.reflect.KClass

/*
 * MIT License
 *
 * Copyright (c) 2017 Hauke Jaeger http://yegair.io
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * Creates a custom equality that is applied to strings only.
 */
fun customEquality(equals: (first: String, second: String) -> Boolean?): CustomEqualityTester {
    return { first: Any?, second: Any? ->
        when {
            first !is String -> undefined
            second !is String -> undefined
            else -> equals(first, second)
        }
    }
}

/**
 * Registers a custom equality tester with Jasmine that is applied to strings only.
 */
fun GlobalJasmine.addCustomEqualityTester(equals: (first: String, second: String) -> Boolean?) {
    addCustomEqualityTester(customEquality(equals))
}

/**
 * Creates a custom equality that is applied to strings only.
 */
fun customEquality(equals: (first: Number, second: Number) -> Boolean?): CustomEqualityTester {
    return { first: Any?, second: Any? ->
        when {
            first !is Number -> undefined
            second !is Number -> undefined
            else -> equals(first, second)
        }
    }
}

/**
 * Registers a custom equality tester with Jasmine that is applied to strings only.
 */
fun GlobalJasmine.addCustomEqualityTester(equals: (first: Number, second: Number) -> Boolean?) {
    addCustomEqualityTester(customEquality(equals))
}

/**
 * Creates a custom equality that is applied to objects of the given type only
 */
fun <T : Any> customEquality(type: KClass<T>, equals: (first: T, second: T) -> Boolean?): CustomEqualityTester {
    return { first, second ->

        @Suppress("UNCHECKED_CAST")
        when {
            first == null -> undefined
            second == null -> undefined
            !type.isInstance(first) -> undefined
            !type.isInstance(second) -> undefined
            else -> equals(first as T, second as T)
        }
    }
}

/**
 * Registers a type safe custom equality tester with Jasmine.
 */
fun <T : Any> GlobalJasmine.addCustomEqualityTester(type: KClass<T>, equals: (first: T, second: T) -> Boolean?) {
    addCustomEqualityTester(customEquality(type, equals))
}