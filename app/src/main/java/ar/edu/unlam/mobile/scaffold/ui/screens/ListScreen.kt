package ar.edu.unlam.mobile.scaffold.ui.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffold.ui.components.ShowError
import ar.edu.unlam.mobile.scaffold.ui.components.ShowKitties
import ar.edu.unlam.mobile.scaffold.ui.components.ShowLoading

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ListScreen(viewModel: ListViewModel = hiltViewModel()) {
    val screenState by viewModel.screenState.collectAsState(initial = ListScreenState.Loading)

    when (screenState) {
        ListScreenState.Loading -> ShowLoading()
        ListScreenState.Error -> ShowError { viewModel.retry() }
        is ListScreenState.Success -> ShowKitties(kitties = viewModel.kitty.collectAsState(initial = emptyList()).value)
    }
}


