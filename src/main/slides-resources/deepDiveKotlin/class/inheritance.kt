open class Animal { // need open
    open fun talk(): String =
        "???"
}

data class Cat(val name: String) : Animal() {
    override fun talk(): String = // need override
        "Meow"
}

data class Dog(val name: String) : Animal() {
    override fun talk(): String =  // need override
        "Woof"
}

fun main(args: Array<String>) {
    val pets: List<Animal> = listOf(Cat("Felix"), Dog("Rex"))

    // 😱
    pets.forEach { pet ->
        println("$pet: ${pet.talk()}")
    }
    // Cat(name=Felix): Meow
    // Dog(name=Rex): Woof
}