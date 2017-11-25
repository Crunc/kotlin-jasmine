(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'kotlin-jasmine-core'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('kotlin-jasmine-core'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'kotlin-jasmine-core-tests'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'kotlin-jasmine-core-tests'.");
    }
    if (typeof this['kotlin-jasmine-core'] === 'undefined') {
      throw new Error("Error loading module 'kotlin-jasmine-core-tests'. Its dependency 'kotlin-jasmine-core' was not found. Please, check whether 'kotlin-jasmine-core' is loaded prior to 'kotlin-jasmine-core-tests'.");
    }
    root['kotlin-jasmine-core-tests'] = factory(typeof this['kotlin-jasmine-core-tests'] === 'undefined' ? {} : this['kotlin-jasmine-core-tests'], kotlin, this['kotlin-jasmine-core']);
  }
}(this, function (_, Kotlin, $module$kotlin_jasmine_core) {
  'use strict';
  var toBe = $module$kotlin_jasmine_core.jasmine.toBe_yixw8n$;
  var Unit = Kotlin.kotlin.Unit;
  var Any = Object;
  var toEqual = $module$kotlin_jasmine_core.jasmine.toEqual_yixw8n$;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var toMatch = $module$kotlin_jasmine_core.jasmine.toMatch_xniniu$;
  var toMatch_0 = $module$kotlin_jasmine_core.jasmine.toMatch_e02pc9$;
  var toBeDefined = $module$kotlin_jasmine_core.jasmine.toBeDefined_uwg63b$;
  var toBeUndefined = $module$kotlin_jasmine_core.jasmine.toBeUndefined_uwg63b$;
  var toBeNull = $module$kotlin_jasmine_core.jasmine.toBeNull_uwg63b$;
  var toBeTruthy = $module$kotlin_jasmine_core.jasmine.toBeTruthy_uwg63b$;
  var IntCompanionObject = Kotlin.kotlin.js.internal.IntCompanionObject;
  var FloatCompanionObject = Kotlin.kotlin.js.internal.FloatCompanionObject;
  var toBeFalsy = $module$kotlin_jasmine_core.jasmine.toBeFalsy_uwg63b$;
  var toContain = $module$kotlin_jasmine_core.jasmine.toContain_xniniu$;
  var toContain_0 = $module$kotlin_jasmine_core.jasmine.toContain_r7b3v0$;
  var toBeLessThan = $module$kotlin_jasmine_core.jasmine.toBeLessThan_1fe7tk$;
  var toBeGreaterThan = $module$kotlin_jasmine_core.jasmine.toBeGreaterThan_1fe7tk$;
  var toBeNaN = $module$kotlin_jasmine_core.jasmine.toBeNaN_sa041q$;
  var DoubleCompanionObject = Kotlin.kotlin.js.internal.DoubleCompanionObject;
  var toBeCloseTo = $module$kotlin_jasmine_core.jasmine.toBeCloseTo_8m97dy$;
  var Exception = Kotlin.kotlin.Exception;
  var toThrow = $module$kotlin_jasmine_core.jasmine.toThrow_2f5p37$;
  var Error_0 = Kotlin.kotlin.Error;
  var equals = Kotlin.kotlin.text.equals_igcy3c$;
  var matchers = $module$kotlin_jasmine_core.jasmine.matchers_qxnh2$;
  var match = $module$kotlin_jasmine_core.jasmine.match_n21y3s$;
  var match_0 = $module$kotlin_jasmine_core.jasmine.match_vap8h8$;
  var match_1 = $module$kotlin_jasmine_core.jasmine.match_pcoc00$;
  function spec$lambda$lambda$lambda() {
    toBe(expect(true), true);
    toBe(expect(false), false);
    return Unit;
  }
  function spec$lambda$lambda$lambda_0() {
    toBe(expect(255), 255);
    toBe(expect(-254), -254);
    return Unit;
  }
  function spec$lambda$lambda$lambda_1() {
    var something = new Any();
    toBe(expect(something), something);
    return Unit;
  }
  function spec$lambda$lambda() {
    it('should compare booleans', spec$lambda$lambda$lambda);
    it('should compare small numbers', spec$lambda$lambda$lambda_0);
    it('should compare object references', spec$lambda$lambda$lambda_1);
    return Unit;
  }
  function spec$lambda$lambda$lambda_2() {
    toBe(expect(true).not, false);
    toBe(expect(false).not, true);
    return Unit;
  }
  function spec$lambda$lambda$lambda_3() {
    toBe(expect(255).not, 256);
    toBe(expect(-254).not, -253);
    return Unit;
  }
  function spec$lambda$lambda$lambda_4() {
    toBe(expect(new Any()).not, new Any());
    return Unit;
  }
  function spec$lambda$lambda_0() {
    it('should compare booleans', spec$lambda$lambda$lambda_2);
    it('should compare small numbers', spec$lambda$lambda$lambda_3);
    it('should compare object references', spec$lambda$lambda$lambda_4);
    return Unit;
  }
  function spec$lambda$lambda$lambda_5() {
    toEqual(expect(true), true);
    toEqual(expect(false), false);
    return Unit;
  }
  function spec$lambda$lambda$lambda_6() {
    toEqual(expect(255), 255);
    toEqual(expect(-254), -254);
    return Unit;
  }
  function spec$lambda$lambda$lambda_7() {
    toEqual(expect(new Any()), new Any());
    return Unit;
  }
  function spec$lambda$lambda$lambda_8() {
    toEqual(expect(new Kotlin.Long(287477235, 567858781)), new Kotlin.Long(287477235, 567858781));
    toEqual(expect(new Kotlin.Long(-230495940, -1278367999)), new Kotlin.Long(-230495940, -1278367999));
    return Unit;
  }
  function spec$lambda$lambda_1() {
    it('should compare booleans', spec$lambda$lambda$lambda_5);
    it('should compare small numbers', spec$lambda$lambda$lambda_6);
    it('should compare object references', spec$lambda$lambda$lambda_7);
    it('should compare large number references', spec$lambda$lambda$lambda_8);
    return Unit;
  }
  function spec$lambda$lambda$lambda_9() {
    toEqual(expect(true).not, false);
    toEqual(expect(false).not, true);
    return Unit;
  }
  function spec$lambda$lambda$lambda_10() {
    toEqual(expect(255).not, -255);
    toEqual(expect(-254).not, 254);
    return Unit;
  }
  function spec$lambda$lambda$lambda_11() {
    var something = to('foo', 42);
    var other = to('foo', 43);
    toEqual(expect(something).not, other);
    return Unit;
  }
  function spec$lambda$lambda$lambda_12() {
    toEqual(expect(new Kotlin.Long(287477235, 567858781)).not, new Kotlin.Long(-287477235, -567858782));
    toEqual(expect(new Kotlin.Long(-230495940, -1278367999)).not, new Kotlin.Long(230495940, 1278367998));
    return Unit;
  }
  function spec$lambda$lambda_2() {
    it('should compare booleans', spec$lambda$lambda$lambda_9);
    it('should compare small numbers', spec$lambda$lambda$lambda_10);
    it('should compare object references', spec$lambda$lambda$lambda_11);
    it('should compare large number references', spec$lambda$lambda$lambda_12);
    return Unit;
  }
  function spec$lambda$lambda$lambda_13() {
    toMatch(expect('Foobar'), '.*bar$');
    return Unit;
  }
  function spec$lambda$lambda$lambda_14() {
    toMatch_0(expect('Foobar'), new RegExp('^Foo.*'));
    return Unit;
  }
  function spec$lambda$lambda_3() {
    it('should support strings', spec$lambda$lambda$lambda_13);
    it('should support RegExp', spec$lambda$lambda$lambda_14);
    return Unit;
  }
  function spec$lambda$lambda$lambda_15() {
    toMatch(expect('Foobar').not, '.*baz$');
    return Unit;
  }
  function spec$lambda$lambda$lambda_16() {
    toMatch_0(expect('Foobar').not, new RegExp('^Baz.*'));
    return Unit;
  }
  function spec$lambda$lambda_4() {
    it('should support strings', spec$lambda$lambda$lambda_15);
    it('should support RegExp', spec$lambda$lambda$lambda_16);
    return Unit;
  }
  function spec$lambda$lambda$lambda_17() {
    toBeDefined(expect(null));
    return Unit;
  }
  function spec$lambda$lambda$lambda_18() {
    toBeDefined(expect(new Any()));
    return Unit;
  }
  function spec$lambda$lambda_5() {
    it('should recognize null', spec$lambda$lambda$lambda_17);
    it('should recognize any', spec$lambda$lambda$lambda_18);
    return Unit;
  }
  function spec$lambda$lambda$lambda_19() {
    toBeDefined(expect().not);
    return Unit;
  }
  function spec$lambda$lambda$lambda_20() {
    toBeDefined(expect(undefined).not);
    return Unit;
  }
  function spec$lambda$lambda_6() {
    it('should recognize void 0', spec$lambda$lambda$lambda_19);
    it('should recognize undefined', spec$lambda$lambda$lambda_20);
    return Unit;
  }
  function spec$lambda$lambda$lambda_21() {
    toBeUndefined(expect());
    return Unit;
  }
  function spec$lambda$lambda$lambda_22() {
    toBeUndefined(expect(undefined));
    return Unit;
  }
  function spec$lambda$lambda_7() {
    it('should recognize void 0', spec$lambda$lambda$lambda_21);
    it('should recognize undefined', spec$lambda$lambda$lambda_22);
    return Unit;
  }
  function spec$lambda$lambda$lambda_23() {
    toBeUndefined(expect(null).not);
    return Unit;
  }
  function spec$lambda$lambda$lambda_24() {
    toBeUndefined(expect(new Any()).not);
    return Unit;
  }
  function spec$lambda$lambda_8() {
    it('should recognize null', spec$lambda$lambda$lambda_23);
    it('should recognize any', spec$lambda$lambda$lambda_24);
    return Unit;
  }
  function spec$lambda$lambda$lambda_25() {
    toBeNull(expect(null));
    return Unit;
  }
  function spec$lambda$lambda$lambda_26() {
    var foo = null;
    toBeNull(expect(foo));
    return Unit;
  }
  function spec$lambda$lambda_9() {
    it('should recognize null', spec$lambda$lambda$lambda_25);
    it('should recognize references', spec$lambda$lambda$lambda_26);
    return Unit;
  }
  function spec$lambda$lambda$lambda_27() {
    toBeNull(expect(true).not);
    toBeNull(expect(false).not);
    return Unit;
  }
  function spec$lambda$lambda$lambda_28() {
    toBeNull(expect(0).not);
    toBeNull(expect(1).not);
    toBeNull(expect(-1).not);
    return Unit;
  }
  function spec$lambda$lambda$lambda_29() {
    toBeNull(expect('').not);
    toBeNull(expect('Foobar').not);
    return Unit;
  }
  function spec$lambda$lambda$lambda_30() {
    toBeNull(expect(new Any()).not);
    toBeNull(expect({}).not);
    return Unit;
  }
  function spec$lambda$lambda_10() {
    it('should recognize boolean', spec$lambda$lambda$lambda_27);
    it('should recognize numbers', spec$lambda$lambda$lambda_28);
    it('should recognize strings', spec$lambda$lambda$lambda_29);
    it('should recognize objects', spec$lambda$lambda$lambda_30);
    return Unit;
  }
  function spec$lambda$lambda$lambda_31() {
    toBeTruthy(expect(true));
    return Unit;
  }
  function spec$lambda$lambda$lambda_32() {
    toBeTruthy(expect(1));
    toBeTruthy(expect(IntCompanionObject.MAX_VALUE));
    toBeTruthy(expect(IntCompanionObject.MIN_VALUE));
    return Unit;
  }
  function spec$lambda$lambda$lambda_33() {
    toBeTruthy(expect(0.1));
    return Unit;
  }
  function spec$lambda$lambda$lambda_34() {
    toBeTruthy(expect('0'));
    return Unit;
  }
  function spec$lambda$lambda$lambda_35() {
    toBeTruthy(expect(new Any()));
    return Unit;
  }
  function spec$lambda$lambda_11() {
    it('should recognize booleans', spec$lambda$lambda$lambda_31);
    it('should recognize integers', spec$lambda$lambda$lambda_32);
    it('should recognize floats', spec$lambda$lambda$lambda_33);
    it('should recognize strings', spec$lambda$lambda$lambda_34);
    it('should recognize objects', spec$lambda$lambda$lambda_35);
    return Unit;
  }
  function spec$lambda$lambda$lambda_36() {
    toBeTruthy(expect(null).not);
    return Unit;
  }
  function spec$lambda$lambda$lambda_37() {
    toBeTruthy(expect(undefined).not);
    return Unit;
  }
  function spec$lambda$lambda$lambda_38() {
    toBeTruthy(expect(false).not);
    return Unit;
  }
  function spec$lambda$lambda$lambda_39() {
    toBeTruthy(expect(0).not);
    return Unit;
  }
  function spec$lambda$lambda$lambda_40() {
    toBeTruthy(expect(0.0).not);
    return Unit;
  }
  function spec$lambda$lambda$lambda_41() {
    toBeTruthy(expect('').not);
    return Unit;
  }
  function spec$lambda$lambda$lambda_42() {
    toBeTruthy(expect(FloatCompanionObject.NaN).not);
    return Unit;
  }
  function spec$lambda$lambda_12() {
    it('should recognize null', spec$lambda$lambda$lambda_36);
    it('should recognize undefined', spec$lambda$lambda$lambda_37);
    it('should recognize booleans', spec$lambda$lambda$lambda_38);
    it('should recognize integers', spec$lambda$lambda$lambda_39);
    it('should recognize floats', spec$lambda$lambda$lambda_40);
    it('should recognize strings', spec$lambda$lambda$lambda_41);
    it('should recognize NaN', spec$lambda$lambda$lambda_42);
    return Unit;
  }
  function spec$lambda$lambda$lambda_43() {
    toBeFalsy(expect(null));
    return Unit;
  }
  function spec$lambda$lambda$lambda_44() {
    toBeFalsy(expect(undefined));
    return Unit;
  }
  function spec$lambda$lambda$lambda_45() {
    toBeFalsy(expect(false));
    return Unit;
  }
  function spec$lambda$lambda$lambda_46() {
    toBeFalsy(expect(0));
    return Unit;
  }
  function spec$lambda$lambda$lambda_47() {
    toBeFalsy(expect(0.0));
    return Unit;
  }
  function spec$lambda$lambda$lambda_48() {
    toBeFalsy(expect(''));
    return Unit;
  }
  function spec$lambda$lambda$lambda_49() {
    toBeFalsy(expect(FloatCompanionObject.NaN));
    return Unit;
  }
  function spec$lambda$lambda_13() {
    it('should recognize null', spec$lambda$lambda$lambda_43);
    it('should recognize undefined', spec$lambda$lambda$lambda_44);
    it('should recognize booleans', spec$lambda$lambda$lambda_45);
    it('should recognize integers', spec$lambda$lambda$lambda_46);
    it('should recognize floats', spec$lambda$lambda$lambda_47);
    it('should recognize strings', spec$lambda$lambda$lambda_48);
    it('should recognize NaN', spec$lambda$lambda$lambda_49);
    return Unit;
  }
  function spec$lambda$lambda$lambda_50() {
    toBeFalsy(expect(true).not);
    return Unit;
  }
  function spec$lambda$lambda$lambda_51() {
    toBeTruthy(expect(1));
    toBeFalsy(expect(IntCompanionObject.MAX_VALUE).not);
    toBeFalsy(expect(IntCompanionObject.MIN_VALUE).not);
    return Unit;
  }
  function spec$lambda$lambda$lambda_52() {
    toBeFalsy(expect(0.1).not);
    return Unit;
  }
  function spec$lambda$lambda$lambda_53() {
    toBeFalsy(expect('0').not);
    return Unit;
  }
  function spec$lambda$lambda$lambda_54() {
    toBeFalsy(expect(new Any()).not);
    return Unit;
  }
  function spec$lambda$lambda_14() {
    it('should recognize booleans', spec$lambda$lambda$lambda_50);
    it('should recognize integers', spec$lambda$lambda$lambda_51);
    it('should recognize floats', spec$lambda$lambda$lambda_52);
    it('should recognize strings', spec$lambda$lambda$lambda_53);
    it('should recognize objects', spec$lambda$lambda$lambda_54);
    return Unit;
  }
  function spec$lambda$lambda$lambda_55() {
    toContain(expect('Foobar'), 'bar');
    return Unit;
  }
  function spec$lambda$lambda$lambda_56() {
    toContain_0(expect([1, 2, 3]), 3);
    return Unit;
  }
  function spec$lambda$lambda_15() {
    it('should recognize strings', spec$lambda$lambda$lambda_55);
    it('should recognize arrays', spec$lambda$lambda$lambda_56);
    return Unit;
  }
  function spec$lambda$lambda$lambda_57() {
    toContain(expect('Foobar').not, 'baz');
    return Unit;
  }
  function spec$lambda$lambda$lambda_58() {
    toContain_0(expect([1, 2, 3]).not, 4);
    return Unit;
  }
  function spec$lambda$lambda_16() {
    it('should recognize strings', spec$lambda$lambda$lambda_57);
    it('should recognize arrays', spec$lambda$lambda$lambda_58);
    return Unit;
  }
  function spec$lambda$lambda$lambda_59() {
    toBeLessThan(expect(42), 43);
    toBeLessThan(expect(-100), -90);
    return Unit;
  }
  function spec$lambda$lambda$lambda_60() {
    toBeLessThan(expect(42.0), 42.01);
    toBeLessThan(expect(-0.1), 0.0);
    return Unit;
  }
  function spec$lambda$lambda_17() {
    it('should recognize integers', spec$lambda$lambda$lambda_59);
    it('should recognize floats', spec$lambda$lambda$lambda_60);
    return Unit;
  }
  function spec$lambda$lambda$lambda_61() {
    toBeLessThan(expect(43).not, 42);
    toBeLessThan(expect(-100).not, -101);
    return Unit;
  }
  function spec$lambda$lambda$lambda_62() {
    toBeLessThan(expect(42.01).not, 42.0);
    toBeLessThan(expect(0).not, -0.1);
    return Unit;
  }
  function spec$lambda$lambda_18() {
    it('should recognize integers', spec$lambda$lambda$lambda_61);
    it('should recognize floats', spec$lambda$lambda$lambda_62);
    return Unit;
  }
  function spec$lambda$lambda$lambda_63() {
    toBeGreaterThan(expect(43), 42);
    toBeGreaterThan(expect(-100), -101);
    return Unit;
  }
  function spec$lambda$lambda$lambda_64() {
    toBeGreaterThan(expect(42.01), 42.0);
    toBeGreaterThan(expect(0), -0.1);
    return Unit;
  }
  function spec$lambda$lambda_19() {
    it('should recognize integers', spec$lambda$lambda$lambda_63);
    it('should recognize floats', spec$lambda$lambda$lambda_64);
    return Unit;
  }
  function spec$lambda$lambda$lambda_65() {
    toBeGreaterThan(expect(42).not, 43);
    toBeGreaterThan(expect(-100).not, -90);
    return Unit;
  }
  function spec$lambda$lambda$lambda_66() {
    toBeGreaterThan(expect(42.0).not, 42.01);
    toBeGreaterThan(expect(-0.1).not, 0.0);
    return Unit;
  }
  function spec$lambda$lambda_20() {
    it('should recognize integers', spec$lambda$lambda$lambda_65);
    it('should recognize floats', spec$lambda$lambda$lambda_66);
    return Unit;
  }
  function spec$lambda$lambda$lambda_67() {
    toBeNaN(expect(FloatCompanionObject.NaN));
    return Unit;
  }
  function spec$lambda$lambda$lambda_68() {
    toBeNaN(expect(DoubleCompanionObject.NaN));
    return Unit;
  }
  function spec$lambda$lambda_21() {
    it('should recognize Float.NaN', spec$lambda$lambda$lambda_67);
    it('should recognize Double.NaN', spec$lambda$lambda$lambda_68);
    return Unit;
  }
  function spec$lambda$lambda$lambda_69() {
    toBeNaN(expect(0).not);
    toBeNaN(expect(42).not);
    return Unit;
  }
  function spec$lambda$lambda$lambda_70() {
    toBeNaN(expect(0.0).not);
    toBeNaN(expect(-0.1).not);
    return Unit;
  }
  function spec$lambda$lambda_22() {
    it('should recognize integers', spec$lambda$lambda$lambda_69);
    it('should recognize floats', spec$lambda$lambda$lambda_70);
    return Unit;
  }
  function spec$lambda$lambda$lambda_71() {
    toBeCloseTo(expect(42.0), 42.4, 0);
    return Unit;
  }
  function spec$lambda$lambda$lambda_72() {
    toBeCloseTo(expect(42.0), 42.05, 1);
    return Unit;
  }
  function spec$lambda$lambda_23() {
    it('should recognize precision 0', spec$lambda$lambda$lambda_71);
    it('should recognize precision 1', spec$lambda$lambda$lambda_72);
    return Unit;
  }
  function spec$lambda$lambda$lambda_73() {
    toBeCloseTo(expect(42.0).not, 43.0, 0);
    return Unit;
  }
  function spec$lambda$lambda$lambda_74() {
    toBeCloseTo(expect(42.0).not, 42.5, 1);
    return Unit;
  }
  function spec$lambda$lambda_24() {
    it('should recognize precision 0', spec$lambda$lambda$lambda_73);
    it('should recognize precision 1', spec$lambda$lambda$lambda_74);
    return Unit;
  }
  function spec$lambda$lambda$lambda$lambda() {
    throw new Exception('42');
  }
  function spec$lambda$lambda$lambda_75() {
    toThrow(expect(spec$lambda$lambda$lambda$lambda));
    return Unit;
  }
  function spec$lambda$lambda$lambda$lambda_0() {
    throw new Error_0('foobar');
  }
  function spec$lambda$lambda$lambda_76() {
    toThrow(expect(spec$lambda$lambda$lambda$lambda_0));
    return Unit;
  }
  function spec$lambda$lambda_25() {
    it('should recognize exceptions', spec$lambda$lambda$lambda_75);
    it('should recognize errors', spec$lambda$lambda$lambda_76);
    return Unit;
  }
  function spec$lambda$lambda$lambda$lambda_1() {
    return 42;
  }
  function spec$lambda$lambda$lambda_77() {
    toThrow(expect(spec$lambda$lambda$lambda$lambda_1).not);
    return Unit;
  }
  function spec$lambda$lambda_26() {
    it('should recognize exceptions', spec$lambda$lambda$lambda_77);
    return Unit;
  }
  function spec$lambda() {
    describe('toBe', spec$lambda$lambda);
    describe('not.toBe', spec$lambda$lambda_0);
    describe('toEqual', spec$lambda$lambda_1);
    describe('not.toEqual', spec$lambda$lambda_2);
    describe('toMatch', spec$lambda$lambda_3);
    describe('not.toMatch', spec$lambda$lambda_4);
    describe('toBeDefined', spec$lambda$lambda_5);
    describe('not.toBeDefined', spec$lambda$lambda_6);
    describe('toBeUndefined', spec$lambda$lambda_7);
    describe('not.toBeUndefined', spec$lambda$lambda_8);
    describe('toBeNull', spec$lambda$lambda_9);
    describe('not.toBeNull', spec$lambda$lambda_10);
    describe('toBeTruthy', spec$lambda$lambda_11);
    describe('not.toBeTruthy', spec$lambda$lambda_12);
    describe('toBeFalsy', spec$lambda$lambda_13);
    describe('not.toBeFalsy', spec$lambda$lambda_14);
    describe('toContain', spec$lambda$lambda_15);
    describe('not.toContain', spec$lambda$lambda_16);
    describe('toBeLessThan', spec$lambda$lambda_17);
    describe('not.toBeLessThan', spec$lambda$lambda_18);
    describe('toBeGreaterThan', spec$lambda$lambda_19);
    describe('not.toBeGreaterThan', spec$lambda$lambda_20);
    describe('toBeNaN', spec$lambda$lambda_21);
    describe('not.toBeNaN', spec$lambda$lambda_22);
    describe('toBeCloseTo', spec$lambda$lambda_23);
    describe('not.toBeCloseTo', spec$lambda$lambda_24);
    describe('toThrow', spec$lambda$lambda_25);
    describe('not.toThrow', spec$lambda$lambda_26);
    return Unit;
  }
  var spec;
  var toBeFoo;
  function matchers$lambda$lambda($receiver, actual) {
    var expected = 'Foo';
    if ($receiver.equals_oaftn8$(actual, expected) === true)
      return $receiver.pass();
    else
      return $receiver.fail_aa41mf$(actual, [expected]);
  }
  function matchers$lambda$lambda_0($receiver, actual) {
    if (equals(actual, 'bar', true) === true)
      return $receiver.pass();
    else
      return $receiver.failWithMessage_61zpoe$(actual + ' is not Bar');
  }
  function matchers$lambda$lambda_1($receiver, actual) {
    if (equals(actual, 'foobar', true) === true)
      return $receiver.pass();
    else
      return $receiver.failWithMessage_61zpoe$(actual + ' is not Foobar');
  }
  function matchers$lambda$lambda_2($receiver, actual) {
    var expected = 'baz';
    if ($receiver.equals_oaftn8$(actual, expected) === true)
      return $receiver.pass();
    else
      return $receiver.fail_aa41mf$(actual, [expected]);
  }
  function matchers$lambda$lambda_3($receiver, actual, expected) {
    if ($receiver.equals_oaftn8$(actual * 42 | 0, expected) === true)
      return $receiver.pass();
    else
      return $receiver.fail_aa41mf$(actual, [expected]);
  }
  function matchers$lambda$lambda_4($receiver, actual, expected, context) {
    if (actual === expected * context === true)
      return $receiver.pass();
    else
      return $receiver.failWithMessage_61zpoe$(actual.toString() + ' is not the product of ' + expected + ' * ' + context);
  }
  function matchers$lambda$lambda_5($receiver, actual, expected, context) {
    if ($receiver.equals_oaftn8$(actual, expected + context | 0) === true)
      return $receiver.pass();
    else
      return $receiver.fail_aa41mf$(actual, [expected, context]);
  }
  function matchers$lambda($receiver) {
    $receiver.matcher_ulpofw$(toBeFoo, matchers$lambda$lambda);
    $receiver.matcher_ulpofw$('toBeBar', matchers$lambda$lambda_0);
    $receiver.matcher_ulpofw$('toBeFoobar', matchers$lambda$lambda_1);
    $receiver.matcher_ulpofw$('toBeBaz', matchers$lambda$lambda_2);
    $receiver.matcher_u998mw$('times42ToEqual', matchers$lambda$lambda_3);
    $receiver.matcher_4aik8o$('toBeProductOf', matchers$lambda$lambda_4);
    $receiver.matcher_4aik8o$('toBeSumOf', matchers$lambda$lambda_5);
    return Unit;
  }
  var matchers_0;
  function toBeFoo_0($receiver) {
    match($receiver, toBeFoo);
  }
  function toBeBar($receiver) {
    match($receiver, 'toBeBar');
  }
  function toBeFoobar($receiver) {
    match($receiver, 'toBeFoobar');
  }
  function toBeBaz($receiver) {
    match($receiver, 'toBeBaz');
  }
  function times42ToEqual($receiver, expected) {
    match_0($receiver, 'times42ToEqual', expected);
  }
  function toBeProductOf($receiver, factor1, factor2) {
    match_1($receiver, 'toBeProductOf', factor1, factor2);
  }
  function toBeSumOf($receiver, op1, op2) {
    match_1($receiver, 'toBeSumOf', op1, op2);
  }
  function spec$lambda$lambda_27() {
    jasmine.addMatchers(matchers_0);
    return Unit;
  }
  function spec$lambda$lambda$lambda_78() {
    toBeFoo_0(expect('Foo'));
    return Unit;
  }
  function spec$lambda$lambda_28() {
    it('should work', spec$lambda$lambda$lambda_78);
    return Unit;
  }
  function spec$lambda$lambda$lambda_79() {
    toBeFoo_0(expect('bar').not);
    return Unit;
  }
  function spec$lambda$lambda_29() {
    it('should work', spec$lambda$lambda$lambda_79);
    return Unit;
  }
  function spec$lambda$lambda$lambda_80() {
    toBeBar(expect('BAR'));
    return Unit;
  }
  function spec$lambda$lambda_30() {
    it('should work', spec$lambda$lambda$lambda_80);
    return Unit;
  }
  function spec$lambda$lambda$lambda_81() {
    toBeBar(expect('foo').not);
    return Unit;
  }
  function spec$lambda$lambda_31() {
    it('should work', spec$lambda$lambda$lambda_81);
    return Unit;
  }
  function spec$lambda$lambda$lambda_82() {
    toBeFoobar(expect('FooBar'));
    return Unit;
  }
  function spec$lambda$lambda_32() {
    it('should work', spec$lambda$lambda$lambda_82);
    return Unit;
  }
  function spec$lambda$lambda$lambda_83() {
    toBeFoobar(expect('baz').not);
    return Unit;
  }
  function spec$lambda$lambda_33() {
    it('should work', spec$lambda$lambda$lambda_83);
    return Unit;
  }
  function spec$lambda$lambda$lambda_84() {
    toBeBaz(expect('baz'));
    return Unit;
  }
  function spec$lambda$lambda_34() {
    it('should work', spec$lambda$lambda$lambda_84);
    return Unit;
  }
  function spec$lambda$lambda$lambda_85() {
    toBeBaz(expect('foobar').not);
    return Unit;
  }
  function spec$lambda$lambda_35() {
    it('should work', spec$lambda$lambda$lambda_85);
    return Unit;
  }
  function spec$lambda$lambda$lambda_86() {
    times42ToEqual(expect(2), 84);
    return Unit;
  }
  function spec$lambda$lambda_36() {
    it('should work', spec$lambda$lambda$lambda_86);
    return Unit;
  }
  function spec$lambda$lambda$lambda_87() {
    times42ToEqual(expect(3).not, 84);
    return Unit;
  }
  function spec$lambda$lambda_37() {
    it('should work', spec$lambda$lambda$lambda_87);
    return Unit;
  }
  function spec$lambda$lambda$lambda_88() {
    toBeProductOf(expect(16.0), 4.0, 4.0);
    return Unit;
  }
  function spec$lambda$lambda_38() {
    it('should work', spec$lambda$lambda$lambda_88);
    return Unit;
  }
  function spec$lambda$lambda$lambda_89() {
    toBeProductOf(expect(16.1).not, 4.0, 4.0);
    return Unit;
  }
  function spec$lambda$lambda_39() {
    it('should work', spec$lambda$lambda$lambda_89);
    return Unit;
  }
  function spec$lambda$lambda$lambda_90() {
    toBeSumOf(expect(42), 40, 2);
    return Unit;
  }
  function spec$lambda$lambda_40() {
    it('should work', spec$lambda$lambda$lambda_90);
    return Unit;
  }
  function spec$lambda$lambda$lambda_91() {
    toBeSumOf(expect(42).not, 40, 1);
    return Unit;
  }
  function spec$lambda$lambda_41() {
    it('should work', spec$lambda$lambda$lambda_91);
    return Unit;
  }
  function spec$lambda_0() {
    beforeEach(spec$lambda$lambda_27);
    describe('toBeFoo', spec$lambda$lambda_28);
    describe('not.toBeFoo', spec$lambda$lambda_29);
    describe('toBeBar', spec$lambda$lambda_30);
    describe('not.toBeBar', spec$lambda$lambda_31);
    describe('toBeFoobar', spec$lambda$lambda_32);
    describe('not.toBeFoobar', spec$lambda$lambda_33);
    describe('toBeBaz', spec$lambda$lambda_34);
    describe('not.toBeBaz', spec$lambda$lambda_35);
    describe('times42ToEqual', spec$lambda$lambda_36);
    describe('not.times42ToEqual', spec$lambda$lambda_37);
    describe('toBeProductOf', spec$lambda$lambda_38);
    describe('not.toBeProductOf', spec$lambda$lambda_39);
    describe('toBeSumOf', spec$lambda$lambda_40);
    describe('not.toBeSumOf', spec$lambda$lambda_41);
    return Unit;
  }
  var spec_0;
  var toBeFoo_1;
  var toSharePrefixWith;
  function customMatchers$lambda$lambda($receiver, actual) {
    if ($receiver.equals_oaftn8$(actual, 'Foo') === true)
      return $receiver.pass();
    else
      return $receiver.fail_aa41mf$(actual, ['Foo']);
  }
  function customMatchers$lambda$lambda_0($receiver, actual, expected, context) {
    console.log('compare');
    var actualToken = actual.substring(0, context);
    var expectedToken = expected.substring(0, context);
    if ($receiver.equals_oaftn8$(actualToken, expectedToken) === true)
      return $receiver.pass();
    else
      return $receiver.fail_s8jyv4$(actual + " doesn't share prefix with " + expected + ' of length ' + context);
  }
  function customMatchers$lambda$lambda_1($receiver, actual, expected, context) {
    console.log('negativeCompare');
    var actualToken = actual.substring(0, context);
    var expectedToken = expected.substring(0, context);
    if ($receiver.equals_oaftn8$(actualToken, expectedToken) === true)
      return $receiver.fail_s8jyv4$(actual + ' shares prefix with ' + expected + ' of length ' + context);
    else
      return $receiver.pass();
  }
  function customMatchers$lambda($receiver) {
    $receiver.matcher_ulpofw$(toBeFoo_1, customMatchers$lambda$lambda);
    $receiver.matcher_rq4ut9$(toSharePrefixWith, customMatchers$lambda$lambda_0, customMatchers$lambda$lambda_1);
    return Unit;
  }
  var customMatchers;
  function toBeFoo_2($receiver) {
    match($receiver, toBeFoo_1);
  }
  function toSharePrefixWith_0($receiver, expected, context) {
    match_1($receiver, toSharePrefixWith, expected, context);
  }
  function spec$lambda$lambda_42() {
    jasmine.addMatchers(customMatchers);
    return Unit;
  }
  function spec$lambda$lambda_43() {
    toBeFoo_2(expect('Foo'));
    return Unit;
  }
  function spec$lambda$lambda_44() {
    toBeFoo_2(expect('Bar').not);
    return Unit;
  }
  function spec$lambda$lambda_45() {
    toSharePrefixWith_0(expect('Foo'), 'Foobar', 3);
    return Unit;
  }
  function spec$lambda$lambda_46() {
    toSharePrefixWith_0(expect('Bar').not, 'Foobar', 3);
    return Unit;
  }
  function spec$lambda_1() {
    beforeEach(spec$lambda$lambda_42);
    it('Foo should be Foo', spec$lambda$lambda_43);
    it('Bar should not be Foo', spec$lambda$lambda_44);
    it('compare', spec$lambda$lambda_45);
    it('negativeCompare', spec$lambda$lambda_46);
    return Unit;
  }
  var spec_1;
  spec = describe('expect', spec$lambda);
  toBeFoo = 'toBeFoo';
  matchers_0 = matchers(matchers$lambda);
  spec_0 = describe('matcher', spec$lambda_0);
  toBeFoo_1 = 'toBeFoo';
  toSharePrefixWith = 'toSharePrefixWith';
  customMatchers = matchers(customMatchers$lambda);
  spec_1 = describe('custom matchers', spec$lambda_1);
  Kotlin.defineModule('kotlin-jasmine-core-tests', _);
  return _;
}));

//# sourceMappingURL=kotlin-jasmine-core-tests.js.map
