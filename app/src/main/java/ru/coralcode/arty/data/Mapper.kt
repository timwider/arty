package ru.coralcode.arty.data

interface Mapper<T, D> {

    fun map(from: T): D
}