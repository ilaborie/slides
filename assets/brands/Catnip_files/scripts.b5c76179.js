// modules are defined as an array
// [ module function, map of requires ]
//
// map of requires is short require name -> numeric require
//
// anything defined in a previous bundle is accessed via the
// orig method which is the require for previous bundles

// eslint-disable-next-line no-global-assign
parcelRequire = (function (modules, cache, entry) {
  // Save the require from previous bundle to this closure if any
  var previousRequire = typeof parcelRequire === 'function' && parcelRequire;
  var nodeRequire = typeof require === 'function' && require;

  function newRequire(name, jumped) {
    if (!cache[name]) {
      if (!modules[name]) {
        // if we cannot find the module within our internal map or
        // cache jump to the current global require ie. the last bundle
        // that was added to the page.
        var currentRequire = typeof parcelRequire === 'function' && parcelRequire;
        if (!jumped && currentRequire) {
          return currentRequire(name, true);
        }

        // If there are other bundles on this page the require from the
        // previous one is saved to 'previousRequire'. Repeat this as
        // many times as there are bundles until the module is found or
        // we exhaust the require chain.
        if (previousRequire) {
          return previousRequire(name, true);
        }

        // Try the node require function if it exists.
        if (nodeRequire && typeof name === 'string') {
          return nodeRequire(name);
        }

        var err = new Error('Cannot find module \'' + name + '\'');
        err.code = 'MODULE_NOT_FOUND';
        throw err;
      }

      localRequire.resolve = resolve;

      var module = cache[name] = new newRequire.Module(name);

      modules[name][0].call(module.exports, localRequire, module, module.exports);
    }

    return cache[name].exports;

    function localRequire(x){
      return newRequire(localRequire.resolve(x));
    }

    function resolve(x){
      return modules[name][1][x] || x;
    }
  }

  function Module(moduleName) {
    this.id = moduleName;
    this.bundle = newRequire;
    this.exports = {};
  }

  newRequire.isParcelRequire = true;
  newRequire.Module = Module;
  newRequire.modules = modules;
  newRequire.cache = cache;
  newRequire.parent = previousRequire;

  for (var i = 0; i < entry.length; i++) {
    newRequire(entry[i]);
  }

  // Override the current require with this new one
  return newRequire;
})({39:[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", { value: true });
function TODO() {
    var reason = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : "";

    throw new Error("Not Implemented" + (reason ? ": " + reason : ""));
}
exports.TODO = TODO;
},{}],21:[function(require,module,exports) {
"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

Object.defineProperty(exports, "__esModule", { value: true });
var utils_1 = require("./utils");

var Frame = function () {
    function Frame(parent, name, position, args, methodBody, stack, locals) {
        _classCallCheck(this, Frame);

        this.parent = parent;
        this.name = name;
        this.position = position;
        this.args = args;
        this.methodBody = methodBody;
        this.stack = stack;
        this.locals = locals;
    }

    _createClass(Frame, [{
        key: "next",
        value: function next(position) {
            utils_1.TODO("Frame#next");
        }
    }, {
        key: "return",
        value: function _return(value) {
            utils_1.TODO("Frame#return");
        }
    }, {
        key: "callStaticMethod",
        value: function callStaticMethod(value) {
            utils_1.TODO("Frame#callStaticMethod");
        }
    }, {
        key: "callObjectMethod",
        value: function callObjectMethod(value) {
            utils_1.TODO("Frame#callObjectMethod");
        }
    }]);

    return Frame;
}();

exports.Frame = Frame;
},{"./utils":39}],22:[function(require,module,exports) {
"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

Object.defineProperty(exports, "__esModule", { value: true });

var LocalVariables = function () {
    function LocalVariables(size) {
        _classCallCheck(this, LocalVariables);

        this._list = [];
        this._list = new Array(size);
    }

    _createClass(LocalVariables, [{
        key: "get",
        value: function get(i) {
            return this._list[i];
        }
    }, {
        key: "set",
        value: function set(i, value) {
            this._list[i] = value;
        }
    }, {
        key: "list",
        get: function get() {
            return this._list;
        }
    }]);

    return LocalVariables;
}();

exports.LocalVariables = LocalVariables;
},{}],23:[function(require,module,exports) {
"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

Object.defineProperty(exports, "__esModule", { value: true });

var OperandStack = function () {
    function OperandStack(size) {
        _classCallCheck(this, OperandStack);

        this._position = 0;
        this._list = new Array(size);
    }

    _createClass(OperandStack, [{
        key: "push",
        value: function push(value) {
            this._list[this._position] = value;
            this._position++;
        }
    }, {
        key: "pop",
        value: function pop() {
            var v = this._list[this._position];
            this._position--;
            return v;
        }
    }, {
        key: "peek",
        value: function peek() {
            return this._list[this._position];
        }
    }, {
        key: "list",
        get: function get() {
            return this._list;
        }
    }]);

    return OperandStack;
}();

exports.OperandStack = OperandStack;
},{}],10:[function(require,module,exports) {
"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

Object.defineProperty(exports, "__esModule", { value: true });
var Frame_1 = require("./Frame");
var LocalVariables_1 = require("./LocalVariables");
var OperandStack_1 = require("./OperandStack");

var Frames = function () {
    function Frames(constantPool, methods) {
        _classCallCheck(this, Frames);

        this.constantPool = constantPool;
        this.methods = methods;
        this.frames = [];
        var mainMethod = methods.get("main");
        if (!mainMethod) {
            throw new Error("'main' method is missing");
        }
        var mainFrame = new Frame_1.Frame(this, "main", mainMethod.code[0].position, [], mainMethod, new OperandStack_1.OperandStack(mainMethod.stack), new LocalVariables_1.LocalVariables(mainMethod.locals));
        this.frames.push(mainFrame);
    }

    _createClass(Frames, [{
        key: "next",
        value: function next() {
            var currentFrame = this.frames[this.frames.length - 1];
            var methodBody = currentFrame.methodBody,
                position = currentFrame.position;

            var instruction = methodBody.code.find(function (inst) {
                return inst.position == position;
            });
            if (!instruction) {
                throw new Error("No instruction for position " + position);
            }
            console.log("Eval " + instruction.instruction.code);
            instruction.instruction.apply(currentFrame);
            console.log({ currentFrame: currentFrame });
        }
    }]);

    return Frames;
}();

exports.Frames = Frames;
},{"./Frame":21,"./LocalVariables":22,"./OperandStack":23}],25:[function(require,module,exports) {
"use strict";

var _slicedToArray = function () { function sliceIterator(arr, i) { var _arr = []; var _n = true; var _d = false; var _e = undefined; try { for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) { _arr.push(_s.value); if (i && _arr.length === i) break; } } catch (err) { _d = true; _e = err; } finally { try { if (!_n && _i["return"]) _i["return"](); } finally { if (_d) throw _e; } } return _arr; } return function (arr, i) { if (Array.isArray(arr)) { return arr; } else if (Symbol.iterator in Object(arr)) { return sliceIterator(arr, i); } else { throw new TypeError("Invalid attempt to destructure non-iterable instance"); } }; }();

Object.defineProperty(exports, "__esModule", { value: true });
var createConstant = function createConstant(s) {
    var regexpConstant = /^\s*#(\d+) = ([a-zA-Z0-8]*)\s+(\S*)(\s+\/\/\s(.*))?$/gm;
    var t = regexpConstant.exec(s);

    var _Array$from = Array.from(t || []),
        _Array$from2 = _slicedToArray(_Array$from, 6),
        all = _Array$from2[0],
        sIndex = _Array$from2[1],
        type = _Array$from2[2],
        v0 = _Array$from2[3],
        rest = _Array$from2[4],
        v1 = _Array$from2[5];

    var index = parseInt(sIndex, 10);
    var value = v1 ? v1 : v0;
    return { index: index, type: type, value: value };
};
exports.constantPoolParser = {
    parse: function parse(input) {
        return input.split("\n").map(function (s) {
            return s.trim();
        }).filter(function (s) {
            return s !== "";
        }).map(function (s) {
            return createConstant(s);
        });
    }
};
},{}],60:[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", { value: true });
var Type;
(function (Type) {
    Type[Type["Constant"] = 0] = "Constant";
    // Numeric
    Type[Type["Int"] = 1] = "Int";
    Type[Type["Long"] = 2] = "Long";
    Type[Type["Float"] = 3] = "Float";
    Type[Type["Double"] = 4] = "Double";
    Type[Type["Char"] = 5] = "Char";
    Type[Type["Short"] = 6] = "Short";
})(Type = exports.Type || (exports.Type = {}));
function typePrefix(type) {
    switch (type) {
        case Type.Int:
            return "i";
        case Type.Long:
            return "l";
        case Type.Float:
            return "f";
        case Type.Double:
            return "d";
        case Type.Char:
            return "c";
        case Type.Short:
            return "s";
        default:
            throw new Error("Unexpected type " + type);
    }
}
exports.typePrefix = typePrefix;
},{}],45:[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", { value: true });
var input_1 = require("../input");
// return, ireturn
exports.flowInstructions = {};
var comparators = {
    ge: function ge(a, b) {
        return a >= b;
    },
    gt: function gt(a, b) {
        return a > b;
    },
    le: function le(a, b) {
        return a <= b;
    },
    lt: function lt(a, b) {
        return a < b;
    },
    ne: function ne(a, b) {
        return a !== b;
    }
};
var intIfCmp = function intIfCmp(jump, cmp) {
    return {
        type: input_1.Type.Int,
        code: "if_icmp" + cmp,
        apply: function apply(frame) {
            var b = frame.stack.pop().value;
            var a = frame.stack.pop().value;
            var needJump = comparators[cmp](a, b);
            frame.next(needJump ? jump : undefined);
        }
    };
};
var gotoLabel = function gotoLabel(jump) {
    return {
        code: "goto",
        apply: function apply(frame) {
            frame.next(jump);
        }
    };
};
exports.flowInstructions.if_icmpgt = function (args) {
    var jump = parseInt(args[0], 10);
    return intIfCmp(jump, "gt");
};
exports.flowInstructions.goto = function (args) {
    var jump = parseInt(args[0], 10);
    return gotoLabel(jump);
};
exports.flowInstructions.ireturn = function () {
    return {
        code: "ireturn",
        apply: function apply(frame) {
            var value = frame.stack.pop();
            frame.return(value);
        }
    };
};
exports.flowInstructions.return = function () {
    return {
        code: "return",
        apply: function apply(frame) {
            return frame.return();
        }
    };
};
},{"../input":60}],46:[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", { value: true });
var input_1 = require("../input");
// imul, iinc
exports.mathInstructions = {};
var typedBinaryOperation = function typedBinaryOperation(type, key, ope) {
    return {
        type: type,
        code: input_1.typePrefix(type) + key,
        apply: function apply(frame) {
            var a = frame.stack.pop();
            var b = frame.stack.pop();
            frame.stack.push({ type: type, value: ope(a.value, b.value) });
            frame.next();
        }
    };
};
var typedUnaryOperation = function typedUnaryOperation(type, key, ope) {
    return {
        type: type,
        code: input_1.typePrefix(type) + key,
        apply: function apply(frame) {
            var a = frame.stack.pop();
            var b = frame.stack.pop();
            frame.stack.push({ type: type, value: ope(a.value) });
            frame.next();
        }
    };
};
var incr = function incr(index, increment) {
    return {
        type: input_1.Type.Int,
        code: "iinc",
        apply: function apply(frame) {
            var _frame$locals$get = frame.locals.get(index),
                type = _frame$locals$get.type,
                value = _frame$locals$get.value;

            frame.locals.set(index, { type: type, value: value + increment });
            frame.next();
        }
    };
};
// Arithmetic
[input_1.Type.Int, input_1.Type.Long, input_1.Type.Float, input_1.Type.Double].forEach(function (type) {
    var t = input_1.typePrefix(type);
    exports.mathInstructions[t + "add"] = function () {
        return typedBinaryOperation(type, "add", function (a, b) {
            return a + b;
        });
    };
    exports.mathInstructions[t + "sub"] = function () {
        return typedBinaryOperation(type, "sub", function (a, b) {
            return a - b;
        });
    };
    exports.mathInstructions[t + "mul"] = function () {
        return typedBinaryOperation(type, "mul", function (a, b) {
            return a * b;
        });
    };
    exports.mathInstructions[t + "div"] = function () {
        return typedBinaryOperation(type, "rem", function (a, b) {
            return a % b;
        });
    };
    exports.mathInstructions[t + "neg"] = function () {
        return typedUnaryOperation(type, "neg", function (a) {
            return -a;
        });
    };
    exports.mathInstructions.iinc = function (args) {
        var index = parseInt(args[0], 10);
        var increment = parseInt(args[1], 10);
        return incr(index, increment);
    };
});
},{"../input":60}],47:[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", { value: true });
var input_1 = require("../input");
// invokestatic, invokevirtual, invokespecial, invokedynamic
exports.objectsInstructions = {};
exports.objectsInstructions.getstatic = function (args) {
    return {
        code: "getstatic",
        apply: function apply(frame) {
            var value = args[0];
            frame.stack.push({ type: input_1.Type.Constant, value: value });
        }
    };
};
exports.objectsInstructions.invokestatic = function (args) {
    return {
        code: "invokestatic",
        apply: function apply(frame) {
            var value = args[0];
            frame.callStaticMethod(value);
        }
    };
};
exports.objectsInstructions.invokevirtual = function (args) {
    return {
        code: "invokevirtual",
        apply: function apply(frame) {
            var value = args[0];
            frame.callObjectMethod(value);
        }
    };
};
},{"../input":60}],48:[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", { value: true });
//
exports.otherInstructions = {};
},{}],49:[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", { value: true });
var input_1 = require("../input");
exports.stackInstructions = {};
var typedConst = function typedConst(type, value) {
    return {
        type: type,
        code: input_1.typePrefix(type) + "const_" + value,
        apply: function apply(frame) {
            frame.stack.push({ type: type, value: value });
            frame.next();
        }
    };
};
var typedStore = function typedStore(type, index) {
    return {
        type: type,
        code: input_1.typePrefix(type) + "store_" + index,
        apply: function apply(frame) {
            var value = frame.stack.pop();
            frame.locals.set(index, value);
            frame.next();
        }
    };
};
var typedLoad = function typedLoad(type, index) {
    return {
        type: type,
        code: input_1.typePrefix(type) + "load_" + index,
        apply: function apply(frame) {
            var value = frame.locals.get(index);
            frame.stack.push({ type: type, value: value });
            frame.next();
        }
    };
};
// Typed const, store, load
[input_1.Type.Int, input_1.Type.Long, input_1.Type.Float, input_1.Type.Double].forEach(function (type) {
    var t = input_1.typePrefix(type);

    var _loop = function _loop(i) {
        exports.stackInstructions[t + "const_" + i] = function () {
            return typedConst(type, i);
        };
    };

    for (var i = 0; i < 6; i++) {
        _loop(i);
    }

    var _loop2 = function _loop2(i) {
        exports.stackInstructions[t + "store_" + i] = function () {
            return typedStore(type, i);
        };
        exports.stackInstructions[t + "load_" + i] = function () {
            return typedLoad(type, i);
        };
    };

    for (var i = 0; i < 4; i++) {
        _loop2(i);
    }
});
exports.stackInstructions.ldc = function (args) {
    return {
        code: "ldc",
        apply: function apply(frame) {
            var value = args[0];
            frame.stack.push({ type: input_1.Type.Constant, value: value });
            frame.next();
        }
    };
};
},{"../input":60}],42:[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", { value: true });
var flow_1 = require("./flow");
var math_1 = require("./math");
var objects_1 = require("./objects");
var others_1 = require("./others");
var stacks_1 = require("./stacks");
var allInstructions = Object.assign({}, stacks_1.stackInstructions, objects_1.objectsInstructions, math_1.mathInstructions, flow_1.flowInstructions, others_1.otherInstructions);
console.log("Got " + Object.keys(allInstructions).length + " instructions");
exports.lookupInstruction = function (opscode, args) {
    var i = allInstructions[opscode];
    if (i) {
        return i(args);
    }
    throw new Error("Opscode " + opscode + " not found !");
};
},{"./flow":45,"./math":46,"./objects":47,"./others":48,"./stacks":49}],26:[function(require,module,exports) {
"use strict";

var _slicedToArray = function () { function sliceIterator(arr, i) { var _arr = []; var _n = true; var _d = false; var _e = undefined; try { for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) { _arr.push(_s.value); if (i && _arr.length === i) break; } } catch (err) { _d = true; _e = err; } finally { try { if (!_n && _i["return"]) _i["return"](); } finally { if (_d) throw _e; } } return _arr; } return function (arr, i) { if (Array.isArray(arr)) { return arr; } else if (Symbol.iterator in Object(arr)) { return sliceIterator(arr, i); } else { throw new TypeError("Invalid attempt to destructure non-iterable instance"); } }; }();

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

Object.defineProperty(exports, "__esModule", { value: true });
var instructions_1 = require("../../models/instructions");
var utils_1 = require("../../models/utils");

var MethodBodyImpl = function () {
    function MethodBodyImpl(stack, locals, args_size, code) {
        _classCallCheck(this, MethodBodyImpl);

        this.stack = stack;
        this.locals = locals;
        this.args_size = args_size;
        this.code = code;
    }

    _createClass(MethodBodyImpl, [{
        key: "eval",
        value: function _eval() {
            return utils_1.TODO(); // FIXME
        }
    }]);

    return MethodBodyImpl;
}();

var createInstruction = function createInstruction(line) {
    var regexpInstruction1 = /^\s*(\d+): ([a-z0-9_]*)\s+(\S*)\s+\/\/\s(.*)$/gm;
    var t1 = regexpInstruction1.exec(line);
    if (t1 !== null) {
        var _Array$from = Array.from(t1 || []),
            _Array$from2 = _slicedToArray(_Array$from, 5),
            all = _Array$from2[0],
            sIndex = _Array$from2[1],
            opscode = _Array$from2[2],
            v0 = _Array$from2[3],
            v1 = _Array$from2[4];

        var position = parseInt(sIndex, 10);
        var args = v0 && v1 ? [v1] : v0 ? [v0] : [];
        var instruction = instructions_1.lookupInstruction(opscode, args);
        return { position: position, instruction: instruction, args: args };
    }
    var regexpInstruction2 = /^\s*(\d+): ([a-z0-9_]*)(\s+([\d, ]*))?$/gm;
    var t2 = regexpInstruction2.exec(line);
    if (t2 !== null) {
        var _Array$from3 = Array.from(t2 || []),
            _Array$from4 = _slicedToArray(_Array$from3, 5),
            _all = _Array$from4[0],
            _sIndex = _Array$from4[1],
            _opscode = _Array$from4[2],
            _v = _Array$from4[3],
            _v2 = _Array$from4[4];

        var _position = parseInt(_sIndex, 10);
        var _args = _v2 ? _v2.split(", ") : [];
        var _instruction = instructions_1.lookupInstruction(_opscode, _args);
        return { position: _position, instruction: _instruction, args: _args };
    }
    throw new Error("Cannot parse line " + line);
};
exports.methodParser = {
    parse: function parse(constantPool, stack, locals, args_size, input) {
        var code = input.split("\n").map(function (s) {
            return s.trim();
        }).filter(function (s) {
            return s !== "";
        }).map(function (s) {
            return createInstruction(s);
        });
        return new MethodBodyImpl(stack, locals, args_size, code);
    }
};
},{"../../models/instructions":42,"../../models/utils":39}],11:[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", { value: true });
var constant_parser_1 = require("./impl/constant-parser");
var instruction_parser_1 = require("./impl/instruction-parser");
var consts = "#1 = Methodref          #6.#18         // java/lang/Object.\"<init>\":()V\n   #2 = Fieldref           #19.#20        // java/lang/System.out:Ljava/io/PrintStream;\n   #3 = Methodref          #5.#21         // _01_factorial/Factorial.factorial:(I)I\n   #4 = Methodref          #22.#23        // java/io/PrintStream.println:(I)V\n   #5 = Class              #24            // _01_factorial/Factorial\n   #6 = Class              #25            // java/lang/Object\n   #7 = Utf8               <init>\n   #8 = Utf8               ()V\n   #9 = Utf8               Code\n  #10 = Utf8               LineNumberTable\n  #11 = Utf8               factorial\n  #12 = Utf8               (I)I\n  #13 = Utf8               StackMapTable\n  #14 = Utf8               main\n  #15 = Utf8               ([Ljava/lang/String;)V\n  #16 = Utf8               SourceFile\n  #17 = Utf8               Factorial.java\n  #18 = NameAndType        #7:#8          // \"<init>\":()V\n  #19 = Class              #26            // java/lang/System\n  #20 = NameAndType        #27:#28        // out:Ljava/io/PrintStream;\n  #21 = NameAndType        #11:#12        // factorial:(I)I\n  #22 = Class              #29            // java/io/PrintStream\n  #23 = NameAndType        #30:#31        // println:(I)V\n  #24 = Utf8               _01_factorial/Factorial\n  #25 = Utf8               java/lang/Object\n  #26 = Utf8               java/lang/System\n  #27 = Utf8               out\n  #28 = Utf8               Ljava/io/PrintStream;\n  #29 = Utf8               java/io/PrintStream\n  #30 = Utf8               println\n  #31 = Utf8               (I)V";
var factorialCode = "0: iconst_1\n1: istore_1\n2: iconst_2\n3: istore_2\n4: iload_2\n5: iload_0\n6: if_icmpgt     19\n9: iload_1\n10: iload_2\n11: imul\n12: istore_1\n13: iinc          2, 1\n16: goto          4\n19: iload_1\n20: ireturn";
var mainCode = " 0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;\n3: iconst_5\n4: invokestatic  #3                  // Method plop:(I)I\n7: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V\n10: return";
exports.constantPool = constant_parser_1.constantPoolParser.parse(consts);
exports.sample = new Map().set("plop:(I)I", instruction_parser_1.methodParser.parse(exports.constantPool, 2, 3, 1, factorialCode)).set("main", instruction_parser_1.methodParser.parse(exports.constantPool, 2, 1, 1, mainCode));
},{"./impl/constant-parser":25,"./impl/instruction-parser":26}],68:[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", { value: true });
var renderStackValue = function renderStackValue(value) {
    return "\n<div class=\"type-" + value.value + "\">" + value.value + "</div>\n";
};
exports.renderStack = function (stack) {
    return "\n<div class=\"stack\">\n    <header>Stack</header>\n    <div>\n        " + stack.list.map(renderStackValue).join('\n') + "\n    </div>\n</div>\n";
};
},{}],69:[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", { value: true });
var renderLocalValue = function renderLocalValue(value) {
    return "\n<div class=\"type-" + value.value + "\">" + value.value + "</div>\n";
};
exports.renderLocals = function (locals) {
    return "\n<div class=\"locals\">\n    <header>Locals</header>\n    <div>\n        " + locals.list.map(renderLocalValue).join('\n') + "\n    </div>\n</div>\n";
};
},{}],24:[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", { value: true });
exports.renderConstantPool = function (constantPool) {
  return "\n<details>\n    <summary>Constant Pool</summary>\n    <div class=\"constants\">\n      " + constantPool.map(function (constant) {
    return "\n        <div class=\"index\">" + constant.index + "</div>\n        <div class=\"type\">" + constant.type + "</div>\n        <div class=\"value\">" + constant.value + "</div>\n        ";
  }).join("\n") + "\n    </div>\n</details>\n";
};
// FIXME Frame, stack, locals, ... current step
exports.renderMethodCode = function (code, position) {
  return "\n<ul class=\"code\">\n  " + code.map(function (inst) {
    return "\n  <li class=\"" + (inst.instruction.type ? "inst-" + inst.instruction.type : '') + " " + (position === inst.position ? "selected" : "") + "\">\n    <div class=\"position\">" + inst.position + "</div>\n    <div class=\"code\">" + inst.instruction.code + "</div>\n    <div class=\"args\">" + inst.args + "</div>\n\n  </li>\n    ";
  }).join("\n") + "\n</ul>\n";
};
},{}],12:[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", { value: true });
var stack_1 = require("./stack");
var local_var_1 = require("./local-var");
var inputs_1 = require("./inputs");
var renderFrame = function renderFrame(frame) {
  return "\n<article class=\"frame\">\n  <header>" + frame.name + " <span>" + frame.args + "</span></header>\n  " + inputs_1.renderMethodCode(frame.methodBody.code, frame.position) + "\n  " + stack_1.renderStack(frame.stack) + "\n  " + local_var_1.renderLocals(frame.locals) + "\n</article>\n";
};
exports.renderFrames = function (frames) {
  return "\n" + inputs_1.renderConstantPool(frames.constantPool) + "\n<details open class=\"frames\">\n    <summary>Frames</summary>\n    <menu>\n      <button type=\"button\" class=\"next\">\u21A9\uFE0F Next</button>  \n    </menu>  \n    <div>\n      " + frames.frames.map(function (frame) {
    return renderFrame(frame);
  }).join('\n') + "\n    </div>\n</details>\n";
};
},{"./stack":68,"./local-var":69,"./inputs":24}],3:[function(require,module,exports) {
"use strict";

Object.defineProperty(exports, "__esModule", { value: true });
var Frames_1 = require("./models/Frames");
var sample_1 = require("./parser/sample");
var frame_1 = require("./templates/frame");
var frames = new Frames_1.Frames(sample_1.constantPool, sample_1.sample);
console.log({ frames: frames });
window.catnip = function (elt) {
    if (elt !== null) {
        elt.innerHTML = frame_1.renderFrames(frames);
        var btn = document.querySelector('.frames menu .next');
        btn.onclick = function () {
            frames.next();
            elt.innerHTML = frame_1.renderFrames(frames);
        };
    } else {
        console.warn("Cannot mount element:", elt);
    }
};
},{"./models/Frames":10,"./parser/sample":11,"./templates/frame":12}],67:[function(require,module,exports) {

var OVERLAY_ID = '__parcel__error__overlay__';

var global = (1, eval)('this');
var OldModule = module.bundle.Module;

function Module(moduleName) {
  OldModule.call(this, moduleName);
  this.hot = {
    data: module.bundle.hotData,
    _acceptCallbacks: [],
    _disposeCallbacks: [],
    accept: function (fn) {
      this._acceptCallbacks.push(fn || function () {});
    },
    dispose: function (fn) {
      this._disposeCallbacks.push(fn);
    }
  };

  module.bundle.hotData = null;
}

module.bundle.Module = Module;

var parent = module.bundle.parent;
if ((!parent || !parent.isParcelRequire) && typeof WebSocket !== 'undefined') {
  var hostname = '' || location.hostname;
  var protocol = location.protocol === 'https:' ? 'wss' : 'ws';
  var ws = new WebSocket(protocol + '://' + hostname + ':' + '50450' + '/');
  ws.onmessage = function (event) {
    var data = JSON.parse(event.data);

    if (data.type === 'update') {
      data.assets.forEach(function (asset) {
        hmrApply(global.parcelRequire, asset);
      });

      data.assets.forEach(function (asset) {
        if (!asset.isNew) {
          hmrAccept(global.parcelRequire, asset.id);
        }
      });
    }

    if (data.type === 'reload') {
      ws.close();
      ws.onclose = function () {
        location.reload();
      };
    }

    if (data.type === 'error-resolved') {
      console.log('[parcel] ✨ Error resolved');

      removeErrorOverlay();
    }

    if (data.type === 'error') {
      console.error('[parcel] 🚨  ' + data.error.message + '\n' + data.error.stack);

      removeErrorOverlay();

      var overlay = createErrorOverlay(data);
      document.body.appendChild(overlay);
    }
  };
}

function removeErrorOverlay() {
  var overlay = document.getElementById(OVERLAY_ID);
  if (overlay) {
    overlay.remove();
  }
}

function createErrorOverlay(data) {
  var overlay = document.createElement('div');
  overlay.id = OVERLAY_ID;

  // html encode message and stack trace
  var message = document.createElement('div');
  var stackTrace = document.createElement('pre');
  message.innerText = data.error.message;
  stackTrace.innerText = data.error.stack;

  overlay.innerHTML = '<div style="background: black; font-size: 16px; color: white; position: fixed; height: 100%; width: 100%; top: 0px; left: 0px; padding: 30px; opacity: 0.85; font-family: Menlo, Consolas, monospace; z-index: 9999;">' + '<span style="background: red; padding: 2px 4px; border-radius: 2px;">ERROR</span>' + '<span style="top: 2px; margin-left: 5px; position: relative;">🚨</span>' + '<div style="font-size: 18px; font-weight: bold; margin-top: 20px;">' + message.innerHTML + '</div>' + '<pre>' + stackTrace.innerHTML + '</pre>' + '</div>';

  return overlay;
}

function getParents(bundle, id) {
  var modules = bundle.modules;
  if (!modules) {
    return [];
  }

  var parents = [];
  var k, d, dep;

  for (k in modules) {
    for (d in modules[k][1]) {
      dep = modules[k][1][d];
      if (dep === id || Array.isArray(dep) && dep[dep.length - 1] === id) {
        parents.push(+k);
      }
    }
  }

  if (bundle.parent) {
    parents = parents.concat(getParents(bundle.parent, id));
  }

  return parents;
}

function hmrApply(bundle, asset) {
  var modules = bundle.modules;
  if (!modules) {
    return;
  }

  if (modules[asset.id] || !bundle.parent) {
    var fn = new Function('require', 'module', 'exports', asset.generated.js);
    asset.isNew = !modules[asset.id];
    modules[asset.id] = [fn, asset.deps];
  } else if (bundle.parent) {
    hmrApply(bundle.parent, asset);
  }
}

function hmrAccept(bundle, id) {
  var modules = bundle.modules;
  if (!modules) {
    return;
  }

  if (!modules[id] && bundle.parent) {
    return hmrAccept(bundle.parent, id);
  }

  var cached = bundle.cache[id];
  bundle.hotData = {};
  if (cached) {
    cached.hot.data = bundle.hotData;
  }

  if (cached && cached.hot && cached.hot._disposeCallbacks.length) {
    cached.hot._disposeCallbacks.forEach(function (cb) {
      cb(bundle.hotData);
    });
  }

  delete bundle.cache[id];
  bundle(id);

  cached = bundle.cache[id];
  if (cached && cached.hot && cached.hot._acceptCallbacks.length) {
    cached.hot._acceptCallbacks.forEach(function (cb) {
      cb();
    });
    return true;
  }

  return getParents(global.parcelRequire, id).some(function (id) {
    return hmrAccept(global.parcelRequire, id);
  });
}
},{}]},{},[67,3])
//# sourceMappingURL=/scripts.b5c76179.map