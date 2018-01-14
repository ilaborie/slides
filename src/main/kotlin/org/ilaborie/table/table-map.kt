package org.ilaborie.table


internal data class MapTable<R, C, V>(private val map: Map<Pair<R, C>, V>) : Table<R, C, V> {
    override fun cells(): Sequence<TableCell<R, C, V>> = map
        .entries.asSequence()
        .map { entry ->
            val (row, column) = entry.key
            TableCell(row, column, entry.value)
        }

    override fun add(row: R, column: C, value: V): Table<R, C, V> {
        val key = row to column
        return MapTable(map + (key to value))
    }

    override fun remove(row: R, column: C): Table<R, C, V> =
        MapTable(map - (row to column))
}
