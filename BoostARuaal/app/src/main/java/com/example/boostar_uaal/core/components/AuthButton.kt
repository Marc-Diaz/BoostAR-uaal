package com.example.boostar_uaal.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.R
import com.example.boostar_uaal.core.theme.authPrimaryButtonColor
import com.example.boostar_uaal.core.theme.authSecondaryButtonColor

@Composable
fun AuthButton(
    onClick: () -> Unit = {},
    icon: Int? = null,
    text: String,
    isFilled: Boolean
){

    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(0.8f).height(75.dp).padding(10.dp),
        content = {
            Row {
                icon?.let {
                    Icon(
                        painter = painterResource(R.drawable.),
                        contentDescription = "Icono"
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                InterText(text, fontSize = 18.sp)
            }
        },
        colors = if (isFilled) ButtonColors(
            containerColor = authPrimaryButtonColor,
            contentColor = authSecondaryButtonColor,
            disabledContainerColor = authPrimaryButtonColor,
            disabledContentColor = authSecondaryButtonColor
        ) else ButtonColors(
            containerColor = authSecondaryButtonColor,
            contentColor = authPrimaryButtonColor,
            disabledContainerColor = Color.Blue,
            disabledContentColor = Color.Black
        ),
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            brush = SolidColor(authPrimaryButtonColor)
        )
    )

}

