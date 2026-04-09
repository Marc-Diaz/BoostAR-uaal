package com.example.boostar_uaal.ui.screen.arScreen.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.components.ItemImage
import com.example.boostar_uaal.core.entities.Partner
import com.example.boostar_uaal.core.utils.formatLikes

@Composable
fun ProductInformationTopBar(partner: Partner, productName: String, productLikes: Long ) {
    val textShadowStyle = TextStyle(
        shadow = Shadow(
            color = Color.Black.copy(alpha = 0.6f),
            offset = Offset(2f, 2f),
            blurRadius = 6f
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White,CircleShape).padding(0.dp)
            ) {
                ItemImage(
                    url =partner.logoUrl,
                    contentDescription = partner.name,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE2E2E2)),
                contentAlignment = Alignment.Center
            ) {
                InterText(
                    text = "42",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))
        Row(
            modifier = Modifier
                .height(44.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InterText(
                text = productName,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                style = textShadowStyle,
                maxLines = 1
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Likes",
                    tint = Color.Red,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                InterText(
                    text = productLikes.formatLikes(),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    style = textShadowStyle
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewOverlayProductInfo(){
    ProductInformationTopBar(
        partner = Partner("", "", ""),
        productName = "Nike",
        productLikes = 111600
    )
}