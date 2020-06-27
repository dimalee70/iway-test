package kz.v.ui_components

/**
 * A wrapper for database and network states.
 */
sealed class ResultState<T> {

    /**
     * A state of [data] which shows that we know there is still an update to come.
     */
    data class Loading<T>(val data: T?) : ResultState<T>()

    /**
     * A state that shows the [data] is the last known update.
     */
    data class Success<T>(var data: T) : ResultState<T>()

    data class SuccessVoid<T>(var data: T?) : ResultState<T>()

    /**
     * A state that shows deleting with animation.
     */
    data class SuccessDeleting<T>(var position: Int, var data: T? = null) : ResultState<T>()

    /**
     * A state that shows the [data] without clearing previous data.
     */
    data class SuccessPagination<T>(var data: T) : ResultState<T>()

    /**
     * A state to show a [throwable] is thrown beside the [lastData] which is cached.
     */
    data class Error<T>(val throwable: Throwable, val lastData: T?) : ResultState<T>()

    data class Empty<T>(val data: T?) : ResultState<T>()

    data class EmptyTabLayout<T>(val data: T?) : ResultState<T>()



}