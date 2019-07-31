package com.martin.loginsample.networking

import com.martin.loginsample.session.SessionManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by martinmistery on 07/28/19.
 */

class RetrofitClient(private val sessionManager: SessionManager) {

    companion object {
        private const val BASE_URL = "https://reqres.in/api/"
    }

    fun getService(): APIRequest {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(APIRequest::class.java)
    }

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    var request = chain.request()
                    val requestBuilder = request.newBuilder()
                    requestBuilder.addHeader("Accept", "application/json")
                    val token = sessionManager.getToken()
                    if (token.isNotEmpty()) {
                        requestBuilder.addHeader("Authorization", getBearerToken(token))
                    }
                    request = requestBuilder.build()
                    val response = chain.proceed(request)

                    response
                }
                .build()
    }

    private fun getBearerToken(token: String): String {
        return "Bearer $token"
    }
}