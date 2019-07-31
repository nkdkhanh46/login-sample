package com.martin.loginsample.dagger

import com.martin.loginsample.base.BaseActivity
import com.martin.loginsample.dagger.modules.*
import com.martin.loginsample.features.login.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class, ViewModelModule::class, StorageModule::class, CommonModule::class])
@Singleton
interface AppComponent {
    fun inject(target: BaseActivity)
    fun inject(target: LoginActivity)
}