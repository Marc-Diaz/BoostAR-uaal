package com.example.boostar_uaal.ui.screen.homeScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.delay

/*@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeHero(
    banners: List<HeroBannerData>, // Recibimos la lista de banners
    onTryArClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    // 1. EL ESTADO DEL CARRUSEL: Sabe cuántas páginas hay y en cuál estamos
    val pagerState = rememberPagerState(pageCount = { banners.size })

    // 2. LA MAGIA DEL AUTO-SCROLL
    LaunchedEffect(pagerState) {
        while (true) {
            delay(4000) // Espera 4 segundos
            // Calcula cuál es la siguiente página (y si llega al final, vuelve a la 0)
            val nextPage = (pagerState.currentPage + 1) % banners.size
            pagerState.animateScrollToPage(nextPage) // Mueve el carrusel con animación suave
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- EL CARRUSEL ---
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp), // Margen a los lados
            pageSpacing = 8.dp // Espacio entre una foto y la otra
        ) { page ->

            val currentBanner = banners[page]

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(if (isLandscape) 16f / 9f else 4f / 5f),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Imagen de fondo
                    Image(
                        painter = painterResource(currentBanner.imageRes),
                        contentDescription = "Hero Image",
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter,
                        modifier = Modifier.fillMaxSize()
                    )

                    // Degradado oscuro para que el texto se lea bien
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                                    startY = 200f
                                )
                            )
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = currentBanner.label,
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = currentBanner.title,
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 32.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = currentBanner.subtitle,
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = onTryArClick,
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6347)),
                            shape = RoundedCornerShape(30.dp)
                        ) {
                            Text("¡Probar en AR!", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // --- LOS PUNTITOS INDICADORES (DOTS) ---
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(banners.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color.LightGray else Color.LightGray.copy(alpha = 0.4f)
                val size = if (pagerState.currentPage == iteration) 8.dp else 6.dp // El punto activo es un pelín más grande

                Box(
                    modifier = Modifier
                        .size(size)
                        .background(color, CircleShape)
                )
            }
        }
    }
}*/

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.runtime.*


@Composable
fun HomeHero(
    banners: List<HeroBannerData>,
    onTryArClick: () -> Unit
) {
    // Si la lista está vacía por algún motivo, no pintamos nada para evitar errores
    if (banners.isEmpty()) return

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    // 1. EL ESTADO: ¿Qué número de banner estamos viendo?
    var currentIndex by remember { mutableStateOf(0) }

    // 2. EL TEMPORIZADOR AUTO-CAMBIO
    // Cada vez que 'currentIndex' cambia, este reloj vuelve a empezar a contar 4 segundos
    LaunchedEffect(currentIndex) {
        delay(4000) // Espera 4 segundos
        currentIndex = (currentIndex + 1) % banners.size // Pasa al siguiente (y vuelve al 0 si es el final)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- EL BANNER FIJO CON TOQUE ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .aspectRatio(if (isLandscape) 16f / 9f else 4f / 5f)
                // 👇 MAGIA: Si tocas la tarjeta, cambia al siguiente banner al instante
                .clickable {
                    currentIndex = (currentIndex + 1) % banners.size
                },
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            // 👇 MAGIA 2: El Crossfade hace que el cambio de imagen sea suave (fundido)
            Crossfade(
                targetState = banners[currentIndex],
                animationSpec = tween(durationMillis = 800), // El fundido dura casi un segundo (queda muy premium)
                label = "banner_fade"
            ) { currentBanner ->

                // Todo lo de dentro es tu diseño original, ahora atado al "currentBanner"
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(currentBanner.imageRes),
                        contentDescription = "Hero Image",
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter,
                        modifier = Modifier.fillMaxSize()
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                                    startY = 200f
                                )
                            )
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = currentBanner.label,
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = currentBanner.title,
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 32.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = currentBanner.subtitle,
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        // OJO: Como este botón está DENTRO de la tarjeta clickeable,
                        // si el usuario toca el botón, hace la acción de AR.
                        // Si toca fuera del botón, cambia el banner. ¡Es perfecto!
                        Button(
                            onClick = onTryArClick,
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6347)),
                            shape = RoundedCornerShape(30.dp)
                        ) {
                            Text("¡Probar en AR!", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // --- LOS PUNTITOS INDICADORES ---
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(banners.size) { iteration ->
                // Comparamos con el currentIndex para saber cuál pintar de gris oscuro
                val color = if (currentIndex == iteration) Color.LightGray else Color.LightGray.copy(alpha = 0.4f)
                val size = if (currentIndex == iteration) 8.dp else 6.dp

                Box(
                    modifier = Modifier
                        .size(size)
                        .background(color, CircleShape)
                )
            }
        }
    }
}