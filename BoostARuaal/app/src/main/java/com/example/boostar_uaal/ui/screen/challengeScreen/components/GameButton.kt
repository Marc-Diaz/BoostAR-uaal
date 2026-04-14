package com.example.boostar_uaal.ui.screen.challengeScreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.theme.primaryColor

@Composable
fun GameButton(modifier: Modifier = Modifier, isButtonVisible: Boolean = true, text: String, onClick: () -> Unit){
    Box(
        modifier = modifier
            .fillMaxWidth().height(60.dp)
            ,
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isButtonVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 400, easing = EaseIn)),
            exit = ExitTransition.None
        )
        {
            Button(
                onClick = {
                    onClick()
                },
                modifier = Modifier.size(width = 323.dp, height = 57.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                shape = CircleShape,
                content = {
                    InterText(
                        text = text,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )
                }
            )

        }

    }
}