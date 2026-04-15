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
fun RankedItemCarrousel(
    products: List<Product>,
    onItemClick: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        itemsIndexed(products) { index, product ->

            GiantRankedItemCard(
                imageUrl = product.coverImage,
                rank = index + 1,
                productName = product.name,
                price = "${product.price}€",
                likes = "${product.numLikes}",
                onClick = { onItemClick(product.id) }
            )

        }
    }
}