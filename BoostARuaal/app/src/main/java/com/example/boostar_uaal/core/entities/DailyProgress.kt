package com.example.boostar_uaal.core.entities

data class DailyProgress(
    var dayStreak: Int,
    var completedGoals: Int,
    val totalGoals: Int,
    var dailyGoals: List<DailyGoal>
)