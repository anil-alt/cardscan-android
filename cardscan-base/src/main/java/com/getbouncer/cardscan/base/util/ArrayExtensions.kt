@file:JvmName("ArrayExtensions")

package com.getbouncer.cardscan.base.util

import kotlin.math.max
import kotlin.math.min

/**
 * Update an array in place with a modifier function.
 */
fun <T> Array<T>.updateEach(operation: (original: T) -> T) {
    for (i in this.indices) {
        this[i] = operation(this[i])
    }
}

/**
 * Update a [FloatArray] in place with a modifier function.
 */
fun FloatArray.updateEach(operation: (original: Float) -> Float) {
    for (i in this.indices) {
        this[i] = operation(this[i])
    }
}

/**
 * Filter an array to only those values specified in an index array.
 */
inline fun <reified T> Array<T>.filterByIndexes(indexesToKeep: IntArray) =
    Array(indexesToKeep.size) { this[indexesToKeep[it]] }

/**
 * Filter an array to only those values specified in an index array.
 */
fun FloatArray.filterByIndexes(indexesToKeep: IntArray) =
    FloatArray(indexesToKeep.size) { this[indexesToKeep[it]] }

/**
 * Flatten an array of arrays into a single array of sequential values.
 */
fun Array<FloatArray>.flatten() = if (this.isNotEmpty()) {
    this.reshape(this.size * this[0].size)[0]
} else {
    floatArrayOf()
}

/**
 * Transpose an array of float arrays.
 */
fun Array<FloatArray>.transpose() = if (this.isNotEmpty()) {
    val oldRows = this.size
    val oldColumns = this[0].size
    Array(oldColumns) { newRow -> FloatArray(oldRows) { newColumn -> this[newColumn][newRow] } }
} else {
    this
}

/**
 * Reshape a two-dimensional array. Assume all rows of the original array are the same length, and
 * that the array is evenly divisible by the new columns.
 */
fun Array<FloatArray>.reshape(newColumns: Int): Array<FloatArray> {
    val oldRows = this.size
    val oldColumns = if (this.isNotEmpty()) this[0].size else 0
    val linearSize = oldRows * oldColumns
    val newRows = linearSize / newColumns + if (linearSize % newColumns != 0) 1 else 0

    var oldRow = 0
    var oldColumn = 0
    return Array(newRows) { FloatArray(newColumns) {
        val value = this[oldRow][oldColumn]
        if (++oldColumn == oldColumns) {
            oldColumn = 0
            oldRow++
        }
        value
    } }
}

/**
 * Clamp the value between min and max
 */
fun clamp(value: Float, minimum: Float, maximum: Float): Float =
    max(minimum, min(maximum, value))

/**
 * Return a list of indexes that pass the filter.
 */
fun FloatArray.filteredIndexes(predicate: (Float) -> Boolean): IntArray {
    val filteredIndexes = ArrayList<Int>()
    for (index in this.indices) {
        if (predicate(this[index])) {
            filteredIndexes.add(index)
        }
    }
    return filteredIndexes.toIntArray()
}
