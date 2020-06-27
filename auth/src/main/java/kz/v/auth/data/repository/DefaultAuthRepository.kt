package kz.v.auth.data.repository

import kz.v.auth.data.remote.AuthApi
import kz.v.auth.domain.repositories.AuthRepository
import kz.v.auth.domain.usecases.AuthUseCase
import kz.v.auth.domain.usecases.CreateCoordinatesUseCase

class DefaultAuthRepository(
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun auth(params: AuthUseCase.Params) = authApi.auth(params).body()!!
    override suspend fun createLocation(params: List<CreateCoordinatesUseCase.Params>) =
        authApi.createLocations(params).body()!!
}