const getLength = vector => {
    match (vector) {
        when { x, y, z } ~> return Math.sqrt(x ** 2 + y ** 2 + z ** 2)
        when { x, y } ~> return Math.sqrt(x ** 2 + y ** 2)
        when [...etc] ~> return vector.length
    }
}
getLength({x: 1, y: 2, z: 3}) // 3.74165