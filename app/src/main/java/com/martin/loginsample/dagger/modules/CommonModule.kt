package com.martin.loginsample.dagger.modules

import com.martin.loginsample.session.SessionManager
import com.martin.loginsample.session.impl.SessionManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CommonModule {

    @Provides
    @Singleton
    fun provideSessionManager(impl: SessionManagerImpl): SessionManager = impl
}