package com.example.boostar_uaal.ui.screen.feedScreen.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.draw.shadow
import com.example.boostar_uaal.core.theme.primaryButtonColor

@Composable

fun BottomActionDock(
    modifier: Modifier = Modifier,
    onDetailsClick: () -> Unit,
    onTryArClick: () -> Unit,
    onQuickPayClick: () -> Unit

) {

    Surface(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth()
            .height(80.dp),
        color = Color.Transparent

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            IconButton(
                onClick = onDetailsClick,
                modifier = Modifier
                    .size(57.dp)
                    .background(Color.White, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.List,
                    contentDescription = "Carrito",
                    tint = primaryButtonColor,
                    modifier = Modifier.size(28.dp)
                )
            }

            Button(
                onClick = { onTryArClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5247)),
                shape = RoundedCornerShape(45.dp),
                modifier = Modifier
                    .height(65.dp)
                    .width(148.dp)
                    .padding(horizontal = 8.dp)

            ) {

                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Detalles",
                    tint = Color.White,
                    modifier = Modifier.size(57.dp)

                )

            }

            IconButton(
                onClick = onQuickPayClick,
                modifier = Modifier
                    .size(57.dp)
                    .background(Color(0xFFFFD600), CircleShape)

            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Pago Rápido",
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)

                )
            }
        }

    }
}