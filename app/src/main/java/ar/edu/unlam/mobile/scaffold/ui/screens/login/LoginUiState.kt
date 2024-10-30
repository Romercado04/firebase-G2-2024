package ar.edu.unlam.mobile.scaffold.ui.screens.login

sealed interface LoginUiState {
    object Loading : LoginUiState
    object Error : LoginUiState
    object Success : LoginUiState
}