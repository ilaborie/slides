fun doSomething(arg: String): String = TODO()

val asValue: String -> String = ::doSomething

val aLambda: String -> String = { s: String -> TODO() }