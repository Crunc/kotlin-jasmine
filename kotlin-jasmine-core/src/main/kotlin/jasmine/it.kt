package jasmine

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
 * Defines a test. Must be called from within a `describe` block.
 */
@JsName("it")
external fun it(description: String, test: () -> Unit)

/**
 * Defines an asynchronous test. Must be called from within a `describe` block.
 */
@JsName("it")
external fun itAsync(description: String, asyncTest: (done: () -> Unit) -> Unit)

/**
 * Disables a test (note the `x`). Must be called from within a `describe` block.
 */
@JsName("xit")
external fun xit(description: String, test: () -> Unit)

/**
 * Disables an asynchronous test (note the `x`). Must be called from within a `describe` block.
 */
@JsName("xit")
external fun xitAsync(description: String, asyncTest: (done: () -> Unit) -> Unit)