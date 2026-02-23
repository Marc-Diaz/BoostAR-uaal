package com.example.boostar_uaal.ui.screen.onboardingChooseScreen.components

import com.example.core.entities.Product

data class OnboardingStep(
    val id: String,
    val title: String,
    val subtitle: String = "Max 2 options",
    val options: List<Product>
)