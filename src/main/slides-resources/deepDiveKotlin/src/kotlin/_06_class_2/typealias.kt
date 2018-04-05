package _06_class_2

interface Entity

//fun getAllEntities(): Map<Pair<String, Int>, List<Entity>> = emptyMap()

typealias Id = String
typealias Version = Int
typealias EntityKey = Pair<Id, Version>

fun getAllEntities(): Map<EntityKey, List<Entity>> = emptyMap()
