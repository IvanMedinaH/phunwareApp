package com.kotlin.phunwareapp.core.di

import android.app.Application
import org.koin.dsl.module

val contextModule = module {
    single { get<Application>() }  // Provides Application context

    // You can also provide activity context if needed
    single { get<Application>().applicationContext }
}