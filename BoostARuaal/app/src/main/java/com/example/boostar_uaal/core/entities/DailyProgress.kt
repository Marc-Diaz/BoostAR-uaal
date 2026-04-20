package com.example.boostar_uaal.core.entities

import java.time.LocalDate

data class DailyProgress(
    private val DAYS: List<String> = listOf("LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO"),
    var dayStreak: Int,
    var dailyGoals: List<DailyGoal>,
    var completedGoals: Int,
    val totalGoals: Int = dailyGoals.count(),

    val day: String = DAYS[LocalDate.now().dayOfWeek.value - 1]
)