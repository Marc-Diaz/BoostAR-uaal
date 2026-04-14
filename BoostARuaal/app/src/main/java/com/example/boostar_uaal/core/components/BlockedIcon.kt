package com.example.boostar_uaal.core.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun BlockedIcon(modifier: Modifier = Modifier){
    Surface(
        modifier = modifier,
        shape = CircleShape,
        shadowElevation = 20.dp
    ) {
        Icon(
            modifier = Modifier.padding(5.dp),
            imageVector = Icons.Default.Lock,
            tint = Color.Gray,
            contentDescription = ""
        )
    }
}
@Preview
@Composable
fun PreviewBlockedIcon(){
    BlockedIcon()
}