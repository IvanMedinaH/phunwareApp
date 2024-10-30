package com.kotlin.phunwareapp

import android.app.Application
import com.kotlin.phunwareapp.core.di.contextModule
import com.kotlin.phunwareapp.core.di.networkModule
import com.kotlin.phunwareapp.core.di.starwarsevents.detail.starwarsDetailRepositoryModule
import com.kotlin.phunwareapp.core.di.starwarsevents.master.starwarsMasterRepositoryModule
import com.kotlin.phunwareapp.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class StarWarsApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger() // Log Koin information
            androidContext(this@StarWarsApp) // Provide application context
            modules(
                contextModule,
                networkModule,
                viewModelModule,
                starwarsMasterRepositoryModule,
                starwarsDetailRepositoryModule
            )
        }
    }
}