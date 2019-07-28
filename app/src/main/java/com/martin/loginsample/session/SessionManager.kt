package com.martin.loginsample.session

interface SessionManager {

    fun getToken(): String

    fun updateToken(token: String)
}