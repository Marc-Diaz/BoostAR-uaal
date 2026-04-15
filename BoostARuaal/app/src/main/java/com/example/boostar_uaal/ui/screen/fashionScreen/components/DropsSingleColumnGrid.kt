package com.example.boostar_uaal.ui.screen.fashionScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.components.DropCard
import com.example.boostar_uaal.core.entities.DropData

@Composable
fun DropsSingleColumnGrid(
    drops: List<DropData>,
    onReserveClick: (DropData) -> Unit,
    onBellClick: (DropData) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        drops.forEach { drop ->
            DropCard(
                drop = drop,
                onReserveClick = { onReserveClick(drop) },
                onBellClick = { onBellClick(drop) }
            )
        }
    }
}