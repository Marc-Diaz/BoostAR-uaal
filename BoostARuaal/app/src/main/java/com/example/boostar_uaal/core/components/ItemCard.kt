package com.example.boostar_uaal.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.data.models.Product

@Composable
fun ItemCard(product: Product, clickable: () -> Unit) {
    val imagen = product.multimedia.first { m -> m.isPrincipal }.multimediaURL
    Card(modifier = Modifier.size(150.dp).clickable{ clickable() }) {

        //Image()
    }
}