package com.example.boostar_uaal.ui.screen.homeScreen.components

import androidx.compose.foundation.Image
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.boostar_uaal.R

@Composable
fun HomeHero(){
    Card() {
        Image(
            painter = painterResource(R.drawable.home),
            contentDescription = "Home Hero",
            contentScale = ContentScale.Crop
        )
    }
}