package com.example.boostar_uaal.ui.screen.feedScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.theme.discountColor


@Composable
fun ProductInfoSection(
    modifier: Modifier = Modifier,
    name: String,
    price: Double,
    discountPrice: Double?
) {
    Column(
        modifier = modifier
            .padding(start = 16.dp, bottom = 110.dp) // Bottom padding alto para dejar espacio al Dock
            .fillMaxWidth(0.75f) // Ocupa el 75% del ancho
    ) {
        // Tag "Oferta"
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(40),
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            Text(
                text = "Oferta",
                color = discountColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }

        // Nombre
        Text(
            text = "Pantalón baggy tejido",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        // Precio
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "19,99 EUR",
                color = discountColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "24,99 EUR",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
                textDecoration = TextDecoration.LineThrough
            )
        }

    }
}