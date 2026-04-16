package com.example.boostar_uaal.ui.screen.newPartnerScreens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import com.example.boostar_uaal.core.entities.PartnerData
import com.example.boostar_uaal.ui.screen.homeScreen.components.ItemCarrousel
import com.example.core.entities.Product

@Composable
fun PartnerSection(partner: PartnerData, products: List<Product>, onItemClick: (Int) -> Unit, onLikeClick: (Int) -> Unit, callback : (String) -> Unit){
    LaunchedEffect(Unit) {
        callback(partner.id)
    }
    Column() {
        PartnerTitle(
            partner = partner,
            enabled = false
        )

        ItemCarrousel(
            products = products,
            onItemClick = { onItemClick(it) },
            onLikeClick = { onLikeClick(it) }
        )

    }
}