package com.example.boostar_uaal.core.entities

data class Challenge(
    val id: Int,
    val title: String,
    val description: String,
    val time: Int,
    val xp: Int,
    val points: Int,
    val isActive: Boolean
)