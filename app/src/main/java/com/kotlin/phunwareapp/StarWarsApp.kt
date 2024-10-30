package com.kotlin.phunwareapp

import android.app.Application
import com.kotlin.phunwareapp.core.di.networkModule
import com.kotlin.phunwareapp.core.di.repositoryModule
import com.kotlin.phunwareapp.core.di.viewModelModule
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
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}