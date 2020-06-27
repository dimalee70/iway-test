package kz.v.auth.presentation.di

import kz.v.auth.data.remote.AuthApi
import kz.v.dep_inject.networkModule
import org.koin.dsl.module
import retrofit2.Retrofit


val networkModule = module {
    networkModule.single {
        createAuthApi(retrofit = get())
    }
}

fun createAuthApi(retrofit: Retrofit): AuthApi {
    return retrofit.create(AuthApi::class.java)
}