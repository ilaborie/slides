const doubleSay = (str) => str + ', ' + str;
const capitalize = (str) => str[0].toUpperCase() + str.substring(1);
const exclaim = (str) => str + '!';

const result1 = exclaim(capitalize(doubleSay('hello')));

const result2 = 'hello'
    |> doubleSay
    |> capitalize
    |> exclaim;