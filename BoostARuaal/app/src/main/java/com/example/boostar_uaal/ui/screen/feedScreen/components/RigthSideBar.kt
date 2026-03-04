package com.example.boostar_uaal.ui.screen.feedScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.ShoppingCart
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
import com.example.boostar_uaal.core.theme.discountColor

//Botones de la derecha de la pantalla
@Composable
fun RightSideBar(modifier: Modifier = Modifier, likes: Int, onProfileClick: () -> Unit) {
    Column(
        modifier = modifier.padding(
            end = 16.dp,
            bottom = 100.dp
        ), // Espacio para no chocar con el dock
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        // Like
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Surface(
                modifier = Modifier.size(65.dp),
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 6.dp
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Like",
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(24.dp)

                    )
                }

            }

        }

        // Share
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Surface(
                modifier = Modifier.size(65.dp),
                shape = CircleShape,
                color = Color.Blue,
                shadowElevation = 6.dp
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingCart,
                        contentDescription = "carrito",
                        tint = Color.White,
                        modifier = Modifier
                            .size(36.dp)
                            .background(Color.Transparent, CircleShape)
                    )
                }
            }
        }

    }
}
