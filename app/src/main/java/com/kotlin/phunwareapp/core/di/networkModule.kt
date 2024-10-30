package com.kotlin.phunwareapp.core.di

import com.google.gson.Gson
import com.kotlin.phunwareapp.BuildConfig
import com.kotlin.phunwareapp.core.consts.secret.AccessData
import com.kotlin.phunwareapp.data.local.NetworkVerifier
import com.kotlin.phunwareapp.data.remote.iservices.IServiceAPI
import com.kotlin.phunwareapp.framework.AndroidNetworkVeriifierImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { providesParser() }
    single { providesOkHttpClient() }
    single { providesRetrofit(get(), get()) }
    single<NetworkVerifier> { AndroidNetworkVeriifierImpl(get()) }
    single { providesApi(get()) }
}


fun providesRetrofit(converter: Gson, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(AccessData.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(converter))
        .build()
}

fun providesParser(): Gson = Gson()
fun providesOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Content-Type", "application/json")
            chain.proceed(requestBuilder.build())
        }
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }
        .build()
}

fun providesApi(retrofit: Retrofit): IServiceAPI = retrofit.create(IServiceAPI::class.java)