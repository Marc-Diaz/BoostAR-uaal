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

@Composable
fun DropBannerStatic(
    banner: BannerData, // Usa tu data class existente
    onTryArClick: () -> Unit, // Acción para el botón naranja
    onReserveClick: () -> Unit // Acción para el botón blanco
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    // Colores sacados de tu diseño
    val coralColor = Color(0xFFFF5A43)
    val blueColor = Color(0xFF007AFF)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                // Hacemos el aspect ratio un pelín más vertical (por ejemplo 3/4) si quieres que se vea más como en tu foto
                .aspectRatio(if (isLandscape) 16f / 9f else 3f / 4.2f),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                // Imagen de fondo
                Image(
                    painter = painterResource(banner.imageRes),
                    contentDescription = "Banner Image",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                )

                // Gradiente oscuro para que se lea el texto
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.9f)),
                                startY = 300f // Ajustado un poco más abajo para que la cara de la modelo se vea clara
                            )
                        )
                )

                // Textos y botones
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = banner.label, // "DROP DEL MOMENTO"
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = banner.title, // "Acaba de llegar al mundo de la moda."
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 32.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = banner.subtitle, // "Nuevas zapatillas Ari Jordun..."
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // 🌟 LA MAGIA DE LOS DOS BOTONES 🌟
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp) // Espacio entre los botones
                    ) {
                        // Botón Izquierdo: Probar AR
                        Button(
                            onClick = onTryArClick,
                            modifier = Modifier
                                .weight(1f) // Ocupa la mitad del espacio
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = coralColor),
                            shape = RoundedCornerShape(30.dp)
                        ) {
                            Text(
                                text = "Probar AR",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        // Botón Derecho: Reservar
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
                                color = blueColor // Letras azules
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}