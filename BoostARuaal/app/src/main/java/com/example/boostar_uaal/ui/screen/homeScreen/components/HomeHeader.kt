package com.example.boostar_uaal.ui.screen.homeScreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.BlockedIcon
import com.example.boostar_uaal.core.components.InterText

@Composable
fun HomeHeader(modifier: Modifier = Modifier, navEnabled: Boolean = true) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
             verticalAlignment = Alignment.CenterVertically

    ) {
        InterText(
            text = "Home",
            fontSize = 38.54.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.weight(1f))
        Box(){
            Surface(
                shape = CircleShape,
                color = Color(0xFFF2F2F2),
                modifier = Modifier.size(48.dp)
            ) {
                IconButton(onClick = {}, enabled = navEnabled) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint = Color.Gray,
                        modifier = Modifier.size(28.dp),
                    )
                }

            }
            if (!navEnabled)
                BlockedIcon(modifier = Modifier
                    .size(30.dp)
                    .offset(x = (25).dp, y = (-10).dp)
                )
        }

    }
}
@Preview(showBackground = true)
@Composable
fun PreviewHomeHeader(){
    HomeHeader(navEnabled = false)
}