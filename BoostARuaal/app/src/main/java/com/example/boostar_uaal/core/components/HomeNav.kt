package com.example.boostar_uaal.core.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.entities.NavHomeItemData
import com.example.boostar_uaal.core.theme.discountColor
import com.example.boostar_uaal.navigation.Routes


@Composable
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

}