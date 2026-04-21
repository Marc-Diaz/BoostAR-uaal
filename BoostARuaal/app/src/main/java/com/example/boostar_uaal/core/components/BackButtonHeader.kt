package com.example.boostar_uaal.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 * Encabezado estándar que incluye un botón para volver atrás y el título de la pantalla.
 *
 * Se utiliza habitualmente en la parte superior de las pantallas secundarias para
 * permitir al usuario regresar a la vista anterior.
 *
 * @param modifier Modificador base para el contenedor del encabezado. Permite ajustar el layout
 * exterior, como la adición de insets del sistema o dimensiones específicas.
 * @param title Cadena de texto que representa el título de la pantalla actual.
 * @param onBackClick Función de orden superior (lambda) que se ejecuta al detectar un evento de
 * clic sobre el icono de retroceso. Su uso principal es invocar la navegación hacia atrás
 * (ej. `popBackStack()`).
 * @param textColor Color aplicado al texto del título (por defecto Color.Black).
 * @param iconColor Color aplicado al vector del icono de retroceso (por defecto Color.Gray).
 * @param fontSize Tamaño tipográfico aplicado a la fuente del título (por defecto 24.sp).
 */
@Composable
fun BackButtonHeader(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit,
    textColor: Color = Color.Black,
    iconColor: Color = Color.Gray,
    fontSize: TextUnit = 24.sp
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "Atrás",
            tint = iconColor,
            modifier = Modifier
                .size(32.dp)
                .clickable { onBackClick() }
                .padding(end = 8.dp)
        )

        InterText(
            text = title,
            color = textColor,
            fontSize = fontSize,
            fontWeight = FontWeight.ExtraBold
        )
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewBackButtonHeader(){
    BackButtonHeader(
        title = "Titulo",
        onBackClick = {})
}