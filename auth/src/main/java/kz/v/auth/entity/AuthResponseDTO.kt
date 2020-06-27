package kz.v.auth.entity

data class AuthResponseDTO(
    val token: String,
    val refresh_token: String
)