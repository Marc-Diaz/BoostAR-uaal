package com.example.boostar_uaal.ui.screen.challengeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.components.PaginationPoints
import com.example.boostar_uaal.core.navigation.Routes

import com.example.boostar_uaal.ui.screen.challengeScreen.components.GameButton

@Composable
fun ChallengeScreen(challengeId: Int, navigateTo: (Routes) -> Unit, back: () -> Unit, backTo: (Routes) -> Unit) {
    val challengeScreenViewModel = viewModel<ChallengeScreenViewModel>()
    val currentStep by challengeScreenViewModel.currentChallengeStep.collectAsState()
    val challengePosition by challengeScreenViewModel.challengeStepState.collectAsState()
    val maxSteps by challengeScreenViewModel.maxSteps.collectAsState()
    val currentStepId by challengeScreenViewModel.currentStepId.collectAsState()
    val isButtonVisible by challengeScreenViewModel.isButtonVisible.collectAsState()

    LaunchedEffect(Unit) {
        challengeScreenViewModel.loadChallenge(challengeId)
        challengeScreenViewModel.loadNextStep()
    }

    Scaffold { paddingValues ->
        currentStep?.let { currentStep ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.1f),
                    contentAlignment =  if (challengePosition == ChallengeStepPositoin.START) Alignment.CenterStart else Alignment.Center
                ) {
                    if (challengePosition == ChallengeStepPositoin.START) {
                        IconButton(
                            onClick = { back() },
                            modifier = Modifier.offset(x = (-12).dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Cerrar",
                                tint = Color.Gray,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                    else{
                            PaginationPoints(
                                size = maxSteps - 1,
                                currentIndex = currentStepId - 1
                            )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.67f),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(currentStep.multimedia),
                        contentDescription = "Imagen del reto",
                        modifier = Modifier.fillMaxSize(0.8f),
                        contentScale = ContentScale.Fit
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.58f),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        modifier = Modifier.padding(top = 30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                            InterText(
                                text = AnnotatedString.fromHtml(currentStep.title?: ""),
                                textAlign = TextAlign.Center,
                                fontSize = 32.sp,
                                lineHeight = 40.sp,
                                minLines = 2,
                                maxLines = 2
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                        InterText(
                            text = AnnotatedString.fromHtml(currentStep.text),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight(400),
                            fontSize = 22.sp,
                            lineHeight = 28.sp,
                            minLines = 3,
                            maxLines = 4
                        )
                    }
                }
                GameButton(
                    modifier = Modifier.weight(0.15f),
                    isButtonVisible = isButtonVisible,
                    text = when (challengePosition) {
                        ChallengeStepPositoin.START -> "Empezar"
                        ChallengeStepPositoin.MIDDLE -> "Siguiente"
                        ChallengeStepPositoin.END -> "Comenzar Trivia"
                    },
                    onClick = {
                        if (challengePosition == ChallengeStepPositoin.END) navigateTo(
                            Routes.ChallengeTriviaScreen)
                        else challengeScreenViewModel.loadNextStep()
                    }
                )
            }
        }
    }
}
