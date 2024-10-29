package ar.edu.unlam.mobile.scaffold.domain.authentication.models

sealed class AuthRes<out T> {

    data class Success<out T>(val data: T) : AuthRes<T>()
    data class Error(val message: String) : AuthRes<Nothing>()
}