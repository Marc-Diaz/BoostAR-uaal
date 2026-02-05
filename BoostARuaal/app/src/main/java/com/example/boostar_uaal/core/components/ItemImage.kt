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

@Composable
fun ItemImage(url: String, contentDescription: String){
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
            val error = state as AsyncImagePainter.State.Error
            Log.d("ERROR IMAGEN", "$error")
        }
    }
}