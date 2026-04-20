package com.example.boostar_uaal.ui.screen.trendsScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.entities.LikeStyle

@Composable
fun BarGraph(modifier: Modifier = Modifier, data: List<LikeStyle>){
    val blueColor = Color(0xFF007AFF)
    val highlightColor = Color(0xFFFF523B)
    val trackColor = Color(0xFFE5E5EA)

    val maxDataValue = data.maxOfOrNull { it.value }?.coerceAtLeast(1) ?: 1

    val highestValue = data.maxOfOrNull { it.value } ?: 0

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp) // Altura total del componente
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            data.forEach { item ->
                val heightFraction = item.value.toFloat() / maxDataValue.toFloat()
                val barColor = if (item.value == highestValue && item.value > 0) highlightColor else blueColor

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(heightFraction)
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                        .background(barColor)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(trackColor)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            data.forEach { item ->
                // Mapeamos los nombres largos para que se parezcan a tu diseño original
                val shortName = when (item.name) {
                    "Streetwear" -> "STW"
                    "Hippie/Bohemio" -> "Boho"
                    "Vintage/Retro" -> "Retro"
                    "Alternativo" -> "Alt"
                    "Sportwear" -> "Sport"
                    else -> item.name
                }

                Text(
                    text = shortName,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    color = blueColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
@Preview
@Composable
fun PreviewMyChart() {
    val myChartData = listOf(
        LikeStyle(id = 3, name = "Sportwear", value = 5),
        LikeStyle(id = 6, name = "Alternativo", value = 0),
        LikeStyle(id = 2, name = "Streetwear", value = 3),
        LikeStyle(id = 5, name = "Hippie/Bohemio", value = 0),
        LikeStyle(id = 7, name = "Formal", value = 3),
        LikeStyle(id = 1, name = "Casual", value = 6),
        LikeStyle(id = 4, name = "Vintage/Retro", value = 5)
    )


    val displayData = myChartData.filter { it.name in listOf("Casual", "Formal", "Streetwear", "Sportwear", "Hippie/Bohemio") }

    BarGraph(data = displayData)
}