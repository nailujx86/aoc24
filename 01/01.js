var __asyncValues = (this && this.__asyncValues) || function (o) {
    if (!Symbol.asyncIterator) throw new TypeError("Symbol.asyncIterator is not defined.");
    var m = o[Symbol.asyncIterator], i;
    return m ? m.call(o) : (o = typeof __values === "function" ? __values(o) : o[Symbol.iterator](), i = {}, verb("next"), verb("throw"), verb("return"), i[Symbol.asyncIterator] = function () { return this; }, i);
    function verb(n) { i[n] = o[n] && function (v) { return new Promise(function (resolve, reject) { v = o[n](v), settle(resolve, reject, v.done, v.value); }); }; }
    function settle(resolve, reject, d, v) { Promise.resolve(v).then(function(v) { resolve({ value: v, done: d }); }, reject); }
};
var _a, e_1, _b, _c;
import * as fs from "node:fs/promises";
const regex = /^\D*(\d)?.*(\d)\D*$/;
let file = await fs.open("./input.txt");
let sum = 0;
let cnt = 0;
try {
    for (var _d = true, _e = __asyncValues(file.readLines()), _f; _f = await _e.next(), _a = _f.done, !_a; _d = true) {
        _c = _f.value;
        _d = false;
        const line = _c;
        console.log(cnt++);
        let regexresult = line.match(regex);
        let num = Number(regexresult === null || regexresult === void 0 ? void 0 : regexresult.slice(1).map(e => e || "").reduce((prev, cur) => (prev || cur) + cur));
        sum += num;
    }
}
catch (e_1_1) { e_1 = { error: e_1_1 }; }
finally {
    try {
        if (!_d && !_a && (_b = _e.return)) await _b.call(_e);
    }
    finally { if (e_1) throw e_1.error; }
}
console.log(sum);
