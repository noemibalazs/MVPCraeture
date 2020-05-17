package com.example.mvpcraeture.application

import android.app.Application
import com.example.mvpcraeture.di.component.AppComponent
import com.example.mvpcraeture.di.component.DaggerAppComponent
import com.example.mvpcraeture.di.module.AppModule

class MyApp : Application() {

    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = getAppComponent(this)
    }

    private fun getAppComponent(application: Application): AppComponent {
        return DaggerAppComponent.builder().appModule(
            AppModule(application)
        ).build()
    }
}