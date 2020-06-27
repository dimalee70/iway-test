package kz.v.auth.presentation.di

import kz.v.auth.data.repository.DefaultAuthRepository
import kz.v.auth.domain.repositories.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { DefaultAuthRepository(authApi = get()) }
}