package com.example.boostar_uaal.ui.screen.gameScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.entities.Challenge
import com.example.boostar_uaal.core.theme.secondaryColor

@Composable
fun ForYouSection(challenge: Challenge?, onChallengeStart: (Int) -> Unit){
    Column() {
        Row(modifier = Modifier.fillMaxWidth().padding(start = 20.dp)) {
            Icon(
                painter = painterResource(R.drawable.game_icon),
                contentDescription = "",
                tint = secondaryColor
            )
            Spacer(Modifier.width(12.dp))
            InterText(
                text = "RECOMENDADO PARA TI",
                color = secondaryColor,
                fontSize = 14.9.sp,
                fontWeight = FontWeight(700)
            )
        }
        ChallengeBanner(challenge = challenge, onChallengeStart = { onChallengeStart(it) })
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
    ForYouSection(challenge, {})
}