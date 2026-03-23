package com.example.boostar_uaal.ui.screen.authScreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.components.InterText
import com.example.boostar_uaal.core.theme.primaryButtonColor
import com.example.boostar_uaal.core.theme.secondaryButtonColor

@Composable
fun AuthButton(
    onClick: () -> Unit = {},
    icon: Int? = null,
    text: String,
    isFilled: Boolean
){

    Button(
        onClick = onClick,
        modifier = Modifier.size(width = 323.dp, height = 57.dp),
        content = {
            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                icon?.let {
                    Image(
                        painter = painterResource(icon),
                        contentDescription = "Icono",
                        modifier = Modifier.size(35.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(modifier = Modifier.width(18.dp))
                InterText(
                    text = text,
                    fontSize = 18.sp,
                    color = if (isFilled) secondaryButtonColor
                    else primaryButtonColor,
                    fontWeight = FontWeight.Black
                )
            }
        },
        colors = if (isFilled) ButtonColors(
            containerColor = primaryButtonColor,
            contentColor = secondaryButtonColor,
            disabledContainerColor = primaryButtonColor,
            disabledContentColor = secondaryButtonColor
        ) else ButtonColors(
            containerColor = secondaryButtonColor,
            contentColor = primaryButtonColor,
            disabledContainerColor = Color.Blue,
            disabledContentColor = Color.Black
        ),
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            brush = SolidColor(primaryButtonColor)
        )
    )
}

@Preview
@Composable
fun AuthButtonPreview(){
    val icon = R.drawable.phone_icon
    AuthButton(
        onClick = {  },
        text = "Continuar con Google",
        isFilled = true,
        icon = icon
    )
}