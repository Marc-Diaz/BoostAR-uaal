package com.example.boostar_uaal.core.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
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
            val dotColor =
                if (currentIndex == iteration) Color.LightGray else Color.LightGray.copy(alpha = 0.4f)
            val dotsSize = if (currentIndex == iteration) 8.dp else 6.dp

            Box(
                modifier = Modifier
                    .size(dotsSize)
                    .background(dotColor, CircleShape)
            )
        }
    }
}
@Composable
fun PaginationPoints(size: Int, pageState: PagerState){
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(size) { iteration ->
            val dotColor =
                if (pageState.currentPage == iteration) Color.LightGray else Color.LightGray.copy(alpha = 0.4f)
            val dotsSize = if (pageState.currentPage == iteration) 8.dp else 6.dp

            Box(
                modifier = Modifier
                    .size(dotsSize)
                    .background(dotColor, CircleShape)
            )
        }
    }
}