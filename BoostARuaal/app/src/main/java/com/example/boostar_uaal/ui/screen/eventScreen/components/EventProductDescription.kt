package com.example.boostar_uaal.ui.screen.eventScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.InterText

@Composable
fun EventProductDescription(modifier: Modifier = Modifier, productDescription: String){
    Column(modifier = modifier) {
        InterText(
            text = AnnotatedString.fromHtml(productDescription),
            fontSize = 11.sp,
            lineHeight = 18.sp,
            maxLines = 12,
            fontWeight = FontWeight(400),
            color = Color(0xFF6E6E6E),
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}