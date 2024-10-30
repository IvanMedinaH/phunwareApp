package com.kotlin.phunwareapp.core.di.starwarsevents.master

import com.kotlin.phunwareapp.data.implrepository.master.StarWarsEventMasterImpl
import com.kotlin.phunwareapp.domain.repository.master.IStarwarsMasterRepository
import org.koin.dsl.module

val starwarsMasterRepositoryModule = module {
    single<IStarwarsMasterRepository>{ StarWarsEventMasterImpl(get()) }
}