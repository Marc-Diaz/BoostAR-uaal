package com.example.boostar_uaal.core.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import com.example.boostar_uaal.R
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.theme.secondaryTextColor

import com.example.core.entities.Product

@Composable
fun ItemCard(
    product: Product,
    clickable: () -> Unit,
    onLikeClick: () -> Unit = {}, // 👈 Le añadimos la acción del corazón (con valor por defecto para no romper otras pantallas)
    isSelected: Boolean = false
) {
    Column(
        modifier = Modifier
            .width(160.dp) // Fijamos el ancho para que no se deforme el carrusel
            .clickable { clickable() }
    ) {

        // --- 1. LA IMAGEN Y EL LOGO DE LA MARCA ---
        Box(
            modifier = Modifier.size(height = 220.dp, width = 160.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(16.dp), // Bordes redondeados como en tu diseño
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                ItemImage(
                    url = product.coverImage,
                    contentDescription = product.name
                )
            }

            // 👇 El logo de PB flotando en la esquina inferior derecha
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .size(28.dp),
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 2.dp
            ) {
                // Aquí usamos tu logo estático por ahora.
                // En el futuro puedes poner: product.brandLogoRes
                Image(
                    painter = painterResource(id = R.drawable.boostar_logo),
                    contentDescription = "Marca",
                    modifier = Modifier.padding(4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween, // Manda los textos a la izq y el corazón a la detra
            verticalAlignment = Alignment.Top
        ) {
            // Textos (Nombre y Precio)
            Column(modifier = Modifier.weight(1f)) {
                InterText(
                    text = product.name,
                    fontWeight = FontWeight.SemiBold,
                )
                InterText(
                    text = "${product.price}€",
                    color = secondaryTextColor
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            // Botón de Corazón y Contador
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { onLikeClick() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite ,
                    contentDescription = "Like",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
                InterText(
                    text = product.numLikes.toString(), // Ej: "4,5k"
                    fontSize = 11.sp,
                    color = Color.Black
                )
            }
        }
    }
}