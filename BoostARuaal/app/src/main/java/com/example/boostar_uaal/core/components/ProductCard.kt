package com.example.boostar_uaal.core.components


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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.LocalAuthState
import com.example.boostar_uaal.core.theme.discountColor
import com.example.boostar_uaal.core.theme.secondaryColor
import com.example.boostar_uaal.core.utils.AuthState
import com.example.boostar_uaal.core.utils.formatLikes

import com.example.core.entities.Product


/**
 * Tarjeta individual para mostrar un producto del catálogo.
 *
 * Este componente renderiza la información fundamental de un artículo, incluyendo
 * su imagen principal, el logotipo de la marca colaboradora, el precio y un control
 * interactivo para marcarlo como favorito.
 *
 * @param modifier Modificador base aplicado al contenedor superior (`Column`).
 * @param product Instancia del modelo de datos que contiene las propiedades a renderizar.
 * @param clickable Callback ejecutado al realizar una pulsación en el área general de la tarjeta.
 * @param onLikeClick Callback específico ejecutado al interactuar con el indicador de favoritos
 * (solo se invocará si el usuario actual cumple las condiciones de validación de estado).
 */
@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    clickable: () -> Unit,
    onLikeClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .width(160.dp)
            .clickable { clickable() }
    ) {
        Box(
            modifier = Modifier.size(height = 220.dp, width = 160.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                ItemImage(
                    url = product.coverImage,
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Surface(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .size(28.dp),
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 2.dp
            ) {
                ItemImage(
                    url = product.partnerLogo,
                    contentDescription = product.partner,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                InterText(
                    text = product.name,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.height(24.dp).padding(end = 4.dp),

                )
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)){
                    product.discountPrice?.let {
                        InterText(
                            text = "${product.discountPrice}€",
                            color = discountColor,
                            fontSize = 12.sp
                        )
                    }
                    InterText(
                        text = "${product.price}€",
                        color = secondaryColor,
                        fontSize = 12.sp,
                        textDecoration = if(product.discountPrice != null) TextDecoration.LineThrough else TextDecoration.None
                    )

                }
            }

            Spacer(modifier = Modifier.width(4.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable(
                        interactionSource = null,
                        indication = null,
                        enabled = LocalAuthState.current is AuthState.Authenticated,
                        onClick = { onLikeClick() },

                    )
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Like",
                    tint = if(product.isLiked)  Color.Red else Color.Black,
                    modifier = Modifier.size(20.dp)
                )
                InterText(
                    text = product.numLikes.formatLikes(),
                    fontSize = 11.sp,
                    color = Color.Black
                )
            }
        }
    }
}