package com.example.boostar_uaal.ui.screen.licenseScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.boostar_uaal.core.entities.License
import com.example.boostar_uaal.core.components.ProductCarrousel
import com.example.core.entities.Product

@Composable
fun LicenseSection(license: License, products: List<Product>, onItemClick: (Int) -> Unit, onLikeClick: (Int) -> Unit, callback : (Int) -> Unit){
    LaunchedEffect(Unit) {
        callback(license.id)
    }
    Column() {
        LicenseTitle(
            license = license,
            enabled = false
        )

        ProductCarrousel(
            products = products,
            onItemClick = { onItemClick(it) },
            onLikeClick = { onLikeClick(it) }
        )

    }
}