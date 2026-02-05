package com.example.boostar_uaal.core.entities

import androidx.compose.ui.graphics.Color
import com.example.boostar_uaal.core.theme.primaryButtonColor
import com.example.boostar_uaal.navigation.Routes

data class NavHomeItemData (
    val name: String,
    val color: Color = primaryButtonColor,
    val routes: Routes
)