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

@Composable
fun AdaptiveFeedLayout(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    content: @Composable ColumnScope.() -> Unit // El espacio para el contenido
) {
    val scrollState = rememberScrollState()

    // El universo que ocupa toda la pantalla (móvil, tablet o monitor de 50 pulgadas)
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.TopCenter // Centra el contenido horizontalmente
    ) {
        // La columna que restringe el ancho para que no se deforme en pantallas gigantes
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .widthIn(max = 840.dp) //El secreto de la adaptabilidad
                .verticalScroll(scrollState)
                .padding(bottom = 100.dp), // Espacio para la futura BottomBar
            verticalArrangement = Arrangement.spacedBy(24.dp), // Separación uniforme entre secciones
            content = content
        )
    }
}