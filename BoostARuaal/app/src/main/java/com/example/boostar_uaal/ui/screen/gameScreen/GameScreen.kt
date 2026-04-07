package com.example.boostar_uaal.ui.screen.gameScreen

import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.gameScreen.components.DailyGoalsSection
import com.example.boostar_uaal.ui.screen.gameScreen.components.ForYouSection
import com.example.boostar_uaal.ui.screen.gameScreen.components.GameHeader
import com.example.boostar_uaal.ui.screen.gameScreen.components.GameUserInformationCard
import com.example.boostar_uaal.ui.screen.gameScreen.components.KnowleadgeSection


@Composable
fun GameScreen(navigateTo: (Routes) -> Unit, back: () -> Unit, backTo: (Routes) -> Unit){
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
                DailyGoalsSection()
                ForYouSection()
                KnowleadgeSection()
            }

        }
    )
}