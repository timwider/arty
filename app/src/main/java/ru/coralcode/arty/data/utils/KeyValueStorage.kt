package ru.coralcode.arty.data.utils

import android.content.Context
import android.content.SharedPreferences
import android.location.SettingInjectorService
import okhttp3.Cache
import ru.coralcode.arty.data.extensions.onEmpty

const val KEY_VALUE_STORAGE_GLOBAL_KEY = "artyglobalkey"
private const val SETTINGS_APP_THEME_LOCAL_STORAGE_KEY = "apptheme"
private const val SETTINGS_DAILY_ARTWORK_LOCAL_STORAGE_KEY = "dailyartwork"
private const val CACHE_SETTINGS_LOCAL_STORAGE_KEY = "cachesettings"

class KeyValueStorage(context: Context): BaseKeyValueStorage {

    private var sp: SharedPreferences? = null

    init {
        sp = context.getSharedPreferences(KEY_VALUE_STORAGE_GLOBAL_KEY, Context.MODE_PRIVATE)
    }

    override fun getBoolean(key: String): Boolean {
        return sp?.getBoolean(key, false) ?: false
    }

    override fun putBoolean(key: String, value: Boolean): Boolean {
        return sp?.edit()?.putBoolean(key, value)?.commit() ?: false
    }

    override fun putString(key: String, value: String): Boolean {
        return sp?.edit()?.putString(key, value)?.commit() ?: false
    }

    override fun getString(key: String): String {
        return sp?.getString(key, "") ?: ""
    }
}

interface BaseKeyValueStorage  {
    fun getBoolean(key: String): Boolean
    fun putBoolean(key: String, value: Boolean): Boolean
    fun putString(key: String, value: String): Boolean
    fun getString(key: String): String
}


enum class AppTheme: BaseEnum { LIGHT, DARK, SYSTEM }
enum class CacheSettingsValue: BaseEnum { NONE, FAVORITES, ALL }

interface BaseEnum

abstract class SettingsStorage(context: Context) {
    protected var keyValueStorage: KeyValueStorage = KeyValueStorage(context)
    protected abstract var key: String

    protected fun <T> onValueNotSet(defaultValue: T): T {
        if (defaultValue is Boolean) {
            keyValueStorage.putBoolean(key, defaultValue)
        } else {
            keyValueStorage.putString(key, defaultValue.toString())
        }
        return defaultValue
    }

    class ThemeStorage(context: Context): SettingsStorage(context) {

        override var key = SETTINGS_APP_THEME_LOCAL_STORAGE_KEY

        fun getTheme(): AppTheme {
            val themeString = keyValueStorage.getString(key)
            return if (themeString.isEmpty()) {
                onValueNotSet(AppTheme.LIGHT)
            } else AppTheme.valueOf(themeString)
        }

        fun setTheme(value: AppTheme) = keyValueStorage.putString(key, value.toString())
    }

    class NotificationStorage(context: Context): SettingsStorage(context) {

        override var key = SETTINGS_DAILY_ARTWORK_LOCAL_STORAGE_KEY

        fun isDailyArtworkNotificationEnabled(): Boolean =
            keyValueStorage.getBoolean(key)

        fun setDailyArtworkNotificationValue(value: Boolean) = keyValueStorage.putBoolean(key, value)
    }

    class CacheSettingsStorage(context: Context): SettingsStorage(context) {

        override var key = CACHE_SETTINGS_LOCAL_STORAGE_KEY

        fun getCacheSettingsValue(): CacheSettingsValue {
            return keyValueStorage.getString(key).onEmpty {
                onValueNotSet(CacheSettingsValue.NONE)
            }
        }

        fun setCacheSettingsValue(value: CacheSettingsValue) =
            keyValueStorage.putString(key, value.toString())
    }
}

