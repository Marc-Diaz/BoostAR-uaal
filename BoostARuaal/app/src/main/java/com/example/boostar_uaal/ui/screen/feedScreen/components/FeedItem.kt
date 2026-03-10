package com.example.boostar_uaal.ui.screen.feedScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.components.BackgorundImage
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.core.entities.Product

@Composable
fun FeedItem(
    product: ProductDetail,
    onPartnerClick: () -> Unit,
    onShareClick: () -> Unit,
    onCarClick: () -> Unit,
    onDetailsClick: () -> Unit,
    onTryArClick: () -> Unit,
    onQuickPayClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // 1. CAPA DE FONDO: IMAGEN DEL PRODUCTO

        BackgorundImage(
            url = product.multimedia[0].multimediaURL,
            contentDescription = product.name
        )

        // 2. CAPA DE SOMBRA (GRADIENTE)
        // Vital para que los textos blancos se lean bien sobre el pantalón
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                        startY = 0.5f // Empieza a oscurecer a mitad de la pantalla
                    )
                )
        )

        // 3. CAPA SUPERIOR (Top Bar: Partner y Compartir)

        BottomPartnerSearch (
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 48.dp, start = 16.dp, end = 16.dp),
            onPartnerClick = onPartnerClick,
            onShareClick = onShareClick
        )

        // 4. CAPA LATERAL DERECHA (Like y Carrito)

        RightSideBar(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 140.dp, end = 16.dp), // Lo subimos para no chocar con el Dock inferior
            isLiked = product.isLiked,
            onCarClick = onCarClick
        )

        // 5. CAPA INFERIOR (Textos + Botones de acción)
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            // Textos: Nombre y Precio
            ProductInfoSection(
                modifier = Modifier.padding(horizontal = 24.dp),
                name = product.name,
                price = product.price,
                discountPrice = product.discountPrice
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Los 3 botones principales de abajo
            // Sustituye "BottomActionDock" por el tuyo
            BottomActionDock(
                modifier = Modifier.fillMaxWidth(),
                onDetailsClick = onDetailsClick, // Botón blanco
                onTryArClick = onTryArClick,     // Botón rojo/naranja
                onQuickPayClick = onQuickPayClick // Botón amarillo
            )
        }
    }
}