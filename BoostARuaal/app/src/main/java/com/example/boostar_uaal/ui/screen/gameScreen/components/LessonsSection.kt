package com.example.boostar_uaal.ui.screen.gameScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.entities.Lesson
import com.example.boostar_uaal.core.entities.LessonState
import com.example.boostar_uaal.core.theme.secondaryTextColor

@Composable
fun LessonSection(lessons: List<Lesson>){
    Column {
        InterText(
            modifier = Modifier.padding(start = 48.dp),
            text = "ÁREAS DE CONOCIMIENTO",
            style = TextStyle(
                fontSize = 14.9.sp,
                fontWeight = FontWeight(700),
                color = secondaryTextColor,
            )
        )
        LazyColumn() {
            items(lessons){ area ->
                LessonCard(area)
            }
        }
    }
}
@Preview
@Composable
fun PreviewLessonSection(){
    val lessons = listOf(
        Lesson(
            id = 1,
            title = "Outfit monocromático",
            category = "Color",
            xp = 120,
            points = 80,
            icon = R.drawable.black_square,
            state = LessonState.AVAILABLE
        ),
        Lesson(
            id = 2,
            title = "Streetwear vs Formal",
            category = "Estilo",
            xp = 200,
            points = 140,
            icon = R.drawable.cap,
            state = LessonState.AVAILABLE
        ),
        Lesson(
            id = 3,
            title = "Streetwear vs Formal",
            category = "Fits",
            xp = 160,
            points = 100,
            icon = R.drawable.ruler,
            state = LessonState.COMPLETED
        ),
        Lesson(
            id = 4,
            title = "Paleta de tierra",
            category = "Color",
            xp = 180,
            points = 120,
            icon = R.drawable.leaf,
            state = LessonState.LOCKED
        )
    )
    LessonSection(lessons)
}