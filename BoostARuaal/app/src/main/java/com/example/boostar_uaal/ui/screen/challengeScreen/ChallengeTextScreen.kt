package com.example.boostar_uaal.ui.screen.challengeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.core.theme.primaryColor

@Composable
fun ChallengeTextScreen(navigateTo: (Routes) -> Unit){

    val challengeScreenViewModel = viewModel<ChallengeScreenViewModel>()
    val totalPoints by challengeScreenViewModel.totalCorrectAnswers.collectAsState()

    LaunchedEffect(Unit) {
        challengeScreenViewModel.startTimer(
            time = 1500,
            callback = {
                navigateTo(Routes.ChallengePointScreen)
            }
        )
    }

    AdaptiveFeedLayout(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        InterText(
            text = if (totalPoints == 0) "¡FATAL!" else "¡GENIAL!",
            fontWeight = FontWeight.Bold,
            fontSize = 47.sp,
            color = if (totalPoints == 0) Color.Red else primaryColor
        )
    }
}
@Preview
@Composable
fun PreviewChallengeTextScreen(){
    ChallengeTextScreen(navigateTo = {})
}