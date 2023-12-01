import * as fs from "node:fs/promises";

const file = await fs.open("./input.txt");
const regex = /(\d|one|two|three|four|five|six|seven|eight|nine)/g
const regex2 = /(\d|eno|owt|eerht|ruof|evif|xis|neves|thgie|enin)/g
const numberToNumber: {[index: string]: string} = {
    "one": "1",
    "two": "2",
    "three": "3",
    "four": "4",
    "five": "5",
    "six": "6",
    "seven": "7",
    "eight": "8",
    "nine": "9"
}

let sum = 0;

for await (const line of file.readLines()) {
    let matches = [line.match(regex)![0], line.split('').reverse().join('').match(regex2)![0]]
    let numStr = "";
    let i = 0;
    for (let match of [matches[0] , matches[1]]) {
        if (isNaN(Number(match))) {
            if (i == 0) {
                numStr += numberToNumber[match];
            } else{
                numStr += numberToNumber[match?.split('').reverse().join('')]
            }
        } else {
            numStr += match;
        }
        i++;
    }
    sum += Number(numStr);
}

console.log(sum);