package com.example.boostar_uaal.core.entities

data class DaylyProgress(
    var completedGoals: Int,
    val totalGoals: Int,
    var dailyGoals: List<DaylyGoal>
)