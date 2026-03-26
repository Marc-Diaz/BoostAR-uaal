package com.example.boostar_uaal.ui.screen.feedScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.BoostArApplication
import com.example.boostar_uaal.LocalAuthState
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.theme.primaryButtonColor
import com.example.boostar_uaal.core.utils.AuthState

//Botones de la derecha de la pantalla
@Composable
fun RightSideBar(
    modifier: Modifier = Modifier,
    isLiked: Boolean,
    onCartClick: () -> Unit,
    onLikeClick: ()-> Unit
) {
    Column(
        modifier = modifier.padding(
            end = 16.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Surface(
                modifier = Modifier.size(57.dp),
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 6.dp
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(enabled = LocalAuthState.current is AuthState.Authenticated, onClick = { onLikeClick() }),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.heart_icon),
                        contentDescription = "Like",
                        tint = if(isLiked) Color.Red else Color.Gray,
                        modifier = Modifier
                            .size(24.dp)

                    )
                }

            }

        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Surface(
                modifier = Modifier.size(57.dp),
                shape = CircleShape,
                color = primaryButtonColor,
                shadowElevation = 6.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onCartClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.cart_icon),
                        contentDescription = "Cart",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(36.dp)
                            .background(Color.Transparent, CircleShape)
                    )
                }
            }
        }

    }
}
