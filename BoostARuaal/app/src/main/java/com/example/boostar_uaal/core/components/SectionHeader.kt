package com.example.boostar_uaal.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.theme.primaryButtonColor
import com.example.boostar_uaal.core.theme.primaryTextColor


@Composable
fun SectionHeader(
    title: String,
    onClick: () -> Unit = {},
    textColor: Color = primaryTextColor,fontSize: TextUnit = 24.sp

) {
    val isClickable = title.endsWith(">")
    val cleanTitle = title.removeSuffix(">").removeSuffix(".").trim()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .then(if (isClickable) Modifier.clickable { onClick() } else Modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        InterText(
            text = cleanTitle,
            color = textColor,
            fontSize = fontSize,
            fontWeight = FontWeight.ExtraBold
        )

        // Solo pintamos la flecha si es navegable
        if (isClickable) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = "Ver más",
                tint = primaryButtonColor,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}