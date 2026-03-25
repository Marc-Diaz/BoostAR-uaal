package com.example.boostar_uaal.ui.screen.feedScreen.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.ItemImage
import com.example.boostar_uaal.core.entities.Partner
import com.example.boostar_uaal.core.navigation.Routes


@Composable
fun BottomPartnerSearch(modifier: Modifier = Modifier, partner: Partner, onShareClick: () -> Unit, onPartnerClick: () -> Unit) {

    val asyncImagePainter = rememberAsyncImagePainter(partner.logoUrl)
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(top = 25.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        IconButton(
            onClick = { onPartnerClick()},
            modifier = Modifier
                .size(32.dp)
                .background(Color.White,CircleShape)
        ) {
            ItemImage(
                url =partner.logoUrl,
                contentDescription = partner.name,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }

        IconButton(
            onClick ={ onShareClick() },
            modifier = Modifier
                .size(32.dp)
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