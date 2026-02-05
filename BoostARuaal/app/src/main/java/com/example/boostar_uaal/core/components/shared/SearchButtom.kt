package com.example.boostar_uaal.core.components.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchButton(modifier: Modifier = Modifier) {
    SmallFloatingActionButton(
        onClick = {},
        containerColor = Color.White.copy(alpha = 0.8f),
        contentColor = Color.Black,
        shape = CircleShape,
        modifier = modifier.padding(top = 48.dp, end = 16.dp)
    ) {
        Icon(Icons.Default.Search, contentDescription = "Buscar")
    }
}
