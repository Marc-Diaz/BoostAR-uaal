package com.example.boostar_uaal.ui.screen.onboardingChooseScreen.components

data class OnboardingUiState(
    val isLoading: Boolean = true,
    val steps: List<OnboardingStep> = emptyList(),
    val currentStepIndex: Int = 0,
    val selectedIds: Map<String, List<Int>> = emptyMap() // Clave: id del paso ("tops"), Valor: lista de IDs de productos
)