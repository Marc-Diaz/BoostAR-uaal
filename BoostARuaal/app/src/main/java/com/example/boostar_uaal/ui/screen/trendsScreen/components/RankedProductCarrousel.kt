package com.example.boostar_uaal.ui.screen.trendsScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed // IMPORTANTE: Usar itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.entities.Product

@Composable
fun RankedProductCarrousel(
    products: List<Product>,
    onItemClick: (Int) -> Unit,
    onLikeClick: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        itemsIndexed(products) { index, product ->

            RankedProductCard(
                product = product,
                rank = index + 1,
                onClick = { onItemClick(product.id) },
                onLikeClick = { onLikeClick(product.id) }
            )

        }
    }
}