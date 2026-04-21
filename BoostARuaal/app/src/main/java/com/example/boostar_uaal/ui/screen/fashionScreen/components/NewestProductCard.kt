package com.example.boostar_uaal.ui.screen.fashionScreen.components
import androidx.compose.foundation.layout.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.components.ProductCard
import com.example.boostar_uaal.core.theme.tertiaryColor
import com.example.core.entities.Product
@Composable
fun NewestProductCard(
    product: Product,
    onClick: () -> Unit,
    onLikeClick: () -> Unit
) {
    Box{
        ProductCard(
            product = product,
            clickable = { onClick() },
            onLikeClick = { onLikeClick() }
        )
        TrendingProductTag(
            text = "NEW",
            backgroundColor = Color.Black,
            modifier = Modifier.padding(10.dp)
        )
    }
}
@Preview
@Composable
fun PreviewNewestProductCard(){
    val product = Product(
        id = 1,
        name = "Camiseta estampado flores",
        price = 30.99,
        discountPrice = 19.99,
        coverImage = "",
        numLikes = 4349,
        partner = "Bazar sarria",
        partnerLogo = "",
        isLiked = true
    )
    NewestProductCard(
        product = product,
        onClick = { },
        onLikeClick = { }
    )
}