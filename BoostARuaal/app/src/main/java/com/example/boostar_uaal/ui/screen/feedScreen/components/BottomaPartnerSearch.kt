package com.example.boostar_uaal.ui.screen.feedScreen.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.navigation.Routes


@Composable
fun BottomPartnerSearch(modifier: Modifier = Modifier, onShareClick: () -> Unit, onPartnerClick: () -> Unit) {


    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 48.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        IconButton(
            onClick = { onPartnerClick()},

            modifier = Modifier
                .size(52.dp)
                .background(Color.White,CircleShape)


        ) {

            Icon(
                painter = painterResource(id = R.drawable.boostar_logo),
                contentDescription = "Atrás",
                tint = Color.Blue,
                modifier = Modifier.size(28.dp)
            )
        }

        IconButton(
            onClick ={ onShareClick() },
            modifier = Modifier
                .size(52.dp)
                .background(Color.White, CircleShape)


        ) {

            Icon(
                imageVector = Icons.Outlined.Share,
                contentDescription = "compartir",
                tint = Color.Black,
                modifier = Modifier.size(28.dp)
            )

        }
    }
}