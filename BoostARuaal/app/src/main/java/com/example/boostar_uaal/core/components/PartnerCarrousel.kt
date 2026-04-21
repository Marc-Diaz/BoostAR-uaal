package com.example.boostar_uaal.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.entities.PartnerData

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