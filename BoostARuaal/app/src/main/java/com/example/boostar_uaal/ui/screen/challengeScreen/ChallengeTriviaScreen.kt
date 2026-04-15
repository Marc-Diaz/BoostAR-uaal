package com.example.boostar_uaal.ui.screen.challengeScreen


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.core.theme.primaryColor
import com.example.boostar_uaal.ui.screen.challengeScreen.components.AnswerOptionCard
import com.example.boostar_uaal.ui.screen.challengeScreen.components.GameButton


@Composable
fun ChallengeTriviaScreen(navigateTo: (Routes) -> Unit) {
    val challengeScreenViewModel: ChallengeScreenViewModel = viewModel<ChallengeScreenViewModel>()
    val currentQuestion by challengeScreenViewModel.currentQuestion.collectAsState()
    val selectedAnswerId by challengeScreenViewModel.selectedAnswerId.collectAsState()
    val isButtonVisible by challengeScreenViewModel.isButtonVisible.collectAsState()
    val challengeState by challengeScreenViewModel.challengeStepState.collectAsState()
    LaunchedEffect(Unit) {
        challengeScreenViewModel.loadNextQuestion()
    }
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                InterText(
                    text = "Desafío",
                    color = primaryColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 56.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                InterText(
                    text = AnnotatedString.fromHtml("Escoge la opción <span style=\"color:#007AFF\">correcta</span>"),
                    fontSize = 16.sp
                )
            }
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp, horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InterText(
                        text = currentQuestion?.question ?: "",
                        textAlign = TextAlign.Center,
                        fontSize = 13.sp,
                        color = Color(0xFF333333)
                    )
                    currentQuestion?.let { currentQuestion ->
                        currentQuestion.answers.forEachIndexed { index, answer ->
                            AnswerOptionCard(
                                answer = answer,
                                isSelected = selectedAnswerId == index,
                                onClick = { challengeScreenViewModel.selectAnswer(index)}
                            )
                        }

                    }
                }
            }
            GameButton(
                text = if (challengeState != ChallengeStepPositoin.END) "Continuar" else "Finalizar",
                onClick = {
                    if (challengeState != ChallengeStepPositoin.END){
                        challengeScreenViewModel.submitAnswer()
                        challengeScreenViewModel.loadNextQuestion()
                    }
                    else {
                        navigateTo(Routes.ChallengeTextScreen)
                    }
                },
                isButtonVisible =  isButtonVisible
            )

        }
    }
}
@Preview
@Composable
fun PreviewChallengeTriviaScreen(){
    val viemodel = viewModel<ChallengeScreenViewModel>()
    ChallengeTriviaScreen({})
}