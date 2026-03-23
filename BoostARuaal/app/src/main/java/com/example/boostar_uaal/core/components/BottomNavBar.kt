package com.example.boostar_uaal.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
    ) {
        items.forEach { navItem ->
            val isSelected = navItem.route == currentRoute
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { naviagetTo(navItem.route) }
            ) {

                Column{
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.title,
                        modifier = Modifier.fillMaxWidth(),
                        tint = if (isSelected) primaryButtonColor else Color.Black
                    )
                    InterText(
                        text = navItem.title,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 7.sp,
                        textAlign = TextAlign.Center,
                        color = if (isSelected) primaryButtonColor else Color.Black
                    )
                }
            }
            
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
@Preview
@Composable
fun NavBarPreview(){
    BottomNavBar( naviagetTo = {}, Routes.ProfileScreen)
}