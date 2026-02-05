package com.example.boostar_uaal.core.components.FeedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.ui.screen.feedScreen.BrandRed
import com.example.boostar_uaal.ui.screen.feedScreen.PriceRed

@Composable
fun RightSideBar(modifier: Modifier = Modifier, likes: Int, onProfileClick: () -> Unit) {
    Column(
        modifier = modifier.padding(end = 16.dp, bottom = 100.dp), // Espacio para no chocar con el dock
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Perfil con Badge
        Box {
            // Avatar placeholder
            Surface(
                modifier = Modifier.size(60.dp),
                shape = CircleShape,
                color = Color.White
            ) {
                Icon(Icons.Default.AccountBox, contentDescription = null, modifier = Modifier.padding(8.dp))
            }
            // Badge (+)
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Seguir",
                tint = BrandRed,
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.TopEnd)
                    .offset(y = 4.dp)
                    .background(Color.White, CircleShape)
            )
        }

        // Like
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Like",
                tint = PriceRed,
                modifier = Modifier.size(36.dp)
            )
        }

        // Share
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Share",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = "4,5k",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
