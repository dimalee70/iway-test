package kz.v.ui_components.utils

import androidx.lifecycle.MutableLiveData
import kz.v.ui_components.ResultState
import kz.v.ui_components.base.Error

fun <T> MutableLiveData<ResultState<T>>.setSuccess(data: T) =
    postValue(ResultState.Success(data))

fun <T> MutableLiveData<ResultState<T>>.setSuccessDeleting(position: Int) =
    postValue(ResultState.SuccessDeleting(position))

fun <T> MutableLiveData<ResultState<T>>.setSuccessPagination(data: T) =
    postValue(ResultState.SuccessPagination(data))

fun <T> MutableLiveData<ResultState<T>>.setLoading() =
    postValue(ResultState.Loading(null))


fun <T> MutableLiveData<ResultState<T>>.setError(e: Throwable, message: T? = null) =
    postValue(ResultState.Error(e, message))

fun <T> MutableLiveData<ResultState<T>>.setEmpty() =
    postValue(ResultState.Empty(null))

fun <T> MutableLiveData<ResultState<T>>.setSuccess() =
    postValue(ResultState.SuccessVoid(null))


fun <T> MutableLiveData<T>.call() = postValue(null)
