package com.kotlin.phunwareapp.core.di

import com.kotlin.phunwareapp.presentation.starwars.master.StarWarsMasterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { StarWarsMasterViewModel(get(), get()) }
}