(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'kotlin-jasmine-core'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'kotlin-jasmine-core'.");
    }
    root['kotlin-jasmine-core'] = factory(typeof this['kotlin-jasmine-core'] === 'undefined' ? {} : this['kotlin-jasmine-core'], kotlin);
  }
}(this, function (_, Kotlin) {
  'use strict';
  PositiveComparison.prototype = Object.create(BaseComparison.prototype);
  PositiveComparison.prototype.constructor = PositiveComparison;
  NegativeComparison.prototype = Object.create(BaseComparison.prototype);
  NegativeComparison.prototype.constructor = NegativeComparison;
  function Comparison() {
  }
  Comparison.$metadata$ = {
    kind: Kotlin.Kind.INTERFACE,
    simpleName: 'Comparison',
    interfaces: []
  };
  function BaseComparison(matcherName, util, customTesters, isNot) {
    this.matcherName_0 = matcherName;
    this.util_0 = util;
    this.customTesters_0 = customTesters;
    this.isNot_0 = isNot;
  }
  BaseComparison.prototype.pass = function () {
    return new Result(true);
  };
  BaseComparison.prototype.fail_s8jyv4$ = function (actual) {
    return new Result(false, this.util_0.buildFailureMessage(this.matcherName_0, this.isNot_0, actual));
  };
  BaseComparison.prototype.fail_aa41mf$ = function (actual, expected) {
    return new Result(false, this.util_0.buildFailureMessage(this.matcherName_0, this.isNot_0, actual, expected));
  };
  BaseComparison.prototype.failWithMessage_61zpoe$ = function (message) {
    return new Result(false, message);
  };
  BaseComparison.prototype.equals_oaftn8$ = function (actual, expected) {
    return this.util_0.equals(actual, expected, this.customTesters_0);
  };
  BaseComparison.prototype.contains_oaftn8$ = function (haystack, needle) {
    return this.util_0.contains(haystack, needle, this.customTesters_0);
  };
  BaseComparison.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: 'BaseComparison',
    interfaces: [Comparison]
  };
  function PositiveComparison(matcherName, util, customTesters) {
    BaseComparison.call(this, matcherName, util, customTesters, false);
  }
  PositiveComparison.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: 'PositiveComparison',
    interfaces: [BaseComparison]
  };
  function NegativeComparison(matcherName, util, customTesters) {
    BaseComparison.call(this, matcherName, util, customTesters, true);
  }
  NegativeComparison.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: 'NegativeComparison',
    interfaces: [BaseComparison]
  };
  function toBe($receiver, expected) {
    $receiver.toBe(expected);
  }
  function toEqual($receiver, expected) {
    $receiver.toEqual(expected);
  }
  function toBeDefined($receiver) {
    $receiver.toBeDefined();
  }
  function toBeUndefined($receiver) {
    $receiver.toBeUndefined();
  }
  function toBeNull($receiver) {
    $receiver.toBeNull();
  }
  function toBeTruthy($receiver) {
    $receiver.toBeTruthy();
  }
  function toBeFalsy($receiver) {
    $receiver.toBeFalsy();
  }
  function toThrow($receiver) {
    $receiver.toThrow();
  }
  function toBeLessThan($receiver, expected) {
    $receiver.toBeLessThan(expected);
  }
  function toBeGreaterThan($receiver, expected) {
    $receiver.toBeGreaterThan(expected);
  }
  function toBeNaN($receiver) {
    $receiver.toBeNaN();
  }
  function toBeCloseTo($receiver, expected, precision) {
    $receiver.toBeCloseTo(expected, precision);
  }
  function toMatch($receiver, pattern) {
    $receiver.toMatch(pattern);
  }
  function toMatch_0($receiver, regex) {
    $receiver.toMatch(regex);
  }
  function toContain($receiver, value) {
    $receiver.toContain(value);
  }
  function toContain_0($receiver, value) {
    $receiver.toContain(value);
  }
  function match($receiver, matcherName) {
    $receiver[matcherName]();
  }
  function match_0($receiver, matcherName, expected) {
    $receiver[matcherName](expected);
  }
  function match_1($receiver, matcherName, expected, context) {
    $receiver[matcherName](expected, context);
  }
  function MatcherDefinitions() {
  }
  MatcherDefinitions.$metadata$ = {
    kind: Kotlin.Kind.INTERFACE,
    simpleName: 'MatcherDefinitions',
    interfaces: []
  };
  function DynamicMatcherDefinitions() {
    this.matcherDefinitions_0 = {};
  }
  Object.defineProperty(DynamicMatcherDefinitions.prototype, 'matcherRegistrations', {
    get: function () {
      return this.matcherDefinitions_0;
    }
  });
  DynamicMatcherDefinitions.prototype.matcher_4aik8o$ = function (name, compare) {
    this.defineMatcher_0(name, compare);
  };
  DynamicMatcherDefinitions.prototype.matcher_rq4ut9$ = function (name, compare, negativeCompare) {
    this.defineMatcher_0(name, compare, negativeCompare);
  };
  DynamicMatcherDefinitions.prototype.matcher_u998mw$ = function (name, compare) {
    this.defineMatcher_1(name, compare);
  };
  DynamicMatcherDefinitions.prototype.matcher_tono6w$ = function (name, compare, negativeCompare) {
    this.defineMatcher_1(name, compare, negativeCompare);
  };
  DynamicMatcherDefinitions.prototype.matcher_ulpofw$ = function (name, compare) {
    this.defineMatcher_2(name, compare);
  };
  DynamicMatcherDefinitions.prototype.matcher_gc4aso$ = function (name, compare, negativeCompare) {
    this.defineMatcher_2(name, compare, negativeCompare);
  };
  function DynamicMatcherDefinitions$defineMatcher$lambda$lambda(closure$name, closure$util, closure$customEqualityTesters, closure$compare) {
    return function (actual, expected, context) {
      return closure$compare(new PositiveComparison(closure$name, closure$util, closure$customEqualityTesters), actual, expected, context);
    };
  }
  function DynamicMatcherDefinitions$defineMatcher$lambda$lambda_0(closure$name, closure$util, closure$customEqualityTesters, closure$negativeCompare) {
    return function (actual, expected, context) {
      return closure$negativeCompare(new NegativeComparison(closure$name, closure$util, closure$customEqualityTesters), actual, expected, context);
    };
  }
  function DynamicMatcherDefinitions$defineMatcher$lambda(closure$name, closure$compare, closure$negativeCompare) {
    return function (util, customEqualityTesters) {
      var matcher = {};
      matcher['compare'] = DynamicMatcherDefinitions$defineMatcher$lambda$lambda(closure$name, util, customEqualityTesters, closure$compare);
      if (closure$negativeCompare != null) {
        matcher['negativeCompare'] = DynamicMatcherDefinitions$defineMatcher$lambda$lambda_0(closure$name, util, customEqualityTesters, closure$negativeCompare);
      }
      return matcher;
    };
  }
  DynamicMatcherDefinitions.prototype.defineMatcher_0 = function (name, compare, negativeCompare) {
    if (negativeCompare === void 0)
      negativeCompare = null;
    this.matcherDefinitions_0[name] = DynamicMatcherDefinitions$defineMatcher$lambda(name, compare, negativeCompare);
  };
  function DynamicMatcherDefinitions$defineMatcher$lambda$lambda_1(closure$name, closure$util, closure$customEqualityTesters, closure$compare) {
    return function (actual, expected) {
      return closure$compare(new PositiveComparison(closure$name, closure$util, closure$customEqualityTesters), actual, expected);
    };
  }
  function DynamicMatcherDefinitions$defineMatcher$lambda$lambda_2(closure$name, closure$util, closure$customEqualityTesters, closure$negativeCompare) {
    return function (actual, expected) {
      return closure$negativeCompare(new NegativeComparison(closure$name, closure$util, closure$customEqualityTesters), actual, expected);
    };
  }
  function DynamicMatcherDefinitions$defineMatcher$lambda_0(closure$name, closure$compare, closure$negativeCompare) {
    return function (util, customEqualityTesters) {
      var matcher = {};
      matcher['compare'] = DynamicMatcherDefinitions$defineMatcher$lambda$lambda_1(closure$name, util, customEqualityTesters, closure$compare);
      if (closure$negativeCompare != null) {
        matcher['negativeCompare'] = DynamicMatcherDefinitions$defineMatcher$lambda$lambda_2(closure$name, util, customEqualityTesters, closure$negativeCompare);
      }
      return matcher;
    };
  }
  DynamicMatcherDefinitions.prototype.defineMatcher_1 = function (name, compare, negativeCompare) {
    if (negativeCompare === void 0)
      negativeCompare = null;
    this.matcherDefinitions_0[name] = DynamicMatcherDefinitions$defineMatcher$lambda_0(name, compare, negativeCompare);
  };
  function DynamicMatcherDefinitions$defineMatcher$lambda$lambda_3(closure$name, closure$util, closure$customEqualityTesters, closure$compare) {
    return function (actual) {
      return closure$compare(new PositiveComparison(closure$name, closure$util, closure$customEqualityTesters), actual);
    };
  }
  function DynamicMatcherDefinitions$defineMatcher$lambda$lambda_4(closure$name, closure$util, closure$customEqualityTesters, closure$negativeCompare) {
    return function (actual) {
      return closure$negativeCompare(new NegativeComparison(closure$name, closure$util, closure$customEqualityTesters), actual);
    };
  }
  function DynamicMatcherDefinitions$defineMatcher$lambda_1(closure$name, closure$compare, closure$negativeCompare) {
    return function (util, customEqualityTesters) {
      var matcher = {};
      matcher['compare'] = DynamicMatcherDefinitions$defineMatcher$lambda$lambda_3(closure$name, util, customEqualityTesters, closure$compare);
      if (closure$negativeCompare != null) {
        matcher['negativeCompare'] = DynamicMatcherDefinitions$defineMatcher$lambda$lambda_4(closure$name, util, customEqualityTesters, closure$negativeCompare);
      }
      return matcher;
    };
  }
  DynamicMatcherDefinitions.prototype.defineMatcher_2 = function (name, compare, negativeCompare) {
    if (negativeCompare === void 0)
      negativeCompare = null;
    this.matcherDefinitions_0[name] = DynamicMatcherDefinitions$defineMatcher$lambda_1(name, compare, negativeCompare);
  };
  DynamicMatcherDefinitions.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: 'DynamicMatcherDefinitions',
    interfaces: [MatcherDefinitions]
  };
  function Result(pass, message) {
    if (message === void 0)
      message = null;
    this.pass = pass;
    this.message = message;
  }
  Result.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: 'Result',
    interfaces: []
  };
  function matchers(matcherFactories) {
    var definitions = new DynamicMatcherDefinitions();
    matcherFactories(definitions);
    return definitions.matcherRegistrations;
  }
  var package$jasmine = _.jasmine || (_.jasmine = {});
  package$jasmine.Comparison = Comparison;
  package$jasmine.BaseComparison = BaseComparison;
  package$jasmine.PositiveComparison = PositiveComparison;
  package$jasmine.NegativeComparison = NegativeComparison;
  package$jasmine.toBe_yixw8n$ = toBe;
  package$jasmine.toEqual_yixw8n$ = toEqual;
  package$jasmine.toBeDefined_uwg63b$ = toBeDefined;
  package$jasmine.toBeUndefined_uwg63b$ = toBeUndefined;
  package$jasmine.toBeNull_uwg63b$ = toBeNull;
  package$jasmine.toBeTruthy_uwg63b$ = toBeTruthy;
  package$jasmine.toBeFalsy_uwg63b$ = toBeFalsy;
  package$jasmine.toThrow_2f5p37$ = toThrow;
  package$jasmine.toBeLessThan_1fe7tk$ = toBeLessThan;
  package$jasmine.toBeGreaterThan_1fe7tk$ = toBeGreaterThan;
  package$jasmine.toBeNaN_sa041q$ = toBeNaN;
  package$jasmine.toBeCloseTo_8m97dy$ = toBeCloseTo;
  package$jasmine.toMatch_xniniu$ = toMatch;
  package$jasmine.toMatch_e02pc9$ = toMatch_0;
  package$jasmine.toContain_xniniu$ = toContain;
  package$jasmine.toContain_r7b3v0$ = toContain_0;
  package$jasmine.match_n21y3s$ = match;
  package$jasmine.match_vap8h8$ = match_0;
  package$jasmine.match_pcoc00$ = match_1;
  package$jasmine.MatcherDefinitions = MatcherDefinitions;
  package$jasmine.DynamicMatcherDefinitions = DynamicMatcherDefinitions;
  package$jasmine.Result = Result;
  package$jasmine.matchers_qxnh2$ = matchers;
  Kotlin.defineModule('kotlin-jasmine-core', _);
  return _;
}));

//# sourceMappingURL=kotlin-jasmine-core.js.map
