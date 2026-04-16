package com.example.boostar_uaal.ui.screen.homeScreen.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.entities.NavHomeItemData
import com.example.boostar_uaal.core.theme.discountColor
import com.example.boostar_uaal.core.navigation.Routes


val homeNavItemsList = listOf(
    NavHomeItemData(name = "Para ti", routes = Routes.ForYouScreen),
    NavHomeItemData(name = "Tendencias", routes = Routes.TrendsScreen),
    NavHomeItemData(name = "Novedades", routes = Routes.FashionNewsScreen),
    NavHomeItemData(name = "Colaboraciones", routes = Routes.LicenseScreen),
    NavHomeItemData(name = "Nuevas Marcas", routes = Routes.NewPartnerScreen),
    NavHomeItemData(name = "Eventos", routes = Routes.EventScreen),
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
                color = item.color,
                onClick = { onItemClick(item.routes) }
            )
        }
    }
}