package com.example.boostar_uaal.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.entities.License

@Composable
fun LicenseCard(
    license: License,
    onClick: () -> Unit,
    enabled: Boolean = false
) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000),
                shape = RoundedCornerShape(21.dp)
            )
            .width(157.dp)
            .height(221.dp)
            .background(color = Color(0xFF1E1E1E), shape = RoundedCornerShape(21.dp))
            .clip(RoundedCornerShape(21.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = license.backgroundImage),
            contentDescription = "Fondo colaboración",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        if (!enabled){
            Image(
                painter = painterResource(R.drawable.blocked_layer),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            InterText(
                text = license.name,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            InterText(
                text = "✕",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 24.sp,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = license.resLogo),
                contentDescription = "Logo marca",
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
@Preview
@Composable
fun PreviewLicenseCard(){
    val license = License(
        id = 1,
        name = "PULL&BEAR",
        resLogo = R.drawable.logo_puma,
        backgroundImage = R.drawable.colab_3
    )
    LicenseCard(
        license = license,
        onClick = { },
        enabled = true
    )
}