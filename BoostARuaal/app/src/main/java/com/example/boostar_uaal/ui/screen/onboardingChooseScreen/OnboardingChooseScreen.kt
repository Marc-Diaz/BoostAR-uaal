package com.example.boostar_uaal.ui.screen.onboardingChooseScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.navigation.Routes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.ItemCard


@Composable
fun OnboardingChooseScreen(
    navigateTo: (Routes) -> Unit,
    back: () -> Unit,
    backTo: (Routes) -> Unit,
    viewModel: OnboardingChooseViewmodel
) {
    val state by viewModel.uiState.collectAsState()

    if (state.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        val currentStep = state.steps[state.currentStepIndex]


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp), // Márgenes generales
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 1. TOP BAR: Botón "Skip >"
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Skip >",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable {
                            // Acción de Skip: Avanzar sin validar o ir al home
                            viewModel.nextStep { navigateTo(Routes.HomeScreen) }
                        }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. TÍTULOS CON ESTILO
            Text(
                text = buildAnnotatedString {
                    val cleanTitle =
                        currentStep.title.replace("you like.", "").replace("you like", "")
                    append(cleanTitle)

                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF007BFF),
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(" you like.")
                    }
                },
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Subtítulo
            Text(
                text = "Max 2 options",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 3. GRID DE PRODUCTOS (Cuerpo Central)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .weight(1f) // IMPORTANTE: Esto hace que el grid empuje el botón hacia abajo
                    .fillMaxWidth()
            ) {
                items(currentStep.options) { product ->
                    val isSelected = state.selectedIds[currentStep.id]?.contains(product.id) == true

                    ItemCard(
                        product = product,
                        isSelected = isSelected,
                        clickable = { viewModel.toggleOption(product.id) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 4. BOTÓN CONTINUAR (Footer)
            Button(
                onClick = {
                    viewModel.nextStep {
                        // Callback onFinished: Navegar al Home
                        navigateTo(Routes.HomeScreen)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)), // Azul marca
                shape = RoundedCornerShape(50), // Bordes muy redondos
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Continue",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Espacio extra abajo por seguridad
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}