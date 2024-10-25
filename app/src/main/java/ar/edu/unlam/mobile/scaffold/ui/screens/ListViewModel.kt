package ar.edu.unlam.mobile.scaffold.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.domain.kitty.models.Kitty
import ar.edu.unlam.mobile.scaffold.domain.kitty.services.KittyService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val kittyService: KittyService) : ViewModel() {

    var kitty : Flow<List<Kitty>> = MutableStateFlow(emptyList())

    private val _screenState: MutableStateFlow<ListScreenState> = MutableStateFlow(
        ListScreenState.Loading
    )
    val screenState: Flow<ListScreenState> = _screenState

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Log.d("ListViewModel", "Error retrieving kitty: ${throwable.message}")
        }

    init {
        fetchData()
    }

    fun retry() {
        _screenState.value = ListScreenState.Loading
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            kotlin.runCatching {
                kittyService.getAllKitties()
            }.onSuccess { kitties ->
                if (kitties.toList().isNotEmpty()) {
                    kitty = kitties
                    _screenState.value = ListScreenState.Success
                } else {
                    _screenState.value = ListScreenState.Error
                }
            }.onFailure {
                // se deberia manejar la opcion de mostrar gatitos que esten guardados en la bdd
                // antes de mostrar una pantalla de error
            }

        }
    }
}
