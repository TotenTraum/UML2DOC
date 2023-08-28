package graph

interface Iterator<T> {
    fun hasNext(): Boolean

    fun next(): T
}