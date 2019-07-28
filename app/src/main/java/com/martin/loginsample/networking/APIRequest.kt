package com.martin.loginsample.networking

import com.martin.loginsample.networking.models.LoginRequest
import com.martin.loginsample.networking.models.LoginResponse
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST

/**
 * Created by martinmistery on 07/28/19.
 */
interface APIRequest {
    @POST("auth")
    fun login(@Body body: LoginRequest): Call<LoginResponse>
}