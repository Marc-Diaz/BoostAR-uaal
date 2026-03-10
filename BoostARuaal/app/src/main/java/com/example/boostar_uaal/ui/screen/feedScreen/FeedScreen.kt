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
import com.example.boostar_uaal.core.components.AdaptiveFeedLayout
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.ui.screen.feedScreen.components.FeedItem


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

    LaunchedEffect(key1 = productId, key2 = products) {
        // Solo intentamos hacer scroll si la lista ya terminó de cargar
        if (products.isNotEmpty()) {

            // Buscamos en qué número de página está realmente este ID
            // Nota: Asegúrate de cambiar 'it.id' por el nombre real de la propiedad id en tu modelo
            val pageIndex = products.indexOfFirst { it.id == productId }

            // Si lo encuentra (es diferente a -1), nos movemos a esa página
            if (pageIndex != -1) {
                pagerState.scrollToPage(pageIndex)
            }
        }
    }

    // 4. LA INTERFAZ
    if (products.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.White)
        }
    } else {
        VerticalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val currentProduct = products[page]

            FeedItem(
                product = currentProduct,
                onPartnerClick = { },
                onShareClick = { },
                onCarClick = { },
                onDetailsClick = { },
                onTryArClick = { },
                onQuickPayClick = { }
            )
        }
    }

}


