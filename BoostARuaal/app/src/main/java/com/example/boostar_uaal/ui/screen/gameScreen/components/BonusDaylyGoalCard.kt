package com.example.boostar_uaal.ui.screen.gameScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.InterText

@Composable
fun BonusDaylyGoalCard(modifier: Modifier = Modifier) {

    val blueBorderColor = Color(0xFF1E88E5)
    val lightBlueBackground = Color(0xFFE3F2FD)
    val textColorDark = Color(0xFF1C1C1E)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
                spotColor = Color.LightGray
            )
            .clip(RoundedCornerShape(12.dp))
            .background(lightBlueBackground)
            .border(
                width = 1.dp,
                color = blueBorderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InterText(
            text = "🎯",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = textColorDark)) {
                    append("Completa ")
                }
                withStyle(style = SpanStyle(color = blueBorderColor, fontWeight = FontWeight.Bold)) {
                    append("todos los objetivos")
                }
                withStyle(style = SpanStyle(color = textColorDark)) {
                    append(" para ganar un ")
                }
                withStyle(style = SpanStyle(color = blueBorderColor, fontWeight = FontWeight.Bold)) {
                    append("bono de +100 XP")
                }
                withStyle(style = SpanStyle(color = textColorDark)) {
                    append(" extra.")
                }
            },
            fontSize = 15.sp,
            lineHeight = 20.sp
        )
    }
}
@Preview
@Composable
fun PreviewBonusDaylyGoalCard(){
    BonusDaylyGoalCard()
}