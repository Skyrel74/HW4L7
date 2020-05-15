package com.example.hw4l7

import android.app.Application
import com.example.hw4l7.di.AppComponent
import com.example.hw4l7.di.DaggerAppComponent

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}