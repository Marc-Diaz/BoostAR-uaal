package com.example.boostar_uaal.ui.screen.gameScreen

import android.annotation.SuppressLint

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BottomNavBar
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.gameScreen.components.DailyGoalsSection
import com.example.boostar_uaal.ui.screen.gameScreen.components.ForYouSection
import com.example.boostar_uaal.ui.screen.gameScreen.components.GameHeader
import com.example.boostar_uaal.ui.screen.gameScreen.components.KnowleadgeSection

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GameScreen(navigateTo: (Routes) -> Unit, back: () -> Unit, backTo: (Routes) -> Unit){
    Scaffold(
        bottomBar = {
            BottomNavBar(
                naviagetTo = { navigateTo(it) },
                currentRoute = Routes.GameScreen
            )
        },
        content = {
            AdaptiveFeedLayout{
                GameHeader()
                DailyGoalsSection()
                ForYouSection()
                KnowleadgeSection()
            }

        }
    )
}