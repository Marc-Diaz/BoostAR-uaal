package com.example.boostar_uaal.ui.screen.challengeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.core.theme.primaryColor
import com.example.boostar_uaal.ui.screen.challengeScreen.components.PointsLabel

@Composable
fun ChallengePointScreen(backTo: (Routes) -> Unit){
   val challengeScreenViewModel = viewModel<ChallengeScreenViewModel>()
    LaunchedEffect(Unit) {
        challengeScreenViewModel.scheduleCallback(
            delayMs = 3000,
            callback = {
                backTo(Routes.GameScreen)
            }
        )
    }
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 81.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            InterText(
                text = "Felicidades",
                fontSize = 22.sp,
                color = primaryColor,

                )
            InterText(
                text = "Has obtenido:",
                fontSize = 20.sp,
                color = Color(0xFF6E6E6E)
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PointsLabel(
                icon = R.drawable.game_icon,
                text = "+250 XP",
                color = Color.Green
            )
            PointsLabel(
                icon = R.drawable.boostar_logo,
                text = "+180 pts",
                color = primaryColor
            )
        }

    }
}
@Preview(showBackground = true)
@Composable
fun PreviewChallengePointScreen(){
    ChallengePointScreen(backTo = {})
}