package com.example.boostar_uaal.core.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.boostar_uaal.R
import androidx.core.net.toUri

@Composable
fun MediaPlayer(context: Context, raw: Int){
    val uri = "android.resource://${context.packageName}/${raw}".toUri()
    val player = ExoPlayer.Builder(context).build().apply {
        setMediaItem(MediaItem.fromUri(uri))
        prepare()

        play()
    }
    DisposableEffect(Unit) {
        onDispose { player.release() }
    }
    AndroidView(
        factory = {
            PlayerView(context).apply {
                this.player = player
            }
        }
    )
}