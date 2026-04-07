package com.example.boostar_uaal.ui.screen.gameScreen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.entities.DaylyGoal

@Composable
fun DaylyGoalItem(dailyGoal: DaylyGoal) {
    // Definición de colores según el diseño
    val greenColor = Color(0xFF00E63D)
    val grayText = Color(0xFF9E9E9E)
    val blueColor = Color(0xFF007AFF)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatusIndicator(dailyGoal.isCompleted, greenColor)

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                InterText(
                    text = dailyGoal.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    // Si está completado, tachamos el texto
                    textDecoration = if (dailyGoal.isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )
                InterText(
                    text = dailyGoal.category,
                    color = grayText,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))


                Row(verticalAlignment = Alignment.CenterVertically) {
                    RewardLabel(icon = R.drawable.boostar_logo, text = "+${dailyGoal.xp} XP", color = greenColor) // Sustituir por icono de rayo
                    Spacer(modifier = Modifier.width(12.dp))
                    RewardLabel(icon = R.drawable.boostar_logo, text = "+${dailyGoal.points} pts", color = blueColor) // Sustituir por icono de mundo
                }
            }

            InterText(
                text = "${dailyGoal.currentProgress}/${dailyGoal.totalProgress}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = if (dailyGoal.isCompleted) greenColor else Color.Gray
            )
        }
    }
}

@Composable
fun StatusIndicator(isCompleted: Boolean, activeColor: Color) {
    if (isCompleted) {
        Surface(
            modifier = Modifier.size(32.dp),
            shape = CircleShape,
            color = activeColor
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(4.dp)
            )
        }
    } else {
        Box(
            modifier = Modifier
                .size(32.dp)
                .border(2.dp, Color.LightGray, CircleShape)
        )
    }
}

@Composable
fun RewardLabel(icon: Int, text: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        InterText(
            text = text,
            color = color,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun PreviewDailyGoalItem(){
    val dailyGoal = DaylyGoal(
        id = 0,
        title = "hola",
        category = "Tierra",
        xp = 50,
        points = 50,
        isCompleted = true,
        totalProgress = 3,
        currentProgress = 3
    )
    DaylyGoalItem(dailyGoal)
}