package kz.v.auth.presentation.di

import kz.v.auth.domain.usecases.AuthUseCase
import kz.v.auth.domain.usecases.CreateCoordinatesUseCase
import org.koin.dsl.module

val usecaseModule = module {
    factory { AuthUseCase(authRepository = get()) }
    factory { CreateCoordinatesUseCase(authRepository = get()) }
}