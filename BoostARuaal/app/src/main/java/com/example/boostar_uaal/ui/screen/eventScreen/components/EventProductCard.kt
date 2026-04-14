package com.example.boostar_uaal.ui.screen.eventScreen.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.components.ItemImage

@Composable
fun EventProductCard(modifier: Modifier = Modifier, productImage: String){
    Surface(
        modifier = modifier
            .aspectRatio(0.75f),
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        ItemImage(
            url =  productImage,
            contentDescription = "Imagen del producto",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}