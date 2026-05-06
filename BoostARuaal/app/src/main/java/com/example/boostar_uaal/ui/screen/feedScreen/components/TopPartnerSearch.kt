package com.example.boostar_uaal.ui.screen.feedScreen.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.ItemImage
import com.example.boostar_uaal.core.components.PaginationPoints
import com.example.boostar_uaal.core.entities.Partner

/**
 * Componente visual para la barra superior (Top Bar) que muestra la marca asociada
 * al producto actual y un acceso rápido para compartir el contenido.
 *
 * FUNCIONES PRINCIPALES:
 * - Navegación de Marca: Muestra el logotipo de la marca (`Partner`) incrustado en un botón circular. Al pulsarlo, permite al usuario navegar hacia el perfil o catálogo de dicho partner.
 * - Acción de Compartir: Incluye un botón circular secundario dedicado a la acción de compartir el elemento actual con otros usuarios o aplicaciones externas.
 * - Disposición Espacial: Utiliza `Arrangement.SpaceBetween` para anclar el logotipo en el extremo izquierdo y el botón de compartir en el extremo derecho, creando un overlay limpio y equilibrado sobre el contenido visual subyacente.
 *
 * @param modifier Modificador base aplicado al contenedor principal (la fila horizontal).
 * @param partner Objeto de datos que contiene la información de la marca, incluyendo la URL de su logotipo (`logoUrl`) para renderizar la imagen.
 * @param onShareClick Callback que se ejecuta cuando el usuario pulsa el icono de compartir.
 * @param onPartnerClick Callback que se dispara al interactuar con el logotipo, utilizado habitualmente para redirigir a la colección de la marca.
 */
@Composable
fun TopPartnerSearch(modifier: Modifier = Modifier, partner: Partner, onShareClick: () -> Unit, onPartnerClick: () -> Unit) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(top = 25.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        IconButton(
            onClick = { onPartnerClick()},
            modifier = Modifier
                .size(40.dp)
                .background(Color.White,CircleShape)
                .padding(0.dp)
        ) {
            ItemImage(
                url =partner.logoUrl,
                contentDescription = partner.name,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
        IconButton(
            onClick ={ onShareClick() },
            modifier = Modifier
                .size(32.dp)
                .background(Color.White, CircleShape)
        ) {

            Icon(
                painter = painterResource(R.drawable.share_icon),
                contentDescription = "compartir",
                tint = Color.Black,
                modifier = Modifier.size(25.dp)
            )

        }
    }
}