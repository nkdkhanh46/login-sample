package com.martin.loginsample.networking.models

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("auth_token")
    var token: String? = null
}