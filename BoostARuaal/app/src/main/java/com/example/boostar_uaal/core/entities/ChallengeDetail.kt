package com.example.boostar_uaal.core.entities

data class ChallengeDetail(
    val id: Int,
    val steps: List<ChallengeStep>,
    val questions: List<ChallengeQuestion>
)