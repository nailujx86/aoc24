var __asyncValues = (this && this.__asyncValues) || function (o) {
    if (!Symbol.asyncIterator) throw new TypeError("Symbol.asyncIterator is not defined.");
    var m = o[Symbol.asyncIterator], i;
    return m ? m.call(o) : (o = typeof __values === "function" ? __values(o) : o[Symbol.iterator](), i = {}, verb("next"), verb("throw"), verb("return"), i[Symbol.asyncIterator] = function () { return this; }, i);
    function verb(n) { i[n] = o[n] && function (v) { return new Promise(function (resolve, reject) { v = o[n](v), settle(resolve, reject, v.done, v.value); }); }; }
    function settle(resolve, reject, d, v) { Promise.resolve(v).then(function(v) { resolve({ value: v, done: d }); }, reject); }
};
var _a, e_1, _b, _c;
import * as fs from "node:fs/promises";
const file = await fs.open("./input.txt");
const regex = /(\d|one|two|three|four|five|six|seven|eight|nine)/g;
const regex2 = /(\d|eno|owt|eerht|ruof|evif|xis|neves|thgie|enin)/g;
const numberToNumber = {
    "one": "1",
    "two": "2",
    "three": "3",
    "four": "4",
    "five": "5",
    "six": "6",
    "seven": "7",
    "eight": "8",
    "nine": "9"
};
let sum = 0;
try {
    for (var _d = true, _e = __asyncValues(file.readLines()), _f; _f = await _e.next(), _a = _f.done, !_a; _d = true) {
        _c = _f.value;
        _d = false;
        const line = _c;
        let matches = [line.match(regex)[0], line.split('').reverse().join('').match(regex2)[0]];
        console.log(line);
        let numStr = "";
        console.log([matches[0], matches[1]]);
        let i = 0;
        for (let match of [matches[0], matches[1]]) {
            if (isNaN(Number(match))) {
                if (i == 0) {
                    numStr += numberToNumber[match];
                }
                else {
                    numStr += numberToNumber[match === null || match === void 0 ? void 0 : match.split('').reverse().join('')];
                }
            }
            else {
                numStr += match;
            }
            i++;
        }
        sum += Number(numStr);
        console.log(sum);
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
