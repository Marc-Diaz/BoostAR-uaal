package com.example.boostar_uaal.ui.screen.forYouScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.entities.Product

@Composable
fun CleanItemCarrousel(
    products: List<Product>,
    onItemClick: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Espacio entre fotos
        contentPadding = PaddingValues(horizontal = 24.dp) // Margen a los lados de la pantalla
    ) {
        items(products) { product ->

            // Recordando tu código anterior, para sacar la imagen de la BBDD usabas algo como esto:
            // val imagen = product.multimedia[0].multimediaURL

            CleanItemCard(
                product= product,
                onClick = { onItemClick(product.id) }
            )
        }
    }
}