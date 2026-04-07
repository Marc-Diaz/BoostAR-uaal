package com.example.boostar_uaal.ui.screen.gameScreen

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.boostar_uaal.core.entities.DaylyGoal
import com.example.boostar_uaal.core.entities.DaylyProgress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameScreenViewModel: ViewModel() {

    var _daylyGoals: MutableStateFlow<DaylyProgress?> = MutableStateFlow(null)
    val daylyGoals: StateFlow<DaylyProgress?> = _daylyGoals.asStateFlow()

    fun loadDaylyGoals(){
        _daylyGoals.value = DaylyProgress(
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
                    totalProgress = 0,
                    currentProgress = 3
                )
            )
        )
    }
}