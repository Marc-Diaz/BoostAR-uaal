package com.example.boostar_uaal.core.entities


data class Lesson(
    val id: Int,
    val title: String,
    val category: String,
    val xp: Int,
    val points: Int,
    val icon: Int, // R.drawable...
    val state: LessonState
)
enum class LessonState {
    AVAILABLE, COMPLETED, LOCKED
}
