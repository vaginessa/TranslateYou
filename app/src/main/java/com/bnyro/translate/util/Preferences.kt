package com.bnyro.translate.util

import android.content.Context
import android.content.SharedPreferences
import com.bnyro.translate.constants.ThemeMode

object Preferences {
    const val instanceUrlKey = "instanceUrl"
    const val apiKey = "apiKey"
    const val defaultInstanceUrl = "https://libretranslate.de"
    const val themeModeKey = "themeMode"
    const val sourceLanguage = "sourceLanguage"
    const val targetLanguage = "targetLanguage"

    private lateinit var prefs: SharedPreferences

    fun initialize(context: Context) {
        prefs = context.getSharedPreferences(
            "preferences",
            Context.MODE_PRIVATE
        )
    }

    fun put(key: String, value: String) {
        prefs.edit().putString(
            key,
            value
        ).apply()
    }

    fun get(key: String, defValue: String): String {
        return prefs.getString(
            key,
            defValue
        ) ?: defValue
    }

    fun getThemeMode(): Int {
        return get(
            themeModeKey,
            ThemeMode.AUTO.toString()
        ).toInt()
    }
}
