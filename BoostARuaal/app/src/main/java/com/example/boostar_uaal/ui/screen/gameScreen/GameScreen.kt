package com.example.boostar_uaal.ui.screen.gameScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.gameScreen.components.DailyGoalsSection
import com.example.boostar_uaal.ui.screen.gameScreen.components.ForYouSection
import com.example.boostar_uaal.ui.screen.gameScreen.components.GameHeader
import com.example.boostar_uaal.ui.screen.gameScreen.components.GameUserInformationCard
import com.example.boostar_uaal.ui.screen.gameScreen.components.KnowledgeSection
import com.example.boostar_uaal.ui.screen.gameScreen.components.LessonSection


@Composable
fun GameScreen(navigateTo: (Routes) -> Unit, back: () -> Unit, backTo: (Routes) -> Unit){
    val gameScreenViewModel = viewModel<GameScreenViewModel>()
    val dailyProgress by gameScreenViewModel.daylyGoals.collectAsState()
    val userStats by gameScreenViewModel.userStats.collectAsState()
    val challenge by gameScreenViewModel.challenge.collectAsState()
    val knowledgeArea by gameScreenViewModel.knowledgeArea.collectAsState()
    val lessons by gameScreenViewModel.lessons.collectAsState()

    LaunchedEffect(Unit) {
        gameScreenViewModel.initializeGameScreen()
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                naviagetTo = { navigateTo(it) },
                currentRoute = Routes.GameScreen
            )
        },
        content = { paddingValues ->
            AdaptiveFeedLayout {
                GameHeader(Modifier.padding(top = paddingValues.calculateTopPadding()))
                userStats?.let {  userStats ->
                    GameUserInformationCard(userStats)
                }
                dailyProgress?.let { daylyProgress ->
                    DailyGoalsSection(dailyProgress = daylyProgress)
                }
                ForYouSection(challenge = challenge, onChallengeStart = { navigateTo(Routes.ChallengeScreen(it))})
                KnowledgeSection(knowledgeArea)
                LessonSection(lessons = lessons)

            }
        }
    )
}
@Preview
@Composable
fun PreviewGameScreen(){
    GameScreen({}, {}, {})
}