package ar.edu.unlam.mobile.scaffold.ui.screens

import ar.edu.unlam.mobile.scaffold.domain.kitty.models.Kitty

sealed class ListScreenState {
     object Loading : ListScreenState()
     object Error : ListScreenState()
     data class Success(val kitties: List<Kitty>) : ListScreenState()
}