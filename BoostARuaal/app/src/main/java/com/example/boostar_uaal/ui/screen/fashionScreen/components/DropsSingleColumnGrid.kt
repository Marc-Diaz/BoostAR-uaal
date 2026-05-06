package com.example.boostar_uaal.ui.screen.fashionScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.components.DropCard
import com.example.boostar_uaal.core.entities.Drop
/**
 * Contenedor vertical que renderiza una lista de próximos lanzamientos (Drops) en una sola columna.
 *
 * CONTEXTO TÉCNICO Y DE RENDIMIENTO:
 * Este componente utiliza un `Column` estándar iterando sobre los elementos a través de un `forEach`.
 * Esto significa que renderiza todos los elementos de la lista en memoria de forma simultánea, sin
 * importar si están visibles en la pantalla del usuario o no.
 *
 * @param drops Lista de objetos `Drop` que contienen la información y estado de los lanzamientos.
 * @param onReserveClick Callback que se ejecuta al pulsar el botón de reserva, pasando el objeto `Drop` específico seleccionado.
 * @param onBellClick Callback que se activa al interactuar con el icono de la campana para fijar un recordatorio, pasando el `Drop` asociado.
 */
@Composable
fun DropsSingleColumnGrid(
    drops: List<Drop>,
    onReserveClick: (Drop) -> Unit,
    onBellClick: (Drop) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        drops.forEach { drop ->
            DropCard(
                drop = drop,
                onReserveClick = { onReserveClick(drop) },
                onBellClick = { onBellClick(drop) }
            )
        }
    }
}