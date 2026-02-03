package com.example.boostar_uaal.ui.screen.baseScreen

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.boostar_uaal.navigation.Routes

data class NavigationItems(
    val label: String,
    val icon: ImageVector,
    val route: Routes,
    val index: Int
)
