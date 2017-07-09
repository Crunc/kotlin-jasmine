# Kotlin Jasmine

Kotlin JS Jasmine Bindings

## [Guide](./docs/guide.md)

## Not yet implemented

There are some advanced features of Jasmine that have not yet been implemented, but will be in future releases:

* `beforeAll`: A callback that is executed once before all specs in a `describe`.
 * `afterAll`: A callback that is executed once after all specs in a `describe`.
* `fail`: A function that can be called from within a Spec that indicates the test has failed.
* `expect(fn).toThrowError()` An expectation that checks whether the given function throws a specific error.
* `Spies`: