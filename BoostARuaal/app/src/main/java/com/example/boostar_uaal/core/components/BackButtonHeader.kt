package com.example.boostar_uaal.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BackButtonHeader(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit,
    textColor: Color = Color.Black,
    iconColor: Color = Color.Gray,
    fontSize: TextUnit = 24.sp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "Atrás",
            tint = iconColor,
            modifier = Modifier
                .size(32.dp)
                .clickable { onBackClick() }
                .padding(end = 8.dp)
        )

        InterText(
            text = title,
            color = textColor,
            fontSize = fontSize,
            fontWeight = FontWeight.ExtraBold
        )
    }
}