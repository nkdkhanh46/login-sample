package com.martin.loginsample.networking.models

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("token")
    var token: String = ""
}