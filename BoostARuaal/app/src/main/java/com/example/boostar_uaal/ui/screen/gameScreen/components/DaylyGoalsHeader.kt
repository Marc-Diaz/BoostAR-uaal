package com.example.boostar_uaal.ui.screen.gameScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.InterText

// Define los colores específicos de tu diseño
val AppBlue = Color(0xFF007BFF)
val AppTextGray = Color(0xFF6C757D)
val AppIconBackgroundBlue = Color(0xFFE9F5FF)
val AppIconBorderBlue = Color(0xFFB9D8FB)

@Composable
fun DaylyGoalsHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        InterText(
            text = "HOY - MARTES",
            color = AppTextGray,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Columna para los textos de objetivos
            Column {
                InterText(
                    text = "Objetivos diarios",
                    color = AppBlue,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold, // Muy grueso como en la imagen
                    letterSpacing = (-0.5).sp // Ajusta ligeramente el espaciado entre letras
                )
                Spacer(modifier = Modifier.height(4.dp))
                InterText(
                    text = "Se renuevan cada 24 horas",
                    color = AppTextGray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Card(
                shape = RoundedCornerShape(16.dp), // Bordes redondeados
                colors = CardDefaults.cardColors(
                    containerColor = AppIconBackgroundBlue // Color de fondo claro
                ),
                border = BorderStroke(1.dp, AppIconBorderBlue), // Borde azul claro
                modifier = Modifier
                    .padding(start = 16.dp) // Espacio a la izquierda para no pegarse al texto
                    .widthIn(min = 100.dp) // Ancho mínimo aproximado
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp), // Espaciado interno
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InterText(text = "🔥", fontSize = 24.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    InterText(
                        text = "12",
                        color = AppBlue,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    InterText(
                        text = "Días",
                        color = AppBlue,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun PreviewDaylyGoalsHeader(){
    DaylyGoalsHeader()
}