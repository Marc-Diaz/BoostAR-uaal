package com.example.boostar_uaal.ui.screen.feedScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.boostar_uaal.core.theme.primaryColor
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.BlockedIcon
import com.example.boostar_uaal.core.components.PaginationPoints

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
                    painter = painterResource(R.drawable.list_icon),
                    contentDescription = "Details",
                    tint = primaryColor,
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
                    painter = painterResource(R.drawable.hanger_icon),
                    contentDescription = "AR",
                    tint = Color.White,
                    modifier = Modifier.size(100.dp)

                )

            }
            Box{
                IconButton(
                    onClick = onQuickPayClick,
                    modifier = Modifier
                        .size(57.dp)
                        .background(Color(0xFFFFD600), CircleShape),
                    enabled = false

                ) {
                    Icon(
                        painter = painterResource(R.drawable.buy_icon),
                        contentDescription = "Pago Rápido",
                        tint = Color.Black,
                        modifier = Modifier.size(28.dp)

                    )
                }
                BlockedIcon(modifier = Modifier.offset(x = 30.dp, y = (-10).dp))
            }
        }

    }
}

@Preview
@Composable
fun PreviewBottomActionDock(){
    BottomActionDock(
        onDetailsClick = {  },
        onTryArClick = { },
        onQuickPayClick = { }
    )
}