package com.martin.loginsample.networking

import com.martin.loginsample.networking.models.LoginRequest
import com.martin.loginsample.networking.models.LoginResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.POST

/**
 * Created by martinmistery on 07/28/19.
 */
interface APIRequest {
    @POST("login")
    fun login(@Body body: LoginRequest): Single<Response<LoginResponse>>
}