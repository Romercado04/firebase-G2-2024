package ar.edu.unlam.mobile.scaffold.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffold.domain.kitty.models.Kitty

@Composable
fun ShowKitties(kitties: List<Kitty>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(kitties.size) { kitty ->
            KittyCard(kitties[kitty])
        }
    }
}