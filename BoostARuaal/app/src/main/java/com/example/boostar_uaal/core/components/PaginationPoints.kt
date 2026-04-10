package com.example.boostar_uaal.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PaginationPoints(size: Int, currentIndex: Int){
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(size) { iteration ->
            val color =
                if (currentIndex == iteration) Color.LightGray else Color.LightGray.copy(alpha = 0.4f)
            val size = if (currentIndex == iteration) 8.dp else 6.dp

            Box(
                modifier = Modifier
                    .size(size)
                    .background(color, CircleShape)
            )
        }
    }
}