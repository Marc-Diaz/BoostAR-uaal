package com.example.boostar_uaal.core.components


// Puede que alos otros composables tengaque hacerles lo mismo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.entities.PartnerData

@Composable
fun PartnerCard(
    partner: PartnerData,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(157.dp)
            .clickable { onClick() },
        shape = CircleShape,
        color = Color(0xFFD9D9D9),
        shadowElevation = 6.dp // Sombra suave
    ) {
        ItemImage(
            url =partner.logoUrl,
            contentDescription = partner.name,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun PartnerCarousel(
    partners: List<PartnerData>,
    onItemClick: (String) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(partners) { partner ->
            PartnerCard(
                partner = partner,
                onClick = { onItemClick(partner.id) }
            )
        }
    }
}