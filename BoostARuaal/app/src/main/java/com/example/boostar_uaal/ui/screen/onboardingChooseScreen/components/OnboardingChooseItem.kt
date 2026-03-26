package com.example.boostar_uaal.ui.screen.onboardingChooseScreen.components

import androidx.compose.foundation.border
import com.example.boostar_uaal.core.components.ItemImage
import com.example.boostar_uaal.core.theme.primaryButtonColor
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.theme.secondaryTextColor
import com.example.core.entities.Product

@Composable
fun OnboardingChooseItem(
    product: Product,
    clickable: () -> Unit,
    isSelected: Boolean = false
) {
        Box(
            modifier = Modifier
                .size(height = 220.dp, width = 160.dp)
                .clickable{ clickable() }
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        width = 3.dp,
                        color = if (isSelected) primaryButtonColor else secondaryTextColor,
                        shape = RoundedCornerShape(16.dp)
                    ),
                shape = RoundedCornerShape(16.dp)
            ) {
                ItemImage(
                    url = product.coverImage,
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
    }
}