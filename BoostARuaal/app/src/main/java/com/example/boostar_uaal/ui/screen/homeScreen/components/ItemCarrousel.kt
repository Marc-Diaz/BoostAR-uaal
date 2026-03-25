package com.example.boostar_uaal.ui.screen.homeScreen.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.components.ItemCard
import com.example.boostar_uaal.core.theme.primaryButtonColor
import com.example.core.entities.Product
@Composable
fun ItemCarrousel(
    products: List<Product>,
    onItemClick: (Int) -> Unit,
    onLikeClick: (Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(products) { product ->
                ItemCard(
                    product,
                    clickable = { onItemClick(product.id) },
                    onLikeClick = { onLikeClick(product.id) })
            }
        }
    }
}