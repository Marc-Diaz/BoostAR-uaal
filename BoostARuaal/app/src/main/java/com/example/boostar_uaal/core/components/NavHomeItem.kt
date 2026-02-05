package com.example.boostar_uaal.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import com.example.boostar_uaal.navigation.Routes

@Composable
fun NavHomeItem(name: String, color: Color, routes: Routes){
    Card(
        shape = CircleShape,
        modifier = Modifier.width(150.dp),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Blue,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.Blue
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        InterText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            text = name,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = color

        )
    }
}