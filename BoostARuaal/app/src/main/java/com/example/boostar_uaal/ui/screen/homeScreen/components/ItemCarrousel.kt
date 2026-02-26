package com.example.boostar_uaal.ui.screen.homeScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
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


/*@Composable
fun ItemCarrousel(name: String, products: List<Product>, iconTint: Color = primaryButtonColor, navigateTo: () -> Unit, itemClickable: (Int) -> Unit){
    Row(
        modifier = Modifier
            .clickable(
                onClick = navigateTo
            )
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        InterText(
            text = name,
            textAlign = TextAlign.Left,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = "Arrow Icon",
            tint = primaryButtonColor,
        )

    }
    LazyRow(
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products){ product ->
            ItemCard(product, { itemClickable(product.id) })
        }
    }
}*/

@Composable
fun ItemCarrousel(
    title: String, // Ej: "Para ti >" o "Novedades."
    products: List<Product>,
    iconTint: Color = primaryButtonColor,
    onHeaderClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    onLikeClick: (Int) -> Unit
) {
    // Lógica inteligente de títulos
    val isClickable = title.endsWith(">")
    val cleanTitle = title.removeSuffix(">").removeSuffix(".").trim()

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // --- CABECERA ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                // Solo es clickeable si tiene el ">"
                .then(if (isClickable) Modifier.clickable { onHeaderClick() } else Modifier),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            InterText(
                text = cleanTitle,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )

            // Solo pintamos la flecha si es navegable
            if (isClickable) {
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                    contentDescription = "Ver más",
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // LISTA DE PRODUCTOS
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
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