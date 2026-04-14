package com.example.boostar_uaal.core.entities

data class ChallengeQuestion(
    val id: Int,
    val question: String,
    val answers: List<QuestionAnswer>
)