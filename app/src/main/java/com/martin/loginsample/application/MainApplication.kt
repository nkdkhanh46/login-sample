package com.martin.loginsample.application

import android.app.Application
import com.martin.loginsample.dagger.AppComponent
import com.martin.loginsample.dagger.DaggerAppComponent
import com.martin.loginsample.dagger.modules.AppModule

class MainApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    @Suppress("DEPRECATION")
    private fun initDagger() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}