package com.example.boostar_uaal.core.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeHeader() {
    Row(Modifier.fillMaxWidth()
        .padding(top = 45.dp)
        .padding(horizontal = 24.dp)) {
        InterText(
            text = "Home",
            fontSize = 38.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}