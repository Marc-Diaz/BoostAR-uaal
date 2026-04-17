package com.example.boostar_uaal.ui.screen.newPartnerScreens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.entities.PartnerData
import com.example.boostar_uaal.core.components.ProductCarrousel
import com.example.boostar_uaal.core.components.SectionHeader
import com.example.core.entities.Product

@Composable
fun PartnerSection(partner: PartnerData, products: List<Product>, onItemClick: (Int) -> Unit, onLikeClick: (Int) -> Unit, callback : (String) -> Unit){
    LaunchedEffect(Unit) {
        callback(partner.id)
    }
    Column {
        SectionHeader(
            modifier = Modifier.padding(vertical = 12.dp),
            icon = partner.logoUrl,
            title = partner.name,
            onClick = {},
            enabled = false
        )

        ProductCarrousel(
            products = products,
            onItemClick = { onItemClick(it) },
            onLikeClick = { onLikeClick(it) }
        )

    }
}