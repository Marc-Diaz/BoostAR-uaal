package com.example.boostar_uaal.ui.screen.ParaTiScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun OnstyleChoosen(
    imageRes: Int,
    clickable: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(height = 220.dp, width = 160.dp)
            .clickable{ clickable() }
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    width = 3.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(16.dp)
                ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = "Chica Tejano",
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxSize()
            )

        }
    }
}