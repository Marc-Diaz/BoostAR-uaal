package com.example.boostar_uaal.ui.screen.gameScreen

import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.gameScreen.components.DaylyGoalsSection
import com.example.boostar_uaal.ui.screen.gameScreen.components.ForYouSection
import com.example.boostar_uaal.ui.screen.gameScreen.components.GameHeader
import com.example.boostar_uaal.ui.screen.gameScreen.components.GameUserInformationCard
import com.example.boostar_uaal.ui.screen.gameScreen.components.KnowleadgeSection


@Composable
fun GameScreen(navigateTo: (Routes) -> Unit, back: () -> Unit, backTo: (Routes) -> Unit){
    val gameScreenViewModel = viewModel<GameScreenViewModel>()
    val daylyProgress by gameScreenViewModel.daylyGoals.collectAsState()

    LaunchedEffect(Unit) {
        gameScreenViewModel.loadDaylyGoals()
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                naviagetTo = { navigateTo(it) },
                currentRoute = Routes.GameScreen
            )
        },
        content = { paddingValues ->
            AdaptiveFeedLayout{
                GameHeader(Modifier.padding(top = paddingValues.calculateTopPadding()))
                GameUserInformationCard()
                daylyProgress?.let { daylyProgress ->
                    DaylyGoalsSection(daylyProgress)
                }
                ForYouSection()
                KnowleadgeSection()
            }
        }
    )
}