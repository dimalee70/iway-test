package kz.v.auth.presentation.di

import kz.v.auth.presentation.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel { RegisterViewModel(authUseCase = get(), createCoordinatesUseCase = get()) }
}