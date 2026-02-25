package com.example.boostar_uaal.ui.screen.singInScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CompanyAccountSelector(
    isChecked: Boolean,
    onToggle: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 8.dp)
            .clickable { onToggle() }
    ) {
        RadioButton(
            selected = isChecked,
            onClick = onToggle,
            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF007AFF))
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text("Company account", fontSize = 16.sp, color = Color.Black)
    }
}