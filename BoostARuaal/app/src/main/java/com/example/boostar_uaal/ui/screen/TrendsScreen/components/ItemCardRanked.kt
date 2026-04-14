package com.example.boostar_uaal.ui.screen.TrendsScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun GiantRankedItemCard(
    imageUrl: String,
    rank: Int,
    productName: String,
    price: String,
    likes: String,
    onClick: () -> Unit
) {
    val blueOutlineColor = Color(0xFF007AFF) // Azul de tu diseño

    // Box contenedor: Más ancho que la tarjeta para dejar asomar el número
    Box(
        modifier = Modifier
            .width(210.dp) // Ancho total (Número asomando + Tarjeta)
            .padding(bottom = 8.dp)
    ) {

        // --- 1. EL NÚMERO GIGANTE DE FONDO ---
        Text(
            text = rank.toString(),
            fontSize = 180.sp, // Tamaño bestial
            fontWeight = FontWeight.Black,
            style = TextStyle(
                color = blueOutlineColor,
                // AQUÍ ESTÁ LA MAGIA DEL CONTORNO:
                drawStyle = Stroke(width = 12f, join = StrokeJoin.Round)
            ),
            modifier = Modifier
                .align(Alignment.CenterStart) // Lo pegamos a la izquierda
                .offset(x = 0.dp, y = (-40).dp) // Lo subimos un poco para centrarlo visualmente
        )

        // --- 2. LA TARJETA Y LOS TEXTOS (POR DELANTE) ---
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd) // Lo pegamos a la derecha tapando medio número
                .width(150.dp) // Ancho exacto de la tarjeta de ropa
        ) {
            // La tarjeta de la ropa
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable { onClick() },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Top $rank",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // Logo pequeño del Partner (PB) abajo a la derecha
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .border(1.dp, Color(0xFFEEEEEE), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("PB", fontWeight = FontWeight.ExtraBold, fontSize = 10.sp, color = Color.Black)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Textos debajo de la tarjeta (Título, Precio y Corazón)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                // Columna Izquierda: Nombre y Precio
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = productName,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis // Pone "..." si es muy largo
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = price,
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Columna Derecha: Corazón y Likes
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Filled.Favorite, // Cambia por tu icono si usas uno XML
                        contentDescription = "Likes",
                        tint = Color.Red,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = likes,
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}