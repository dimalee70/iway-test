package kz.v.auth.domain.usecases

import kz.v.auth.domain.repositories.AuthRepository
import kz.v.network_components.BaseResponse
import kz.v.network_components.BaseUseCase
import kz.v.network_components.Either
import kz.v.network_components.Failure

class CreateCoordinatesUseCase(
    private val authRepository: AuthRepository
) : BaseUseCase<BaseResponse<Boolean>, List<CreateCoordinatesUseCase.Params>>() {

    override suspend fun run(params: List<Params>): Either<Failure, BaseResponse<Boolean>> {
        return try {
            val response = authRepository.createLocation(params = params)
            Either.Right(response)
        } catch (e: Exception) {
            Either.Left(Failure.FeatureFailure(e))
        }
    }

    data class Params(
        var point: Point? = null,
        var sent: String? = null,
        var trip_id: Int? = null,
        var type: Int? = null,
        var speed: Int = 60
    )

    data class Point(
        var lat: Double? = 55.32562,
        var lng: Double? = 88.36863
    )
}