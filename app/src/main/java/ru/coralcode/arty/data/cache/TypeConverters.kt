package ru.coralcode.arty.data.cache

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import ru.coralcode.arty.data.JsonParserWrapper

interface TypeConverter<T, D> {

    fun convert(value: T): D

    fun convertBack(value: D): T

}

class RoomListTypeConverter: TypeConverter<List<String>, String>, JsonParserWrapper() {

    @androidx.room.TypeConverter
    override fun convert(value: List<String>): String {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val jsonAdapter: JsonAdapter<List<String>> = jsonParser.adapter(type)
        return jsonAdapter.toJson(value)
    }

    @androidx.room.TypeConverter
    override fun convertBack(value: String): List<String> {
        return kotlin.runCatching {
            val type = Types.newParameterizedType(List::class.java, String::class.java)
            val jsonAdapter: JsonAdapter<List<String>> = jsonParser.adapter(type)
            jsonAdapter.fromJson(value)
        }.getOrNull() ?: emptyList()
    }
}

