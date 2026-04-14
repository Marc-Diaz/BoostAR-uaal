package com.example.boostar_uaal.ui.screen.challengeScreen.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.entities.QuestionAnswer

@Composable
fun AnswerOptionCard(
    answer: QuestionAnswer,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) Color(0xFF007BFF) else Color(0xFFE5E5E5)
    val borderWidth = if (isSelected) 2.dp else 1.dp

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            // Hacemos que toda la tarjeta sea clickeable
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        border = BorderStroke(borderWidth, borderColor)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(answer.answerImage),
                contentDescription = ""
            )
        }
    }
}
@Preview
@Composable
fun PreviewAnswerOptionCard(){
    AnswerOptionCard(
        answer = QuestionAnswer(R.drawable.complementary_line, true),
        isSelected = true,
        onClick = { }
    )
}