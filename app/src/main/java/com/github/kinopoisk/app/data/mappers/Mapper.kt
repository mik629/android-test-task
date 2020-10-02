package com.github.kinopoisk.app.data.mappers

interface Mapper<T, V> {
    fun map(obj: T): V

    fun reverseMap(obj: V): T
}
