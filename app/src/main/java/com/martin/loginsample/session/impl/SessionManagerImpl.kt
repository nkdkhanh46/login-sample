package com.martin.loginsample.session.impl

import com.martin.loginsample.session.SessionManager
import com.martin.loginsample.storage.AppSharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManagerImpl @Inject constructor(private val sharedPreferences: AppSharedPreferences): SessionManager {

    override fun getToken(): String {
        return ""
    }

    override fun updateToken(token: String) {

    }
}