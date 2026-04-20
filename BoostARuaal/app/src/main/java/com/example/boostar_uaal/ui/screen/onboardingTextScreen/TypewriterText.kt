package com.example.boostar_uaal.ui.screen.onboardingTextScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import com.example.boostar_uaal.core.theme.primaryColor
import kotlinx.coroutines.delay

sealed class OnboardingState {
    data object Step1 : OnboardingState()
    data object Step2 : OnboardingState()
}


@Composable
fun TypewriterText(
    baseText: String,
    highlightText: String = "",
    fontSize: TextUnit,
    onAnimationFinished: () -> Unit = {}
) {
    val fullText = "$baseText $highlightText".trim()
    var displayedCharacterCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(fullText) {
        displayedCharacterCount = 0
        while (displayedCharacterCount < fullText.length) {
            delay(50)
            displayedCharacterCount++
        }
        onAnimationFinished()
    }

    val styledText = buildAnnotatedString {
        val currentBase = if (displayedCharacterCount <= baseText.length) {
            baseText.take(displayedCharacterCount)
        } else {
            baseText
        }
        append(currentBase)

        if (displayedCharacterCount > baseText.length) {
            if (baseText.isNotEmpty()) append(" ")

            val highlightCount = displayedCharacterCount - (baseText.length + (if(baseText.isNotEmpty()) 1 else 0))
            if (highlightCount > 0) {
                withStyle(style = SpanStyle(color = primaryColor, fontWeight = FontWeight.Bold)) {
                    append(highlightText.take(highlightCount))
                }
            }
        }
    }

    Text(
        text = styledText,
        fontSize = fontSize,
        fontWeight = FontWeight.Medium
    )
}