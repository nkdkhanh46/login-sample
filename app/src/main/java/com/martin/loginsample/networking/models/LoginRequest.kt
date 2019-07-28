package com.martin.loginsample.networking.models

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("type")
    val type: String = "normal")