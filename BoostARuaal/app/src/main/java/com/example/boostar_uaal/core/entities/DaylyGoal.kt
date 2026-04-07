package com.example.boostar_uaal.core.entities

data class DaylyGoal(
    val id: Int,
    val title: String,
    val category: String,
    val xp: Int,
    val points: Int,
    val isCompleted: Boolean,
    val totalProgress: Int,
    var currentProgress: Int
)