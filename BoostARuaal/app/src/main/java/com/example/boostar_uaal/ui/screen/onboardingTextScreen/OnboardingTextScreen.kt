package com.example.boostar_uaal.ui.screen.onboardingTextScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun OnboardingTextScreen(navigateToChoose: () -> Unit, viewModel: OnboardingTextViewmodel = viewModel())
{


    val uiState by viewModel.uiState.collectAsState()

    // Escuchamos el evento de navegación final
    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { shouldNavigate ->
            if (shouldNavigate) {
                navigateToChoose()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        // Cambiamos el contenido según el estado
        when (uiState) {
            is OnboardingState.Step1 -> {
                TypewriterText(
                    baseText = "Now, tell me",
                    highlightText = "your style.",
                    fontSize = 24.sp
                )
            }
            is OnboardingState.Step2 -> {
                TypewriterText(
                    baseText = "",
                    highlightText = "¡Let's go!",
                    fontSize = 32.sp
                )
            }
        }
    }
}