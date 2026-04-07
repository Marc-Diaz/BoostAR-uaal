package com.example.boostar_uaal.ui.screen.gameScreen

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.entities.Challenge
import com.example.boostar_uaal.core.entities.DailyGoal
import com.example.boostar_uaal.core.entities.DailyProgress
import com.example.boostar_uaal.core.entities.KnowledgeArea
import com.example.boostar_uaal.core.entities.Lesson
import com.example.boostar_uaal.core.entities.LessonState
import com.example.boostar_uaal.core.entities.UserGameStats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameScreenViewModel: ViewModel() {

    private var _userStats: MutableStateFlow<UserGameStats?> = MutableStateFlow(null)
    val userStats = _userStats.asStateFlow()
    private var _daylyGoals: MutableStateFlow<DailyProgress?> = MutableStateFlow(null)
    val daylyGoals: StateFlow<DailyProgress?> = _daylyGoals.asStateFlow()

    private var _challenge: MutableStateFlow<Challenge?> = MutableStateFlow(null)
    val challenge = _challenge.asStateFlow()

    private var _knowleadgeAreas: MutableStateFlow<List<KnowledgeArea>> = MutableStateFlow(emptyList())
    val knowledgeArea = _knowleadgeAreas.asStateFlow()

    private var _lessons : MutableStateFlow<List<Lesson>> = MutableStateFlow(emptyList())
    val lessons = _lessons
    fun loadDaylyGoals(){
        _daylyGoals.value = DailyProgress(
            dayStreak = 12,
            completedGoals = 1,
            totalGoals = 4,
            dailyGoals = listOf(
                DailyGoal(
                    id = 0,
                    title = "Prueba 1 prenda en AR",
                    category = "Exploración",
                    xp = 30,
                    points = 15,
                    isCompleted = true,
                    totalProgress = 1,
                    currentProgress = 1
                ),
                DailyGoal(
                    id = 1,
                    title = "Crea un outfit con 3 colores",
                    category = "Exploración",
                    xp = 50,
                    points = 25,
                    isCompleted = false,
                    totalProgress = 0,
                    currentProgress = 3
                )
            )
        )
    }

    fun loadUserStats(){
        _userStats.value = UserGameStats(
            level = 12,
            currentXp = 3400,
            totalXp = 5000,
            points = 1240,
            title = "PRINCIPIANTE"
        )
    }

    fun loadChallenge(){
         _challenge.value = Challenge(
            id = 1,
            title = "Domina la\ncolorimetría",
            description = "Aprende a combinar colores y crea un outfit con AR que refleje tu paleta personal.",
            time = 15,
            xp = 250,
            points = 150,
            isActive = false
        )
    }

    fun loadKnowledgeAreas(){
        _knowleadgeAreas.value = listOf(
            KnowledgeArea(
                id = 1,
                title = "Fits",
                percentage = 25,
                color = Color(0xFF0080FF),
                image = R.drawable.boostar_logo
            ),
            KnowledgeArea(
                id = 2,
                title = "Color",
                percentage = 68,
                color = Color(0xFFFF00DD),
                image = R.drawable.heart_icon
            ),
            KnowledgeArea(
                id = 3,
                title = "Estilo",
                percentage = 43,
                color = Color(0xFFFFD900),
                image = R.drawable.basket_icon
            )
        )
    }

    fun loadLessons(){
        _lessons.value = listOf(
            Lesson(
                id = 1,
                title = "Outfit monocromático",
                category = "Color",
                xp = 120,
                points = 80,
                icon = R.drawable.black_square,
                state = LessonState.AVAILABLE
            ),
            Lesson(
                id = 2,
                title = "Streetwear vs Formal",
                category = "Estilo",
                xp = 200,
                points = 140,
                icon = R.drawable.cap,
                state = LessonState.AVAILABLE
            ),
            Lesson(
                id = 3,
                title = "Streetwear vs Formal",
                category = "Fits",
                xp = 160,
                points = 100,
                icon = R.drawable.ruler,
                state = LessonState.COMPLETED
            ),
            Lesson(
                id = 4,
                title = "Paleta de tierra",
                category = "Color",
                xp = 180,
                points = 120,
                icon = R.drawable.leaf,
                state = LessonState.LOCKED
            )
        )
    }
}