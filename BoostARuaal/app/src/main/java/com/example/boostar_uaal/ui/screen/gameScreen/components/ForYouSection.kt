package com.example.boostar_uaal.ui.screen.gameScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.entities.Challenge
import com.example.boostar_uaal.core.theme.secondaryTextColor

@Composable
fun ForYouSection(challenge: Challenge?){
    Column() {
        InterText(
            modifier = Modifier.padding(start = 48.dp),
            text = "RECOMENDADO PARA TI",
            style = TextStyle(
                fontSize = 14.9.sp,
                fontWeight = FontWeight(700),
                color = secondaryTextColor,
            )
        )
        ChallengeBanner(challenge = challenge)
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewForYouSection(){
    val challenge = Challenge(
        id = 0,
        title = "Domina la\ncolorimetría",
        description = "Aprende a combinar colores y crea un outfit con AR que refleje tu paleta personal.",
        time = 15,
        xp = 250,
        points = 150,
        isActive = false
    )
    ForYouSection(challenge)
}