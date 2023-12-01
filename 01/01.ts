import * as fs from "node:fs/promises";
const regex = /^\D*(\d)?.*(\d)\D*$/
let file = await fs.open("./input.txt");
let sum = 0;
let cnt = 0;
for await (const line of file.readLines()) {
    console.log(cnt++);
    let regexresult = line.match(regex);
    let num = Number(regexresult?.slice(1).map(e => e || "").reduce((prev, cur) => (prev || cur) + cur));
    sum += num;
}
console.log(sum);