package kz.v.network_components

import java.lang.Error

data class BaseResponse<T>(
    var result: T? = null,
    var error: Error? = null
)