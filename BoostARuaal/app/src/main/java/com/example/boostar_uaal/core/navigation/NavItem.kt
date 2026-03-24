package com.example.boostar_uaal.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.boostar_uaal.R
import com.example.boostar_uaal.data.models.SortOrder

sealed class NavItem(val route: Routes, val title: String, val icon: Int, val selectedIcon: Int){
    object Inicio : NavItem(Routes.HomeScreen, "Inicio", R.drawable.home_icon, R.drawable.home_icon_active)
    object Retos : NavItem(Routes.GameScreen, "Retos", R.drawable.game_icon, R.drawable.game_icon_active)
    object Feeds : NavItem(Routes.FeedScreen(sortOrder = SortOrder.FORYOU), "Feeds", R.drawable.feed_icon, R.drawable.feed_icon_active)
    object Cesta : NavItem(Routes.BasketScreen, "Cesta", R.drawable.basket_icon, R.drawable.basket_icon_active)
    object Perfil : NavItem(Routes.ProfileScreen, "Perfil", R.drawable.profile_icon, R.drawable.profile_icon_active)
}