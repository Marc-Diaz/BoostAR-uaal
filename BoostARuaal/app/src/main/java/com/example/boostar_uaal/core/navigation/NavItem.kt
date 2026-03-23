package com.example.boostar_uaal.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.boostar_uaal.data.models.SortOrder

sealed class NavItem(val route: Routes, val title: String, val icon: ImageVector){
    object Inicio : NavItem(Routes.HomeScreen, "Inicio", Icons.Filled.Home)
    object Retos : NavItem(Routes.GameScreen, "Retos", Icons.Filled.Info)
    object Feeds : NavItem(Routes.FeedScreen(sortOrder = SortOrder.FORYOU), "Feeds", Icons.Filled.Face)
    object Cesta : NavItem(Routes.BasketScreen, "Cesta", Icons.Filled.ShoppingCart)
    object Perfil : NavItem(Routes.ProfileScreen, "Perfil", Icons.Filled.Person)
}