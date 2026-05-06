package com.example.boostar_uaal.ui.screen.fashionScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.res.Configuration
import com.example.boostar_uaal.core.entities.BannerData
import com.example.boostar_uaal.core.theme.primaryColor
import com.example.boostar_uaal.core.theme.tertiaryColor


/**
 * Componente visual que renderiza un banner promocional estático para lanzamientos especiales (Drops).
 *
 * FUNCIONES PRINCIPALES:
 * - Diseño Adaptativo: Lee la orientación del dispositivo (`LocalConfiguration`) en tiempo
 * real para ajustar la relación de aspecto (Aspect Ratio) de la tarjeta: utiliza 16:9 para
 * modo apaisado (Landscape) y 3:4.2 para modo vertical (Portrait).
 * - Mejora de Legibilidad: Inyecta un gradiente oscuro superpuesto (Overlay) sobre la
 * parte inferior de la imagen para asegurar el contraste y la legibilidad de la tipografía
 * blanca, independientemente de la fotografía de fondo.
 * - Llamadas a la Acción (CTAs): Utiliza un contenedor en fila (`Row`) con pesos equitativos
 * (`weight(1f)`) para disponer los botones de "Probar AR" y "Reservar" de forma simétrica.
 *
 * @param banner Estructura de datos (`BannerData`) que contiene los recursos visuales (textos e imagen).
 * @param onTryArClick Acción que se dispara al pulsar el botón de Realidad Aumentada.
 * @param onReserveClick Acción que se dispara al pulsar el botón de reserva del producto.
 */
@Composable
fun DropBannerStatic(
    banner: BannerData,
    onTryArClick: () -> Unit,
    onReserveClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .aspectRatio(if (isLandscape) 16f / 9f else 3f / 4.2f),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                Image(
                    painter = painterResource(banner.imageRes),
                    contentDescription = "Banner Image",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.9f)),
                                startY = 300f
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
                        text = banner.label,
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = banner.title,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 32.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = banner.subtitle,
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Button(
                            onClick = onTryArClick,
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = tertiaryColor),
                            shape = RoundedCornerShape(30.dp)
                        ) {
                            Text(
                                text = "Probar AR",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Button(
                            onClick = onReserveClick,
                            modifier = Modifier
                                .weight(1f) // Ocupa la otra mitad del espacio
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            shape = RoundedCornerShape(30.dp)
                        ) {
                            Text(
                                text = "Reservar",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = primaryColor
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}