package com.example.boostar_uaal.ui.screen.newPartnerScreens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.BlockedIcon
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.components.ItemImage
import com.example.boostar_uaal.core.entities.PartnerData
import com.example.boostar_uaal.core.theme.primaryColor

@Composable
fun PartnerTitle(partner: PartnerData, enabled: Boolean = true){
    Row(
        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 18.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Surface(
            modifier = Modifier
                .size(40.dp),
            shape = CircleShape,
            color = Color.Transparent,
            shadowElevation = 6.dp
        ) {
            ItemImage(
                url =partner.logoUrl,
                contentDescription = partner.name,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        InterText(
            modifier = Modifier.widthIn(max = 200.dp),
            text = AnnotatedString.fromHtml(partner.name),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = "Ver más",
            tint = primaryColor,
            modifier = Modifier.size(22.dp)
        )
        if (!enabled)
            BlockedIcon()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPartnerTitle(){
    val parter = PartnerData(
        id = "",
        name = "Bazar sarria",
        logoUrl = ""
    )
    PartnerTitle(parter, false)
}
