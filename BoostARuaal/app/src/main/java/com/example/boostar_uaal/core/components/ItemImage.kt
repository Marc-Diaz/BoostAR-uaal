package com.example.boostar_uaal.core.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.utils.shimmer

/**
 * Componente envoltorio para la carga asíncrona de imágenes desde la red mediante Coil.
 *
 * Este componente abstrae la complejidad de descargar y renderizar una imagen remota, gestionando
 * de forma explícita los tres estados del ciclo de vida de la carga (Cargando, Éxito y Error)
 * para ofrecer una interfaz robusta y facilitar el mantenimiento del código.
 *
 * @param url Dirección web (HTTP/HTTPS) que apunta al archivo de la imagen.
 * @param contentDescription Texto descriptivo destinado a los servicios de accesibilidad (TalkBack).
 * @param modifier Modificador para ajustar las dimensiones, padding o comportamiento externo
 * (por defecto es un Modifier vacío).
 * @param contentScale Regla de escalado para determinar cómo se debe ajustar la imagen dentro
 * de los límites de su contenedor (por defecto `ContentScale.Fit`).
 */
@Composable
fun ItemImage(url: String, contentDescription: String, modifier: Modifier = Modifier, contentScale: ContentScale = ContentScale.Fit){
    val painter = rememberAsyncImagePainter(model = url)
    val state by painter.state.collectAsState()
    when(state){
        is AsyncImagePainter.State.Empty,
        is AsyncImagePainter.State.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shimmer()
            )
        }
        is AsyncImagePainter.State.Success ->
            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier = modifier,
                contentScale = contentScale

            )
        is AsyncImagePainter.State.Error -> {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            val error = state as AsyncImagePainter.State.Error
            Log.d("ERROR IMAGEN", "$error")
        }
    }
}