package com.example.boostar_uaal.core.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.theme.primaryTextColor
import androidx.compose.ui.text.font.FontStyle
import kotlin.math.max
import kotlin.math.min

@Composable
fun InterText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight = FontWeight.Medium,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = primaryTextColor,
    textDecoration: TextDecoration = TextDecoration.None,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified,
    style: TextStyle = LocalTextStyle.current,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    minLines: Int = 1,
    maxLines: Int = Int.MAX_VALUE
){
    Text(
        text = text,
        modifier = modifier,
        fontFamily = FontFamily(Font(R.font.inter_medium)),
        fontStyle = fontStyle,
        fontSize = fontSize,
        fontWeight = fontWeight,
        textAlign = textAlign,
        color = color,
        overflow = overflow,
        textDecoration = textDecoration,
        letterSpacing = letterSpacing,
        lineHeight = lineHeight,
        style = style,
        minLines = minLines,
        maxLines = maxLines
    )
}
@Composable
fun InterText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight = FontWeight.Medium,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = primaryTextColor,
    textDecoration: TextDecoration = TextDecoration.None,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified,
    style: TextStyle = LocalTextStyle.current,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    minLines: Int = 1,
    maxLines: Int = Int.MAX_VALUE
){
    Text(
        text = text,
        modifier = modifier,
        fontFamily = FontFamily(Font(R.font.inter_medium)),
        fontStyle = fontStyle,
        fontSize = fontSize,
        fontWeight = fontWeight,
        textAlign = textAlign,
        color = color,
        overflow = overflow,
        textDecoration = textDecoration,
        letterSpacing = letterSpacing,
        lineHeight = lineHeight,
        style = style,
        minLines = minLines,
        maxLines = maxLines
    )
}