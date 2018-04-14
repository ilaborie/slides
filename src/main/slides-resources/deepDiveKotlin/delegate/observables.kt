import kotlin.properties.Delegates

fun main(args: Array<String>) {

    var obserbable: String by Delegates.observable("Initial value") {
        prop, old, new ->
            println("$old -> $new")
    }

    obserbable = "new value"
}
