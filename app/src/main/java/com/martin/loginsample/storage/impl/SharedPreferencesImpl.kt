package com.martin.loginsample.storage.impl

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.martin.loginsample.storage.AppSharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesImpl @Inject constructor(private val context: Context): AppSharedPreferences {
    companion object {
        const val KEY_TOKEN = "Token"
    }

    private fun getSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    private fun getEditor(): SharedPreferences.Editor = getSharedPreferences().edit()

    private fun setString(key: String, value: String?) {
        val editor = getEditor()
        editor.putString(key, value)
        editor.apply()
    }

    private fun getString(key: String): String {
        return getSharedPreferences().getString(key, "")?:""
    }

    override fun getToken(): String {
        return getString(KEY_TOKEN)
    }

    override fun setToken(token: String) {
        setString(KEY_TOKEN, token)
    }
}