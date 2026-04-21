package com.example.boostar_uaal.ui.screen.forYouScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.components.ItemImage
import com.example.core.entities.Product

@Composable
fun CleanProductCard(
    product : Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .height(180.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        ItemImage(
            url = product.coverImage,
            contentDescription = "Imagen del producto",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
@Preview
@Composable
fun PreviewCleanProductCard(){
    val product = Product(
        id = 1,
        name = "Producto",
        price = 20.00,
        discountPrice = 10.00,
        coverImage = "",
        numLikes = 13213,
        partner = "P&B",
        partnerLogo = "",
        isLiked = false
    )
    CleanProductCard(
        product = product,
        onClick = { }
    )
}