package com.martin.loginsample.session.impl

import com.martin.loginsample.session.SessionManager
import com.martin.loginsample.storage.AppSharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManagerImpl @Inject constructor(private val sharedPreferences: AppSharedPreferences): SessionManager {

    var authToken = ""

    init {
        authToken = sharedPreferences.getToken()
    }

    override fun getToken(): String {
        return authToken
    }

    override fun updateToken(token: String) {
        authToken = token
        sharedPreferences.setToken(token)
    }
}