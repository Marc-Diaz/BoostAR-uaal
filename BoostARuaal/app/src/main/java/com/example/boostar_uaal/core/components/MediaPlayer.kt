package com.example.boostar_uaal.core.components

import android.content.Context
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.boostar_uaal.R
import androidx.core.net.toUri
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
/**
 * Componente envoltorio para la carga asíncrona de imágenes desde la red mediante Coil.
 *
 * Este componente abstrae la complejidad de descargar y renderizar una imagen remota, gestionando
 * de forma explícita los tres estados del ciclo de vida de la carga (Cargando, Éxito y Error)
 * para ofrecer una interfaz robusta y facilitar el mantenimiento del código.

 * @param url Dirección web (HTTP/HTTPS) que apunta al archivo de la imagen.
 * @param contentDescription Texto descriptivo destinado a los servicios de accesibilidad (TalkBack).
 * @param modifier Modificador para ajustar las dimensiones, padding o comportamiento externo
 * (por defecto es un Modifier vacío).
 * @param contentScale Regla de escalado para determinar cómo se debe ajustar la imagen dentro
 * de los límites de su contenedor (por defecto `ContentScale.Fit`).
 */
@OptIn(UnstableApi::class)
@Composable
fun MediaPlayer(context: Context, raw: Int, modifier: Modifier = Modifier){

    val uri = "android.resource://${context.packageName}/${raw}".toUri()
    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(uri))
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
            prepare()
            play()
        }
    }
    DisposableEffect(Unit) {
        onDispose { player.release() }
    }
    AndroidView(
        modifier = modifier,
        factory = {
            PlayerView(context).apply {
                this.player = player
                useController = false
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            }
        }
    )
}
@OptIn(UnstableApi::class)
@Composable
fun MediaPlayer(context: Context, uri: String, modifier: Modifier = Modifier){
    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(uri))
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
            prepare()
            play()
        }
    }
    DisposableEffect(Unit) {
        onDispose { player.release() }
    }
    AndroidView(
        modifier = modifier,
        factory = {
            PlayerView(context).apply {
                this.player = player
                useController = false
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            }
        }
    )
}