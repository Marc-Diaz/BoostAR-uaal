package com.example.boostar_uaal.ui.screen.gameScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.boostar_uaal.core.entities.DaylyGoal
import com.example.boostar_uaal.core.entities.DaylyProgress

@Composable
fun DaylyGoalsSection(daylyProgress: DaylyProgress){
    Column() {
        DaylyGoalsHeader()
        DaylyProgressCard()
        LazyColumn() {
            items(daylyProgress.dailyGoals){ dailyGoal ->
                DaylyGoalItem(dailyGoal)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewDaylyGoalsSection(){
    val daylyProgress = DaylyProgress(
        completedGoals = 1,
        totalGoals = 4,
        dailyGoals = listOf(
            DaylyGoal(
                id = 0,
                title = "Prueba 1 prenda en AR",
                category = "Exploración",
                xp = 30,
                points = 15,
                isCompleted = true,
                totalProgress = 1,
                currentProgress = 1
            ),
            DaylyGoal(
                id = 1,
                title = "Crea un outfit con 3 colores",
                category = "Exploración",
                xp = 50,
                points = 25,
                isCompleted = false,
                currentProgress = 0,
                totalProgress = 3

            )
        )
    )
    DaylyGoalsSection(daylyProgress)
}