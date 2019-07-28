package com.martin.loginsample.storage

interface AppSharedPreferences {
    fun getToken(): String
    fun setToken(token: String)
}