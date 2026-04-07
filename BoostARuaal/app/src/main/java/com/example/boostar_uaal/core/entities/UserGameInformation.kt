package com.example.boostar_uaal.core.entities

data class UserGameInformation(
    var level: Int,
    var currentXp: Int,
    var maxXp: Int,
    var points: Int,
    var title: String,
    var dayStreak: String,
)