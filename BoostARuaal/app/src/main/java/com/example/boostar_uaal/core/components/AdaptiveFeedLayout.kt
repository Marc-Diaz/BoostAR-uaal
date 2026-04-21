package com.example.boostar_uaal.core.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Contenedor principal adaptativo para las pantallas de la aplicación.
 *
 * Este componente sirve como base para cualquier pantalla que requiera desplazamiento vertical.
 * Implementa las directrices de Material Design 3 para pantallas grandes mediante la restricción
 * de su ancho máximo (`widthIn(max = 840.dp)`). Esto asegura que en dispositivos móviles el contenido
 * ocupe el 100% del ancho disponible, mientras que en tablets o formato apaisado se mantenga
 * centrado y con proporciones legibles, evitando la distorsión de los elementos visuales.
 *
 * @param modifier Modificador base para el contenedor exterior. Útil para aplicar insets del sistema.
 * @param backgroundColor Color de fondo aplicado a la pantalla completa (por defecto Color.White).
 * @param verticalArrangement Define la separación vertical entre los componentes hijos. El valor por
 * defecto establece un espaciado estandarizado de 24.dp para mantener la consistencia del diseño.
 * @param horizontalAlignment Alineación horizontal de los componentes internos (por defecto Alignment.Start).
 * @param content Bloque composable que define la interfaz de usuario. Al ejecutarse dentro del contexto
 * de un `ColumnScope`, permite el uso de modificadores específicos como `weight()` en los componentes hijos.
 */
@Composable
fun AdaptiveFeedLayout(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(24.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .widthIn(max = 840.dp)
                .verticalScroll(scrollState)
                .padding(bottom = 100.dp),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            content = content
        )
    }
}