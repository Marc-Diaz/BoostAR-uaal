package com.example.boostar_uaal.ui.screen.arScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.theme.primaryColor

@Composable
fun CameraControlsOverlay(
    onFlipCamera: () -> Unit,
    onDetailClick: () -> Unit
) {

    val borderGrey = Color(0xFFA9A9A9)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier.align(Alignment.BottomStart),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                onClick = { onFlipCamera() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.flip_camera),
                    contentDescription = "Flip camera",
                    tint = Color.Unspecified,
                    modifier = Modifier.fillMaxSize()
                )
            }

            IconButton(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                onClick = { onDetailClick() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.list_icon),
                    contentDescription = "Details",
                    tint = primaryColor,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        IconButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(130.dp)
                .height(64.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.White)
                .border(
                    width = 4.dp,
                    color = borderGrey,
                    shape = RoundedCornerShape(50)
                ),
                onClick = {}
        ) {
            Icon(
                painter = painterResource(R.drawable.camera_icon),
                contentDescription = "Tomar Foto",
                tint = Color.Unspecified,
                modifier = Modifier.size(32.dp)
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(56.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Añadir",
                tint = primaryColor,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}
@Preview
@Composable
fun PrevierCameraControlsOverlay(){
    CameraControlsOverlay(
        onFlipCamera = { },
        onDetailClick = {}
    )
}