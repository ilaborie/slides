package org.ilaborie.table

data class TableCell<R, C, V>(val row: R, val column: C, val value: V)

interface Table<R, C, V> : Iterable<TableCell<R, C, V>> {

    fun cells(): Sequence<TableCell<R, C, V>>

    override fun iterator(): Iterator<TableCell<R, C, V>> = cells().iterator()

    fun keys(): Sequence<Pair<R, C>> =
        cells().map { it.row to it.column }

    fun rows(): Set<R> =
        cells().map { it.row }.toSet()

    fun columns(): Set<C> =
        cells().map { it.column }.toSet()

    fun values(): Sequence<V> =
        cells().map { it.value }

    fun isEmpty(): Boolean = cells().count() == 0
    fun size(): Pair<Int, Int> = rows().size to columns().size

    fun contains(row: R, column: C): Boolean =
        keys().any { (r, c) -> r == row && c == column }

    fun containsRow(row: R): Boolean =
        rows().contains(row)

    fun containsColumn(column: C): Boolean =
        columns().contains(column)

    fun containsValue(value: V): Boolean =
        values().any { it == value }

    fun get(row: R, column: C): V? = cells()
        .filter { it.row == row && it.column == column }
        .map { it.value }
        .firstOrNull()

    fun invoke(row: R, column: C): V? =
        get(row, column)

    fun getRow(row: R): Map<C, V>? = cells()
        .filter { it.row == row }
        .map { it.column to it.value }
        .toMap()

    fun getColumn(column: C): Map<R, V>? = cells()
        .filter { it.column == column }
        .map { it.row to it.value }
        .toMap()

    fun add(row: R, column: C, value: V): Table<R, C, V>

    fun addRow(row: R, columnValues: Map<C, V>): Table<R, C, V> =
        columnValues.toList()
            .fold(this) { table, (column, value) -> table.add(row, column, value) }

    fun addColumn(column: C, rowValues: Map<R, V>): Table<R, C, V> =
        rowValues.toList()
            .fold(this) { table, (row, value) -> table.add(row, column, value) }

    fun remove(row: R, column: C): Table<R, C, V>

    fun removeRow(row: R): Table<R, C, V> = keys()
        .filter { it.first == row }
        .fold(this) { table, (_, column) -> table.remove(row, column) }

    fun removeColumn(column: C): Table<R, C, V> = keys()
        .filter { it.second == column }
        .fold(this) { table, (row, _) -> table.remove(row, column) }


    fun transpose(): Table<C, R, V> {
        class TransposedTable<C, R, V>(private val self: Table<R, C, V>) : Table<C, R, V> {
            override fun cells(): Sequence<TableCell<C, R, V>> = self.cells()
                .map { (row, column, value) -> TableCell(column, row, value) }

            override fun add(row: C, column: R, value: V): Table<C, R, V> =
                TransposedTable(self.add(column, row, value))

            override fun remove(row: C, column: R): Table<C, R, V> =
                TransposedTable(self.remove(column, row))
        }
        return TransposedTable(this)
    }

    companion object {
        fun <R, C, V> sparse(): Table<R, C, V> = MapTable(mapOf())
    }
}


