package com.kotlin.phunwareapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class StarWarsApp:Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            // Log Koin into Android logger information for logcat
            androidLogger()
            // Reference Android context
            androidContext(this@StarWarsApp)
            // Load modules
            modules(

            )
        }
    }
}