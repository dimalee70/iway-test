package kz.v.auth.data.remote

import kz.v.auth.domain.usecases.AuthUseCase
import kz.v.auth.domain.usecases.CreateCoordinatesUseCase
import kz.v.auth.entity.AuthResponseDTO
import kz.v.network_components.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

private const val AUTH = "v3/driver/auth/auth"
private const val CREATE = "v3/locations/create"

interface AuthApi {

    @POST(AUTH)
    suspend fun auth(
        @Body params: AuthUseCase.Params
    ): Response<BaseResponse<AuthResponseDTO>>

    @POST(CREATE)
    suspend fun createLocations(
        @Body params: List<CreateCoordinatesUseCase.Params>
    ): Response<BaseResponse<Boolean>>
}