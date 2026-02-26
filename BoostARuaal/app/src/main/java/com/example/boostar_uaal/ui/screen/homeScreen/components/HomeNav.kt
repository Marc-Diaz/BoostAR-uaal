package com.example.boostar_uaal.ui.screen.homeScreen.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.entities.NavHomeItemData
import com.example.boostar_uaal.core.theme.discountColor
import com.example.boostar_uaal.core.navigation.Routes


/*@Composable
fun HomeNav(){
    val navHomeItem = listOf<NavHomeItemData>(
        NavHomeItemData(
            name = "Para ti",
            routes = Routes.HomeScreen
        ),
        NavHomeItemData(
            name = "Tendencias",
            routes = Routes.HomeScreen
        ),
        NavHomeItemData(
            name = "Novedades",
            routes = Routes.HomeScreen
        ),
        NavHomeItemData(
            name = "Colaboraciones",
            routes = Routes.HomeScreen
        ),
        NavHomeItemData(
            name = "Nuevas Marcas",
            routes = Routes.HomeScreen
        ),
        NavHomeItemData(
            name = "Eventos",
            routes = Routes.HomeScreen
        ),
        NavHomeItemData(
            name = "Rebajas",
            color = discountColor,
            routes = Routes.HomeScreen
        )
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(navHomeItem){
            NavHomeItem(
                name = it.name,
                color = it.color,
                routes = it.routes
            )
        }
    }

}*/


import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
// Tus otros imports...

// Creamos la lista fuera del Composable para no saturar la memoria
val homeNavItemsList = listOf(
    NavHomeItemData(name = "Para ti", routes = Routes.HomeScreen),
    NavHomeItemData(name = "Tendencias", routes = Routes.HomeScreen),
    NavHomeItemData(name = "Novedades", routes = Routes.HomeScreen),
    NavHomeItemData(name = "Colaboraciones", routes = Routes.HomeScreen),
    NavHomeItemData(name = "Nuevas Marcas", routes = Routes.HomeScreen),
    NavHomeItemData(name = "Eventos", routes = Routes.HomeScreen),
    // Solo a Rebajas le forzamos un color distinto
    NavHomeItemData(name = "Rebajas", routes = Routes.HomeScreen, color = discountColor)
)

@Composable
fun HomeNav(
    items: List<NavHomeItemData> = homeNavItemsList,
    onItemClick: (Routes) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(items) { item ->
            NavHomeItem(
                name = item.name,
                color = item.color, // Aquí siempre habrá un color (el rojo o el azul por defecto)
                onClick = { onItemClick(item.routes) }
            )
        }
    }
}