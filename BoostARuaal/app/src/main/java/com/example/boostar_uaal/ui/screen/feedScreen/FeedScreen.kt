package com.example.boostar_uaal.ui.screen.feedScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.boostar_uaal.core.navigation.Routes


@Composable

fun FeedScreen(

    productId: Int,

    navigateTo: (Routes) -> Unit,

    back: () -> Unit,

    backTo: (Routes) -> Unit,

    viewModel: FeedScreenViewModel

) {
    // 1. Observamos la lista del ViewModel
    val products by viewModel.products.collectAsState()
    val context = LocalContext.current

    // 2. Inicializamos el feed con el productId al entrar a la pantalla
    // Usamos Unit como key para que solo se ejecute la primera vez que se compone
    LaunchedEffect(Unit) {
        viewModel.initializeFeed(productId)
        Log.d("PRODUCTOS", "$products")
    }

    val pagerState = rememberPagerState(pageCount = { products.size })

    LaunchedEffect(pagerState.currentPage) {
        if (products.isNotEmpty()) {
            // Si nos acercamos al final, cargamos hacia ABAJO
            if (pagerState.currentPage >= products.size - 2) {
                viewModel.loadNextPage()
            }
            // Si subimos cerca del inicio, cargamos hacia ARRIBA
            if (pagerState.currentPage <= 1) {
                viewModel.loadPrevPage()
            }
        }

    }

    // 5. Interfaz Gráfica
    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {

        if (products.isEmpty()) {
            // Pantalla de carga inicial
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )
        } else {
            // Feed vertical estilo TikTok
            VerticalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val currentProduct = products[page]

                Box(modifier = Modifier.fillMaxSize()) {

                    // --- IMAGEN DEL PRODUCTO (COIL) ---
                    // Asegúrate de cambiar 'imageUrl' por el nombre real de tu propiedad en ProductDetail
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(currentProduct.coverImage)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Producto ${currentProduct.id}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // --- GRADIENTE SUPERPUESTO (Opcional, para que el texto se lea mejor) ---
                    // Aquí podrías añadir un gradiente negro semitransparente en la parte inferior

                    // --- INFORMACIÓN Y BOTONES DEL LADO DERECHO / INFERIOR ---
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                            .padding(bottom = 60.dp), // Espacio para la barra de navegación del sistema
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Botón de AR
                        Button(
                            onClick = { viewModel.onTryArClick(context, currentProduct) },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text("Probar AR")
                        }

                        // Ejemplo: Si quieres ir a los detalles del producto usando tu 'navigateTo'
                        // Spacer(modifier = Modifier.height(16.dp))
                        // IconButton(onClick = { navigateTo(Routes.ProductDetailRoute(currentProduct.id)) }) { ... }
                    }
                }
            }
        }

        // --- BOTÓN DE ATRÁS FLOTANTE (Capa superior) ---
        // Lo ponemos fuera del Pager para que siempre esté fijo en la pantalla
        IconButton(
            onClick = back,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 40.dp, start = 16.dp) // Respetar la barra de estado superior
                .background(Color.Black.copy(alpha = 0.5f), shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = Color.White
            )
        }
    }
}


