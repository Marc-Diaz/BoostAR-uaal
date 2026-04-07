package com.example.boostar_uaal.ui.screen.gameScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.entities.Lesson
import com.example.boostar_uaal.core.entities.LessonState

@Composable
fun LessonCard(lesson: Lesson) {
    val backgroundColor = when (lesson.state) {
        LessonState.COMPLETED -> Color(0xFFE8F9ED) // Verde clarito
        else -> Color.White
    }

    val borderColor = when (lesson.state) {
        LessonState.COMPLETED -> Color(0xFF4ADE80)
        else -> Color(0xFFE5E7EB)
    }

    val contentAlpha = if (lesson.state == LessonState.LOCKED) 0.5f else 1.0f

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, borderColor),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .alpha(contentAlpha),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(16.dp))
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(lesson.icon),
                    contentDescription = null,
                    tint = Color.Unspecified
                )

            }

            Spacer(modifier = Modifier.width(16.dp))

            // Información Central
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = lesson.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = lesson.category,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Badges de XP y Pts
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    BadgeInfo(text = "+${lesson.xp} XP", color = Color(0xFF22C55E), icon = R.drawable.game_icon)
                    BadgeInfo(text = "+${lesson.points} pts", color = Color(0xFF3B82F6), icon = R.drawable.boostar_logo)
                }
            }

            // Icono de Estado (Derecha)
            Box(modifier = Modifier.size(24.dp)) {
                when (lesson.state) {
                    LessonState.AVAILABLE -> Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color(0xFF3B82F6))
                    LessonState.COMPLETED -> Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF22C55E))
                    LessonState.LOCKED -> Icon(Icons.Default.Lock, contentDescription = null, tint = Color.Black)
                }
            }
        }
    }
}

@Composable
fun BadgeInfo(text: String, color: Color, icon: Int) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFFE5E7EB))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(icon), contentDescription = null, modifier = Modifier.size(14.dp), tint = color)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = text, color = color, style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
        }
    }
}

@Preview
@Composable
fun PreviewLessonCard(){
    val lesson = Lesson(
        id = 1,
        title = "Titulo",
        category = "Fits",
        xp = 250,
        points = 150,
        icon = R.drawable.home_hero,
        state = LessonState.COMPLETED
    )
    LessonCard(lesson)
}