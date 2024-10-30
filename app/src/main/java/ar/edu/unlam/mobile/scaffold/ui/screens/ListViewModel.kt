package ar.edu.unlam.mobile.scaffold.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.domain.kitty.services.KittyService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val kittyService: KittyService) : ViewModel() {
    private val _screenState = MutableStateFlow<ListScreenState>(ListScreenState.Loading)
    val screenState: StateFlow<ListScreenState> = _screenState


    init {
        observeKittyHistory()
    }

    private fun observeKittyHistory() {
        viewModelScope.launch {
            kittyService.kittyHistory.collect { kitties ->
                Log.d("ListViewModel", "Contenido de kittyHistory: $kitties")
                _screenState.value = if (kitties.isNotEmpty()) {
                    ListScreenState.Success(kitties)
                } else {
                    ListScreenState.Error
                }
            }
        }
    }
}
