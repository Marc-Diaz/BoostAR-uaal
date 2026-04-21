package com.example.boostar_uaal.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.theme.primaryColor
import com.example.boostar_uaal.core.theme.primaryTextColor


@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    icon: String? = null,
    title: String,
    onClick: () -> Unit = {},
    textColor: Color = primaryTextColor,
    fontSize: TextUnit = 24.sp,
    arrowColor: Color = primaryColor,
    enabled: Boolean = true

) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .then(if (enabled) Modifier.clickable { onClick() } else Modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        icon?.let {
            Surface(
                modifier = Modifier
                    .size(40.dp),
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 6.dp
            ) {
                ItemImage(
                    url =icon,
                    contentDescription = title,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
        InterText(
            text = title,
            color = textColor,
            fontSize = fontSize,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 1
        )


        Spacer(modifier = Modifier.width(8.dp))
        if (enabled){
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = "Ver más",
                tint = arrowColor,
                modifier = Modifier.size(22.dp)
            )
        }
        else {
            BlockedIcon()
        }
    }
}
@Composable
fun SectionHeader(
    icon: String? = null,
    title: AnnotatedString,
    onClick: () -> Unit = {},
    textColor: Color = primaryTextColor,
    fontSize: TextUnit = 24.sp,
    arrowColor: Color = primaryColor,
    enabled: Boolean = true

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .then(if (enabled) Modifier.clickable { onClick() } else Modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        icon?.let {
            Surface(
                modifier = Modifier
                    .size(40.dp),
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 6.dp
            ) {
                ItemImage(
                    url =icon,
                    contentDescription = title.toString(),
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
        InterText(
            text = title,
            color = textColor,
            fontSize = fontSize,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 1
        )


        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = "Ver más",
            tint = arrowColor,
            modifier = Modifier.size(22.dp)
        )

        if (!enabled){
            Spacer(modifier = Modifier.width(8.dp))
            BlockedIcon()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewSectionHeader(){
    SectionHeader(
        icon = "",
        title = "Título sección",
        onClick = {},
        enabled = true
    )
}