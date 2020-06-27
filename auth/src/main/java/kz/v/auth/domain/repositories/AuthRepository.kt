package kz.v.auth.domain.repositories

import kz.v.auth.domain.usecases.AuthUseCase
import kz.v.auth.domain.usecases.CreateCoordinatesUseCase
import kz.v.auth.entity.AuthResponseDTO
import kz.v.network_components.BaseResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun auth(params: AuthUseCase.Params): BaseResponse<AuthResponseDTO>
    suspend fun createLocation(params: List<CreateCoordinatesUseCase.Params>): BaseResponse<Boolean>
}