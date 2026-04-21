package com.example.boostar_uaal.core.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
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


/**
 * Muestra una imagen de fondo descargada desde una URL, ocupando la totalidad de el espacio disponible.
 *
 * Este componente gestiona automáticamente los diferentes estados de la descarga usando Coil:
 * - Mientras carga: Muestra un indicador circular de progreso.
 * - Si tiene éxito: Muestra la imagen recortada para rellenar completamente el espacio (`ContentScale.Crop`).
 * - Si hay un error: Muestra una imagen por defecto de la aplicación para evitar que el fondo quede en blanco.
 *
 * @param url Enlace directo a la imagen que se quiere descargar.
 * @param contentDescription Texto descriptivo de la imagen para herramientas de accesibilidad (lectores de pantalla).
 */
@Composable
fun BackgroundImage(url: String, contentDescription: String){
    val painter = rememberAsyncImagePainter(model = url)
    val state by painter.state.collectAsState()
    when(state){
        is AsyncImagePainter.State.Empty,
        is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
        is AsyncImagePainter.State.Success ->
            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop

            )
        is AsyncImagePainter.State.Error -> {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}