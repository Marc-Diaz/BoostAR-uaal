package com.example.boostar_uaal.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.entities.PartnerData


/**
 * Carrusel horizontal deslizable para mostrar una colección de partners.
 *
 * Este componente agrupa múltiples `PartnerCard` en una fila navegable. Es la solución ideal
 * para mostrar listas largas de elementos de izquierda a derecha sin saturar la pantalla principal.
 *
 * @param partners Lista de objetos `PartnerData` obtenidos de la base de datos o ViewModel.
 * @param onItemClick Acción que se ejecuta al seleccionar una marca. Este callback emite el
 * identificador único (`String`) del partner pulsado para que el componente padre decida qué
 * hacer con él (por ejemplo, navegar a la pantalla de detalle de ese partner).
 */
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