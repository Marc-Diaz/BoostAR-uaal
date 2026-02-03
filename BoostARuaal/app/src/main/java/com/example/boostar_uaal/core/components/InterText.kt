package com.example.boostar_uaal.core.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R

@Composable
fun InterText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium
){
    Text(
        text = text,
        modifier = modifier,
        fontFamily = FontFamily(Font(R.font.waltographUI)),
        fontSize = fontSize,
        fontWeight = fontWeight
    )
}