package com.example.boostar_uaal.ui.screen.TrendsScreen.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.ProductCard
import com.example.boostar_uaal.core.entities.Partner
import com.example.boostar_uaal.core.theme.secondaryColor
import com.example.boostar_uaal.core.theme.tertiaryColor
import com.example.boostar_uaal.core.utils.formatLikes
import com.example.core.entities.Product
@Composable
fun ProductGridCard(
    product: Product,
    onClick: () -> Unit,
    onLikeClick: () -> Unit
) {
    Box(){
        ProductCard(
            product = product,
            clickable = { onClick() },
            onLikeClick = { onLikeClick() }
        )
        ProductTagBadge(
            text = "HOT",
            backgroundColor = tertiaryColor,
            modifier = Modifier
        )
    }
}
@Preview
@Composable
fun PreviewProductGridCard(){
    val product = Product(
        id = 1,
        name = "Camiseta estampado flores",
        price = 30.99,
        discountPrice = 19.99,
        coverImage = "",
        numLikes = 4349,
        partner = Partner(
            id = "j54lj3o434u",
            name = "michelle",
            logoUrl = ""
        ),
        partnerLogo = "",
        isLiked = true
    )
    ProductGridCard(
        product = product,
        onClick = { },
        onLikeClick = { }
    )
}