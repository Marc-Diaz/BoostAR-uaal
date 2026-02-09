package com.example.boostar_uaal.ui.screen.feedScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun BottomActionDock(
    modifier: Modifier = Modifier,
    onCartClick: () -> Unit,
    onBuyClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 24.dp) // 1. Margen a los lados de la pantalla
            .fillMaxWidth() //
            .height(80.dp), // Un poco más alto para que respire
        shape = RoundedCornerShape(40.dp), // Borde muy redondo (cápsula)
        color = Color.Transparent,
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp), // 3. Espacio interno para que los botones no toquen el borde blanco
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // 4. CLAVE: Esto separa los iconos (Izquierda - Centro - Derecha)
        ) {
            // --- BOTÓN CARRITO (Izquierda) ---
            IconButton(
                onClick = onCartClick,
                modifier = Modifier
                    .size(52.dp)
                    //  .border(1.dp, Color.White, CircleShape)
                    .background(Color.White, CircleShape)

            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "Carrito",
                    tint = Color.Blue, // Azul como en la foto
                    modifier = Modifier.size(28.dp)
                )
            }

            // --- BOTÓN PERCHA CENTRAL (Centro - Más ancho) ---
            Button(
                onClick = onBuyClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5247)), // Rojo marca
                shape = RoundedCornerShape(30.dp), // Bordes redondeados pero no círculo
                modifier = Modifier
                    .height(56.dp)
                    .width(140.dp) // 5. Ancho fijo grande para que destaque
                    .padding(horizontal = 8.dp) // Un pequeño margen extra por seguridad
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Detalles",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }

            // --- BOTÓN PAGO RÁPIDO (Derecha) ---
            IconButton(
                onClick = { /* Acción pago */ },
                modifier = Modifier
                    .size(52.dp)
                    .background(Color(0xFFFFD600), CircleShape) // Amarillo fondo
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send, // O Icons.Default.MonetizationOn
                    contentDescription = "Pago Rápido",
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}