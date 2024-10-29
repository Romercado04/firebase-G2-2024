package ar.edu.unlam.mobile.scaffold.domain.kitty.services

import android.util.Log
import ar.edu.unlam.mobile.scaffold.data.kitty.repository.KittyRepository
import ar.edu.unlam.mobile.scaffold.domain.kitty.models.Kitty
import ar.edu.unlam.mobile.scaffold.domain.kitty.usecases.KittyGetter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class KittyService @Inject constructor(val repository: KittyRepository) : KittyGetter {
    override suspend fun getKitty(): Flow<Kitty> {
        return this.repository.getKitty()
    }

    override suspend fun getKittyById(id: Int): Flow<Kitty> {
        TODO("Not yet implemented")
    }

    private val _kittyHistory = MutableStateFlow<List<Kitty>>(emptyList())
    val kittyHistory: StateFlow<List<Kitty>> = _kittyHistory

    fun addKittyToHistory(kitty: Kitty) {
        if (!kittyHistory.value.contains(kitty)) { // Evita duplicados
            _kittyHistory.value += kitty
            Log.d(
                "KittyService",
                "Kitty añadido: ${kitty}. Nuevo historial: ${_kittyHistory.value}"
            )
        }
    }
}
