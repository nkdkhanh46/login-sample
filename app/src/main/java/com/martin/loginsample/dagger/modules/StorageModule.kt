package com.martin.loginsample.dagger.modules

import com.martin.loginsample.storage.AppSharedPreferences
import com.martin.loginsample.storage.impl.SharedPreferencesImpl
import dagger.Module
import dagger.Provides

@Module
class StorageModule {
    @Provides
    fun provideAppSharedPreferences(impl: SharedPreferencesImpl): AppSharedPreferences = impl
}