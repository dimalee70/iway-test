package kz.v.auth.presentation.ui.register

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthResponseUi(
    val token: String,
    val refresh_token: String
) : Parcelable