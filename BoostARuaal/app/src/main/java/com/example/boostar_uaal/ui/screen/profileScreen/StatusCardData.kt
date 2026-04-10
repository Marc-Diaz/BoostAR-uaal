package com.example.boostar_uaal.ui.screen.profileScreen

import androidx.compose.ui.graphics.Color


data class StatusCardData(
    val title: String,
    val icon: Int,
    val color: Color,
    val count: Int,
    val showNotificationDot: Boolean = false
)