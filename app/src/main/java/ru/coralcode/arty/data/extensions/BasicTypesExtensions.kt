package ru.coralcode.arty.data.extensions

import android.os.Environment
import android.os.SharedMemory
import android.os.StatFs
import android.util.Log
import androidx.core.os.bundleOf
import ru.coralcode.arty.data.cache.RoomListTypeConverter

fun String.fromJsonToList(): List<String> = RoomListTypeConverter().convertBack(this)

fun emptyString() = ""

fun CharSequence.clear() = StringBuilder(this).delete(0, this.lastIndex)

fun String.onEmpty(action: () -> Unit) {
    if (this.isEmpty()) action.invoke()
}


