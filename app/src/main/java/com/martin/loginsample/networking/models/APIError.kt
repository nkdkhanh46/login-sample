package com.martin.loginsample.networking.models

import com.google.gson.annotations.SerializedName

class APIError(
    @SerializedName("error")
    var error: String?)