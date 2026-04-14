package com.example.boostar_uaal.ui.screen.challengeScreen


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.theme.primaryColor
import com.example.boostar_uaal.ui.screen.challengeScreen.components.AnswerOptionCard


@Composable
fun ChallengeTriviaScreen() {
    //val challengeScreenViewModel = viewModel<ChallengeScreenViewModel>()
    //val currentQuestion by challengeScreenViewModel.currentQuestion.collectAsState()
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InterText(
                text = "Desafío",
                color = primaryColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            InterText(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append("Escoge la opción ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = primaryColor,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("correcta")
                    }
                },
                fontSize = 16.sp
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color(0xFFEEEEEE)), // Borde muy sutil
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp, horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Pregunta
                    InterText(
                        text = "¿Cuál de estas opciones es una\npaleta de colores neutra?",
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        color = Color(0xFF333333)
                    )

                    Spacer(modifier = Modifier.height(32.dp))
                    /*
                    // --- Tarjetas de Respuesta ---
                    palettes.forEachIndexed { index, colors ->
                        AnswerOptionCard(
                            colors = colors,
                            isSelected = selectedOptionIndex == index,
                            onClick = {
                                selectedOptionIndex = index
                            } // Actualiza el estado al hacer clic
                        )

                        // Espacio entre tarjetas, excepto después de la última
                        if (index < palettes.size - 1) {
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                     */
                    }
                }
            }
        }

    }
@Preview
@Composable
fun PreviewChallengeTriviaScreen(){
    ChallengeTriviaScreen()
}