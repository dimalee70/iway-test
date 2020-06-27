package kz.v.ui_components.base

data class Error(
    var name: String?,
    override var message: String?,
    var code: Int?,
    var status: Int?
): Throwable(message = message)