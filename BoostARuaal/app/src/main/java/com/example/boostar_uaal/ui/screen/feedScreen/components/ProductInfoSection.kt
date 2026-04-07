package com.example.boostar_uaal.ui.screen.feedScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradient
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.InterText
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
            .fillMaxWidth(0.75f)
    ) {
        discountPrice?.let {
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(40),
                modifier = Modifier
                    .padding(bottom = 6.dp)
                    .shadow(
                        shape = RoundedCornerShape(40),
                        elevation = 2.dp
                    )
            ) {
                Text(
                    text = "Oferta",
                    color = discountColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

        }
        InterText(
            modifier = Modifier
                .height(24.dp),
            text = name,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(4f, 4f),
                    blurRadius = 16f
                )
            )
        )

        // Precio
        Row(verticalAlignment = Alignment.CenterVertically) {
            discountPrice?.let {
                InterText(
                    text = "$discountPrice€",
                    color = discountColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(4f, 4f),
                            blurRadius = 16f
                        )
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            InterText(
                text = "$price€",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
                style = TextStyle(
                    textDecoration = if (discountPrice != null) TextDecoration.LineThrough else TextDecoration.None,
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(4f, 4f),
                        blurRadius = 16f
                    )
                )
            )
        }


    }
}