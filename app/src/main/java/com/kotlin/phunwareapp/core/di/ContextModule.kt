package com.kotlin.phunwareapp.core.di

import android.app.Application
import org.koin.dsl.module

val contextModule = module {
    single { get<Application>() }
}