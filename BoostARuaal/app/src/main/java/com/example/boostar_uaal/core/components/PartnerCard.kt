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
        // La imagen centrada dentro del círculo
        Image(
            painter = painterResource(id = partner.logoRes),
            contentDescription = partner.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp), // Un poco de margen interno para que el logo no choque con los bordes
            contentScale = ContentScale.Fit // Fit hace que el logo entero se vea sin cortarse
        )
    }
}

@Composable
fun PartnerCarousel(
    partners: List<PartnerData>,
    onItemClick: (Int) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 24.dp), // Margen a los lados de la pantalla
        horizontalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre círculo y círculo
    ) {
        items(partners) { partner ->
            PartnerCard(
                partner = partner,
                onClick = { onItemClick(partner.id) }
            )
        }
    }
}