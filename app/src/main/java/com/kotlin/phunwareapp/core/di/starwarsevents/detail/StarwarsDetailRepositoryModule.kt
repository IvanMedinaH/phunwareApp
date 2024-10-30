package com.kotlin.phunwareapp.core.di.starwarsevents.detail

import com.kotlin.phunwareapp.data.implrepository.detail.StarWarsEventDetailImpl
import com.kotlin.phunwareapp.domain.repository.detail.IStarwarsDetailRepository
import org.koin.dsl.module

val starwarsDetailRepositoryModule =module {
    single<IStarwarsDetailRepository>{ StarWarsEventDetailImpl(get()) }
}