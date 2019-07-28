package com.martin.loginsample.dagger.modules

import com.martin.loginsample.networking.RetrofitClient
import com.martin.loginsample.session.SessionManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitClient(sessionManager: SessionManager) = RetrofitClient(sessionManager)
}