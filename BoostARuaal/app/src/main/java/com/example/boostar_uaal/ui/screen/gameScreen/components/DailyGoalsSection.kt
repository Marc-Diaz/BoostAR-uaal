package com.example.boostar_uaal.ui.screen.gameScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.entities.DailyGoal
import com.example.boostar_uaal.core.entities.DailyProgress

@Composable
fun DailyGoalsSection(modifier: Modifier = Modifier, dailyProgress: DailyProgress){
    Column(modifier) {
        DailyGoalsHeader(day = dailyProgress.day, dayStreak = dailyProgress.dayStreak)
        DailyProgressCard(
            completedGoals = dailyProgress.completedGoals,
            totalGoals = dailyProgress.totalGoals,
        )
        dailyProgress.dailyGoals.forEach { dailyGoal ->
            DailyGoalItem(dailyGoal)
        }
        BonusDailyGoalCard(Modifier.padding(horizontal = 8.dp, vertical = 16.dp))
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewDailyGoalsSection(){
    val dailyProgress = DailyProgress(
        dayStreak = 12,
        completedGoals = 1,
        dailyGoals = listOf(
            DailyGoal(
                id = 0,
                title = "Prueba 1 prenda en AR",
                category = "Exploración",
                xp = 30,
                points = 15,
                isCompleted = true,
                totalProgress = 1,
                currentProgress = 1
            ),
            DailyGoal(
                id = 1,
                title = "Crea un outfit con 3 colores complementarios",
                category = "Exploración",
                xp = 50,
                points = 25,
                isCompleted = false,
                currentProgress = 0,
                totalProgress = 3

            )
        )
    )
    DailyGoalsSection(dailyProgress = dailyProgress)
}