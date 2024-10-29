package ar.edu.unlam.mobile.scaffold.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.domain.authentication.usercases.GetCurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject
constructor(
    private val authUser: GetCurrentUser,
) : ViewModel() {
    private val _isUserAuthenticated = MutableStateFlow<Boolean?>(null)
    val isUserAuthenticated: StateFlow<Boolean?> = _isUserAuthenticated

    // cuando iniciamos la activity tiene que traer el user
    init {
        viewModelScope.launch {
            checkUserAuthenticated()
        }
    }

    suspend fun checkUserAuthenticated() {
        val currentUser = authUser.getCurrentUser()
        _isUserAuthenticated.value = currentUser != null
    }
}