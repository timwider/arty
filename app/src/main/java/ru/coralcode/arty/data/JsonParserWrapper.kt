package ru.coralcode.arty.data

import com.squareup.moshi.Moshi

interface BaseJsonParserWrapper<T> {
    fun initializeParser(): T
}

abstract class JsonParserWrapper: BaseJsonParserWrapper<Moshi> {

    protected val jsonParser: Moshi by lazy { initializeParser() }

    override fun initializeParser(): Moshi = Moshi.Builder().build()
}
