package ar.edu.unlam.mobile.scaffold.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffold.ui.components.ShowError
import ar.edu.unlam.mobile.scaffold.ui.components.ShowKitties
import ar.edu.unlam.mobile.scaffold.ui.components.ShowLoading

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ListScreen(viewModel: ListViewModel = hiltViewModel()) {
    val screenState by viewModel.screenState.collectAsState()
    Log.d("ListScreen", "kittyHistory: $screenState")
    when (screenState) {
        ListScreenState.Loading -> ShowLoading()
        ListScreenState.Error -> ShowError { /*TODO: viewModel.retry()*/ }
        is ListScreenState.Success -> {
            ShowKitties(kitties = (screenState as ListScreenState.Success).kitties)
        }
    }
    Log.d("ListScreen", "kittyHistory: $screenState")
}


