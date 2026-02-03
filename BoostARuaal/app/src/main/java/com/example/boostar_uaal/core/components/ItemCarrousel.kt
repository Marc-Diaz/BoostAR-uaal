package com.example.boostar_uaal.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable

fun ItemCarrousel(name: String, products: List<Product>, navigateTo: () -> Unit, itemClickable: (Int) -> Unit){
    HorizontalDivider(thickness = 2.dp)
    Text(
        text = name,
        modifier = Modifier.clickable(
            onClick = navigateTo
        ).fillMaxWidth(),
        style = TextStyle(
            textAlign = TextAlign.Left,
            fontSize = 24.sp
        )
    )
    LazyRow() {
        items(products){ product ->
            ItemCard(product, {itemClickable(product.id)})
        }
    }
}