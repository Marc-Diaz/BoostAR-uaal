package com.example.boostar_uaal.ui.screen.fashionScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.ui.screen.TrendsScreen.components.ProductGridCard
import com.example.core.entities.Product

@Composable
fun ProductsGrid(
    products: List<Product>,
    onProductClick: (Int) -> Unit
) {
    val top4Products = products.take(4)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), // Padding exterior
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        if (top4Products.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    ProductGridCard(
                        product = top4Products[0],
                        onClick = { onProductClick(top4Products[0].id) }
                    )
                }
                if (top4Products.size > 1) {
                    Box(modifier = Modifier.weight(1f)) {
                        ProductGridCard(
                            product = top4Products[1],
                            onClick = { onProductClick(top4Products[1].id) }
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
        if (top4Products.size > 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    ProductGridCard(
                        product = top4Products[2],
                        onClick = { onProductClick(top4Products[2].id) }
                    )
                }
                if (top4Products.size > 3) {
                    Box(modifier = Modifier.weight(1f)) {
                        ProductGridCard(
                            product = top4Products[3],
                            onClick = { onProductClick(top4Products[3].id) }
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}