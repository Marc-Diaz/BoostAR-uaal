package com.example.boostar_uaal.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.navigation.NavItem
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.core.theme.primaryButtonColor

@Composable
fun BottomNavBar(naviagetTo: (Routes) -> Unit, currentRoute: Routes){
    val items = listOf(
        NavItem.Inicio,
        NavItem.Retos,
        NavItem.Feeds,
        NavItem.Cesta,
        NavItem.Perfil
    )
    BottomAppBar(
        modifier = Modifier
            .height(80.dp),
        containerColor = Color.White) {
        items.forEach { navItem ->
            val isSelected = navItem.route == currentRoute
            val navIcon = if (isSelected) navItem.selectedIcon else navItem.icon
            NavigationBarItem(
                selected = isSelected,
                icon = {
                    Icon(
                        painter = painterResource(navIcon),
                        contentDescription = navItem.title,
                        modifier = Modifier.height(32.dp),
                        tint = Color.Unspecified
                    )
                },
                label = {
                    InterText(
                        text = navItem.title,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        color = if (isSelected) primaryButtonColor else Color.Black,
                        fontWeight = FontWeight.ExtraBold

                    )
                },
                onClick = { naviagetTo(navItem.route) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = primaryButtonColor,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = primaryButtonColor,
                    unselectedTextColor = Color.Black
                )
            )
        }
    }
}
@Preview
@Composable
fun NavBarPreview(){
    BottomNavBar( naviagetTo = {}, Routes.ProfileScreen)
}