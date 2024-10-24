package ar.edu.unlam.mobile.scaffold.ui.screens

sealed class ListScreenState {
     object Loading : ListScreenState()
     object Error : ListScreenState()
     object Success : ListScreenState()
}