package com.example.boostar_uaal.core.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.core.entities.Product
@Composable
fun ProductCarrousel(
    products: List<Product>,
    onItemClick: (Int) -> Unit,
    onLikeClick: (Int) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            if (!products.isEmpty())
                items(products) { product ->
                    ProductCard(
                        product = product,
                        clickable = { onItemClick(product.id) },
                        onLikeClick = { onLikeClick(product.id) }
                    )
                }
            else{
                items(10) {
                    BlankProductCard()
                }
            }

        }
    }
}