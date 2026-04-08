package com.example.boostar_uaal.core.entities

data class ChallengeStep(
    val id: Int,
    val multimedia: Int,
    val title: String? = null,
    val text: String,
    val sleepTimeInSeconds: Int
)