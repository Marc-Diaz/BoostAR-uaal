package com.example.boostar_uaal.ui.screen.gameScreen.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.InterText

// Colores basados en tu imagen
val ProgressBlue = Color(0xFF007BFF)
val ProgressTrackGray = Color(0xFFEEEEEE)

@Composable
fun DailyProgressCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            InterText(
                text = "Tu progreso de hoy",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold
            )

            InterText(
                text = "1/4 completados",
                color = ProgressBlue,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                InterText(
                    text = "25%",
                    color = ProgressBlue,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Barra de progreso
            LinearProgressIndicator(
                progress = { 0.25f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp),
                color = ProgressBlue,
                trackColor = ProgressTrackGray,
                strokeCap = StrokeCap.Round,
            )
        }
    }
}
@Preview
@Composable
fun PreviewDailyProgressCard(){
    DailyProgressCard()
}