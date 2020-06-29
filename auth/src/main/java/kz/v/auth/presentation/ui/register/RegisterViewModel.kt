package kz.v.auth.presentation.ui.register

import androidx.lifecycle.*
import kz.v.auth.domain.usecases.AuthUseCase
import kz.v.auth.domain.usecases.CreateCoordinatesUseCase
import kz.v.auth.entity.AuthResponseDTO
import kz.v.network_components.BaseResponse
import kz.v.network_components.Failure
import kz.v.ui_components.ResultState
import kz.v.ui_components.utils.setError
import kz.v.ui_components.utils.setLoading
import kz.v.ui_components.utils.setSuccess
import timber.log.Timber

class RegisterViewModel(
    private val authUseCase: AuthUseCase,
    private val createCoordinatesUseCase: CreateCoordinatesUseCase
) : ViewModel() {

    private val _authResponse = MutableLiveData<ResultState<AuthResponseUi>>()
    val authResponse: LiveData<ResultState<AuthResponseUi>>
        get() = _authResponse

    private val _createResponse = MutableLiveData<ResultState<Boolean>>()
    val createResponse: LiveData<ResultState<Boolean>>
        get() = _createResponse

    val loading = Transformations.map(_createResponse) {
        it is ResultState.Loading
    }

//    init {
//        auth()
//    }

    private fun createCoordinates() {
        _createResponse.setLoading()
        val params = listOf(
            CreateCoordinatesUseCase.Params(
                point = CreateCoordinatesUseCase.Point(),
                trip_id = 2101015,
                sent = "2019-06-07 06:38",
                type = 1,
                speed = 60
            )
        )
        createCoordinatesUseCase(viewModelScope, params) {
            it.either(
                ::onSentFailure,
                ::onSentSuccess
            )
        }
    }

    fun onSentClicked() {
        Timber.i("onSentClicked")
        createCoordinates()
    }

    private fun onSentFailure(e: Failure) {
        Timber.i("onSentFailure, $e")
        _createResponse.setError(e.exception)
    }

    private fun onSentSuccess(data: BaseResponse<Boolean>) {
        if (data.result != null)
            _createResponse.setSuccess(data = data.result!!)
        else
            _createResponse.setError(e = data.error!!)
    }

    fun auth() {
        val params = AuthUseCase.Params(
            login = 917428730930,
            password = "351597",
            device_info = AuthUseCase.DeviceInfo(
                os = "android",
                app_version = "72",
                hardware_id = "b1g25d21-54d5-k1f1-8d83-5443b51b1328"
            )
        )
        authUseCase(viewModelScope, params) { it.either(::onFailure, ::onSuccess) }
    }

    private fun onFailure(e: Failure) {
        Timber.i("onFailure, $e")
        _authResponse.setError(e.exception)
    }

    private fun onSuccess(data: BaseResponse<AuthResponseDTO>) {
        Timber.i("onSuccess, $data")
        if (data.result != null)
            _authResponse.setSuccess(transformAuthResponsePresentation(data = data.result!!))
        else
            _authResponse.setError(data.error!!)
    }

    private fun transformAuthResponsePresentation(data: AuthResponseDTO) =
        AuthResponseUi(
            token = data.token,
            refresh_token = data.refresh_token
        )
}