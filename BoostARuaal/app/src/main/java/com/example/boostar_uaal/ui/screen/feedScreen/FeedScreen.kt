package com.example.boostar_uaal.ui.screen.feedScreen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.entities.TypeMultimedia
import com.example.boostar_uaal.core.navigation.Routes
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.components.BackgorundImage
import com.example.boostar_uaal.ui.screen.feedScreen.components.BottomActionDock
import com.example.boostar_uaal.ui.screen.feedScreen.components.ProductInfoSection
import com.example.boostar_uaal.ui.screen.feedScreen.components.RightSideBar
import com.example.boostar_uaal.core.components.SearchButton
import com.example.boostar_uaal.ui.screen.feedScreen.components.FeedItem


/*@Composable

fun FeedScreen(

    productId: Int,

    navigateTo: (Routes) -> Unit,

    back: () -> Unit,

    backTo: (Routes) -> Unit,

    viewModel: FeedScreenViewModel = viewModel()

) {

    val product by viewModel.product.collectAsState()

    Log.d("Producto", "$product")

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

            val imageUrl = p.multimedia.find { it.type == TypeMultimedia.IMAGE }?.multimediaURL ?: p.coverImage


// 1. IMAGEN DE FONDO

            BackgorundImage(imageUrl, "")


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

// Botón Buscar (Top End)

            SearchButton(

                modifier = Modifier.align(Alignment.TopEnd),

                )


// Barra Lateral Derecha (Likes, Share, Perfil)

            RightSideBar(

                modifier = Modifier

                    .align(Alignment.BottomEnd)

                    .padding(bottom = 50.dp)

                    .padding(end = 16.dp), // Margen derecho

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

}*/


import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeedScreen(
    startProductId: Int,
    navigateTo: (Routes) -> Unit,
    back: () -> Unit
) {
    val viewModel: FeedScreenViewModel = viewModel()
    val feedList  by viewModel.feedState.collectAsState()
    val context = LocalContext.current

    // 2. Buscamos en qué posición está la prenda que tocaste para empezar ahí
    val initialPage = remember(feedList) {
        val index = feedList.indexOfFirst { it.id == startProductId }
        if (index >= 0) index else 0
    }

    // 3. El estado del Scroll (Pager)
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { feedList.size }
    )

    // Sincronización extra por si la lista tarda en cargar de internet
    LaunchedEffect(feedList) {
        if (feedList.isNotEmpty()) {
            val index = feedList.indexOfFirst { it.id == startProductId }
            if (index >= 0) pagerState.scrollToPage(index)
        }
    }

    // 4. Tu carcasa adaptativa
    AdaptiveFeedLayout(backgroundColor = Color.Black) {

        if (feedList.isEmpty()) {
            // Pantalla de carga mientras el ViewModel trae la lista
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.White)
            }
        } else {
            // EL MOTOR TIKTOK
            VerticalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                // Sacamos la prenda que toca dibujar en esta página
                val currentProduct = feedList[page]

                // Llamamos a tu componente visual pasándole sus datos y conectando los botones
                FeedItem(
                    product = currentProduct,
                    onPartnerClick = { viewModel.onPartnerClick(currentProduct.id) },
                    onShareClick = { viewModel.onShareClick(currentProduct.id) },
                    onCarClick = { viewModel.onCarClick(currentProduct.id) },
                    onDetailsClick = { viewModel.onDetailsClick(currentProduct.id) },
                    onTryArClick = {},

                    onQuickPayClick = { viewModel.onQuickPayClick(currentProduct.id) }
                )
            }
        }
    }
}