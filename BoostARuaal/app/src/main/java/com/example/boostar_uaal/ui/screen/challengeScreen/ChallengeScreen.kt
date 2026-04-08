package com.example.boostar_uaal.ui.screen.challengeScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.core.theme.primaryColor
import com.example.boostar_uaal.core.theme.secondaryButtonColor

@Composable
fun ChallengeScreen(challengeId: Int, navigateTo: (Routes) -> Unit, back: () -> Unit, backTo: (Routes) -> Unit){
    val challengeScreenViewModel = viewModel<ChallengeScreenViewModel>()
    val currentStep by challengeScreenViewModel.currentChallengeStep.collectAsState()
    val challengePosition by challengeScreenViewModel.challengeStepState.collectAsState()


    LaunchedEffect(Unit) {
        challengeScreenViewModel.loadChallenge(challengeId)
        challengeScreenViewModel.loadNextStep()
    }

    Scaffold { paddingValues ->
        currentStep?.let { currentStep ->
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(currentStep.multimedia),
                    contentDescription = "Color wheel",
                    modifier = Modifier.fillMaxWidth()
                )
                currentStep.title?.let {
                    InterText(
                        text = it,
                        textAlign = TextAlign.Center,
                        fontSize = 33.sp
                    )
                }
                InterText(
                    text = currentStep.text,
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = {
                        if (challengePosition == ChallengeStepPositoin.END) back()
                        else challengeScreenViewModel.loadNextStep()
                              },
                    modifier = Modifier.size(width = 323.dp, height = 57.dp),
                    content = {
                            InterText(
                                text = when(challengePosition){
                                    ChallengeStepPositoin.START -> "Empezar"
                                    ChallengeStepPositoin.MIDDLE -> "Siguiente"
                                    ChallengeStepPositoin.END -> "Finalizar"
                                },
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 18.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Black,
                                textAlign = TextAlign.Center
                            )
                    },
                    colors = ButtonColors(
                        containerColor = primaryColor,
                        contentColor = secondaryButtonColor,
                        disabledContainerColor = primaryColor,
                        disabledContentColor = secondaryButtonColor
                    ),
                    shape = CircleShape,
                    border = BorderStroke(
                        width = 2.dp,
                        brush = SolidColor(primaryColor)
                    )
                )
            }

        }
    }
}
