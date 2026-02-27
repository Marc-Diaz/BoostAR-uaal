package com.example.boostar_uaal.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.ui.screen.homeScreen.components.CollabData

@Composable
fun CollabCard(
    collab: CollabData,
    onClick: () -> Unit
) {
    // Usamos EXACTAMENTE los valores de Figma
    Box(
        modifier = Modifier
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000),
                shape = RoundedCornerShape(21.dp) // Añadimos el shape a la sombra para que no sea cuadrada
            )
            .width(157.dp)
            .height(221.dp)
            .background(color = Color(0xFF1E1E1E), shape = RoundedCornerShape(21.dp))
            .clip(RoundedCornerShape(21.dp)) // Corta lo que sobresalga (como la imagen)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        // 1. Imagen de fondo
        Image(
            painter = painterResource(id = collab.backgroundImageRes),
            contentDescription = "Fondo colaboración",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 2. Filtro oscuro para que el texto resalte
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )

        // 3. Los textos y logos centrales
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Marca de arriba (Texto)
            Text(
                text = collab.brandTop,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // La "X" fina del medio
            Text(
                text = "✕", // Símbolo de cruz fino
                color = Color.White.copy(alpha = 0.7f), // Un pelín transparente
                fontSize = 24.sp,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Marca de abajo (Logo imagen)
            Image(
                painter = painterResource(id = collab.brandBottomRes),
                contentDescription = "Logo marca",
                modifier = Modifier
                    .width(100.dp) // Ajusta este tamaño según logos
                    .height(40.dp),
                contentScale = ContentScale.Fit // Para que no se deforme el logo
            )
        }
    }
}