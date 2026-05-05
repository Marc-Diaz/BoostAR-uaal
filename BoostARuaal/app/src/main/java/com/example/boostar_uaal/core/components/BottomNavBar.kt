package com.example.boostar_uaal.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.navigation.NavItem
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.core.theme.primaryColor

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
        modifier = Modifier,
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
                    if (!navItem.enablad){
                        BlockedIcon(
                            modifier = Modifier.size(20.dp).offset(x = 20.dp, y = (-5).dp)
                        )
                    }
                },
                label = {
                    InterText(
                        text = navItem.title,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        color = if (isSelected) primaryColor else Color.Black,
                        fontWeight = FontWeight.ExtraBold

                    )
                },
                enabled = navItem.enablad,
                onClick = {  naviagetTo(navItem.route) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = primaryColor,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = primaryColor,
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