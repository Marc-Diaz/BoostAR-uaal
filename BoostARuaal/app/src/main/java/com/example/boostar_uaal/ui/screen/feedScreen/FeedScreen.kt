package com.example.boostar_uaal.ui.screen.feedScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.boostar_uaal.core.entities.TypeMultimedia
import com.example.boostar_uaal.core.navigation.Routes
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.ui.screen.feedScreen.components.BottomActionDock
import com.example.boostar_uaal.ui.screen.feedScreen.components.ProductInfoSection
import com.example.boostar_uaal.ui.screen.feedScreen.components.RightSideBar
import com.example.boostar_uaal.core.components.SearchButton

// Colores extraídos aproximados de la imagen
val BrandRed = Color(0xFFFF5247)
val BrandYellow = Color(0xFFFFD600)
val PriceRed = Color(0xFFFF1744)

@Composable
fun FeedScreen(
    productId: Int,
    navigateTo: (Routes) -> Unit,
    back: () -> Unit,
    backTo: (Routes) -> Unit,
    viewModel: FeedScreenViewModel = viewModel()
) {
    val product by viewModel.product.collectAsState()

    LaunchedEffect(productId) {
        viewModel.getProduct(productId)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Fondo por si la imagen tarda
    ) {

        if (product != null) {
            val p = product!!

            // Selección de imagen: Prioriza multimedia principal -> coverImage
            val imageUrl =
                p.multimedia.find { it.isPrincipal && it.type == TypeMultimedia.IMAGE }?.multimediaURL
                    ?: p.coverImage

            // 1. IMAGEN DE FONDO
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = p.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // 2. GRADIENTE (Sombra para legibilidad)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                            startY = 0.5f
                        )
                    )
            )

            // 3. CAPA DE INTERFAZ (UI)

            // Botón Atrás (Top Start)
            IconButton(
                onClick = back,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 48.dp, start = 16.dp)
                    .background(Color.Black.copy(alpha = 0.3f), CircleShape)
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás",
                    tint = Color.White
                )
            }

            // Botón Buscar (Top End)
            SearchButton(
                modifier = Modifier.align(Alignment.TopEnd),

                )

            // Barra Lateral Derecha (Likes, Share, Perfil)
            RightSideBar(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 50.dp)
                    .padding(end = 16.dp),      // Margen derecho
                likes = p.numLikes,
                onProfileClick = {}
            )

            // Info del Producto (Nombre, Precio)
            ProductInfoSection(
                modifier = Modifier.align(Alignment.BottomStart),
                name = p.name,
                price = p.price,
                discountPrice = p.discountPrice
            )

            // Dock de Acciones (Carrito, Comprar)
            BottomActionDock(
                modifier = Modifier.align(Alignment.BottomCenter),
                onCartClick = { /* navigateTo(Routes.Cart) */ },
                onBuyClick = { /* navigateTo(Routes.Checkout(p.id)) */ }
            )

        } else {
            // Loading Spinner
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}




