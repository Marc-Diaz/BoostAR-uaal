package com.example.boostar_uaal.ui.screen.fashionScreen.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.boostar_uaal.core.components.BlankProductCard
import com.example.core.entities.Product

@Composable
fun NewestProductGrid(products: List<Product>, onClick: (Int) -> Unit, onLikeClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    FlowRow(
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
        maxItemsInEachRow = 2,
        modifier = modifier.fillMaxWidth()
    ) {
        if (products.isEmpty()) {
            repeat(4) {
                BlankProductCard(
                    modifier = Modifier.weight(1f)
                )
            }
        } else {
            products.forEach { product ->
                NewestProductCard(
                    product = product,
                    onClick = { onClick(product.id) },
                    onLikeClick = { onLikeClick(product.id) }
                )
            }
        }
    }
}