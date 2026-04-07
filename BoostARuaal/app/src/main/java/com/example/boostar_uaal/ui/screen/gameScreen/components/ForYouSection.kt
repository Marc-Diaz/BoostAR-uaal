package com.example.boostar_uaal.ui.screen.gameScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.theme.secondaryTextColor

@Composable
fun ForYouSection(){
    Column() {
        InterText(
            text = "RECOMENDADO PARA TI",
            style = TextStyle(
                fontSize = 14.9.sp,
                fontWeight = FontWeight(700),
                color = secondaryTextColor,
            )
        )
        ChallengeBanner()
    }
}
@Preview
@Composable
fun PreviewForYouSection(){
    ForYouSection()
}