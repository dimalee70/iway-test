package kz.v.auth.domain.usecases

import kz.v.auth.domain.repositories.AuthRepository
import kz.v.auth.entity.AuthResponseDTO
import kz.v.network_components.BaseResponse
import kz.v.network_components.BaseUseCase
import kz.v.network_components.Either
import kz.v.network_components.Failure

class AuthUseCase(
    private val authRepository: AuthRepository
) : BaseUseCase<BaseResponse<AuthResponseDTO>, AuthUseCase.Params>() {


    override suspend fun run(params: Params): Either<Failure, BaseResponse<AuthResponseDTO>> {
        return try {
            val response = authRepository.auth(params = params)
            Either.Right(response)
        } catch (e: Exception) {
            Either.Left(Failure.FeatureFailure(e))
        }
    }

    data class Params(
        var login: Long? = null,
        var password: String? = null,
        val device_info: DeviceInfo
    )

    data class DeviceInfo(
        var os: String = "android",
        var app_version: String = "72",
        var hardware_id: String = "b1g25d21-54d5-k1f1-8d83-5443b51b1328"
    )
}