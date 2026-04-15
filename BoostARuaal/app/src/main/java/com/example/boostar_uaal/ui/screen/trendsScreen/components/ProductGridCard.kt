package com.example.boostar_uaal.ui.screen.TrendsScreen.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.utils.formatLikes
import com.example.core.entities.Product
@Composable
fun ProductGridCard(
    product: Product, // Usamos tu Data Class real
    onClick: () -> Unit
) {
    // Para que se vea variado en tu prueba, vamos a hacer que si el ID es par salga HOT y si es impar NEW
    // (O puedes ponerle a todas "NEW" si lo prefieres)
    val isHot = product.id % 2 == 0
    val tagText = if (isHot) "HOT" else "NEW"
    val tagColor = if (isHot) Color(0xFFFF5A43) else Color.Black

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        // --- CAJA DE LA IMAGEN ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 4f),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                // 1. Imagen principal desde la URL (Supabase)
                AsyncImage(
                    model = product.coverImage,
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // 2. Etiqueta Estática (NEW / HOT)
                ProductTagBadge(
                    text = tagText,
                    backgroundColor = tagColor,
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.TopStart) // Arriba a la izquierda
                )

                // 3. Logo del Partner desde la URL (Supabase)
                Box(
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.BottomEnd) // Abajo a la derecha
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(1.dp, Color(0xFFEEEEEE), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = product.partnerLogo,
                        contentDescription = "Logo ${product.partner}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // --- TEXTOS DEBAJO ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                // Título
                Text(
                    text = product.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                // Precio (Si tiene oferta, mostramos el precio de oferta, sino el normal)
                val finalPrice = product.discountPrice ?: product.price
                Text(
                    text = "${finalPrice}€",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Corazón y Likes
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Likes",
                    tint = if (product.isLiked) Color.Red else Color.LightGray, // Cambia color si está gustado
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = product.numLikes.formatLikes(),
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}