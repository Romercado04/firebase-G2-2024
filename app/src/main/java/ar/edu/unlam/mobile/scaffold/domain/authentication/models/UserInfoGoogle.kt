package ar.edu.unlam.mobile.scaffold.domain.authentication.models

data class UserInfoGoogle(
    val userId: String,
    val displayName: String?,
    val email: String?,
    val photoUrl: String?,
)
