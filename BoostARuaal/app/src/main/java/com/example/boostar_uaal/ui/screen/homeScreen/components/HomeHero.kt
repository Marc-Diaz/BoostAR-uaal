package com.example.boostar_uaal.ui.screen.homeScreen.components


// este es un componetnte reutilizable , tendre que cambiarle el nombre

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import com.example.boostar_uaal.core.components.PaginationPoints

@Composable
fun HomeHero(
    banners: List<HeroBannerData>,
    onTryArClick: () -> Unit,
    isLiked: Boolean = false,
    showlikeBotton: Boolean = false,
    onLikeClick: () -> Unit = {}
) {

    if (banners.isEmpty()) return

    val configuration = LocalConfiguration.current
    val isLandscape =
        configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(currentIndex) {
        delay(4000) // Espera 4 segundos
        currentIndex =
            (currentIndex + 1) % banners.size // Pasa al siguiente (y vuelve al 0 si es el final)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .aspectRatio(if (isLandscape) 16f / 9f else 4f / 5f)
                .clickable {
                    currentIndex = (currentIndex + 1) % banners.size
                },
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Crossfade(
                targetState = banners[currentIndex],
                animationSpec = tween(durationMillis = 800),
                label = "banner_fade"
            ) { currentBanner ->

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
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.8f)
                                    ),
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6347)),
                            shape = RoundedCornerShape(30.dp)
                        ) {
                            Text(
                                "¡Probar en AR!",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    if (showlikeBotton) {
                        IconButton(
                            onClick = onLikeClick,
                            modifier = Modifier
                                .align(Alignment.TopEnd) // Lo pega arriba a la derecha
                                .padding(16.dp) // Margen para que no toque los bordes
                                .background(
                                    Color.White.copy(alpha = 0.8f),
                                    CircleShape
                                ) // Fondo blanco semitransparente
                        ) {
                            Icon(
                                imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "Me gusta",
                                tint = if (isLiked) Color.Red else Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                }

            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        PaginationPoints(
            size = banners.size,
            currentIndex = currentIndex
        )
    }


}