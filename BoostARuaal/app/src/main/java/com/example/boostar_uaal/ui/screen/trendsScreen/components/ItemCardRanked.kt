package com.example.boostar_uaal.ui.screen.trendsScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import com.example.boostar_uaal.core.components.ProductCard
import com.example.boostar_uaal.core.theme.primaryColor
import com.example.core.entities.Product

@Composable
fun GiantRankedItemCard(
    product: Product,
    rank: Int,
    onClick: () -> Unit,
    onLikeClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .width(210.dp)
            .padding(bottom = 8.dp)
    ) {

        Text(
            text = rank.toString(),
            fontSize = 180.sp,
            fontWeight = FontWeight.Black,
            style = TextStyle(
                color = primaryColor,
                drawStyle = Stroke(width = 12f, join = StrokeJoin.Round)
            ),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = 0.dp, y = (-40).dp)
        )
        ProductCard(
            modifier = Modifier.offset(x = (60.dp)),
            product = product,
            clickable = { onClick() },
            onLikeClick = { onLikeClick() }
        )
    }
}