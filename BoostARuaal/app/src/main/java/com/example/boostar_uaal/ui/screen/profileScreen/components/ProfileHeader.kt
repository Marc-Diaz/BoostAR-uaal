package com.example.boostar_uaal.ui.screen.profileScreen.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.boostar_uaal.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.components.ItemImage
import com.example.boostar_uaal.core.theme.primaryColor

@Composable
fun ProfileHeader(modifier: Modifier = Modifier, name: String, mail: String, picture: String, level: Int ){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {

            ItemImage(
                url = picture,
                contentDescription = name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    ,
                contentScale = ContentScale.Crop
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .offset(y = 14.dp)
                    .background(
                        color = primaryColor,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.game_icon),
                    contentDescription = "Nivel",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                InterText(
                    text = "$level",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        InterText(
            text = name,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))


        InterText(
            text = mail,
            color = Color(0xFF8E8E93),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
    }
}
@Preview
@Composable
fun PreviewProfileHeader(){
    
    ProfileHeader(
        name = "Carlos",
        mail = "perez@gmail.com",
        picture = "lkfd",
        level = 12
    )
}