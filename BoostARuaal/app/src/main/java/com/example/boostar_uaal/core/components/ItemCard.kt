package com.example.boostar_uaal.core.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.theme.secondaryTextColor

import com.example.boostar_uaal.data.repository.MockProductRepositoryImpl
import com.example.core.entities.Product

@Composable
fun ItemCard(product: Product, clickable: () -> Unit) {
    Column(
        modifier = Modifier.clickable{ clickable() }
        ) {
        Card(
            modifier =
            Modifier
                .size(height = 220.dp, width = 160.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            ItemImage(
                url = product.coverImage,
                contentDescription = product.name
            )
        }
        InterText(
            text = product.name,
        )
        InterText(
            text = "${product.price}€",
            color = secondaryTextColor
        )
    }
}
@Preview(showBackground = true)
@Composable
fun ItemCardPreview(){
    ItemCard(
        product = MockProductRepositoryImpl().getMockProducts().first(),
        clickable = { }
    )
}