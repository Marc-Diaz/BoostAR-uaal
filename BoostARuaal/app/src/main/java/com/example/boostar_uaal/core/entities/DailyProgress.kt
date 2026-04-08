package com.example.boostar_uaal.core.entities

import java.time.LocalDate

val DAYS = arrayOf("LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO")
data class DailyProgress(
    var dayStreak: Int,
    var dailyGoals: List<DailyGoal>,
    var completedGoals: Int,
    val totalGoals: Int = dailyGoals.count(),

    val day: String = DAYS[LocalDate.now().dayOfWeek.value - 1]
)