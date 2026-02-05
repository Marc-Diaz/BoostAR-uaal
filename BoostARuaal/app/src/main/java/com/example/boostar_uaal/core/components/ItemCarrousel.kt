package com.example.boostar_uaal.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.theme.primaryButtonColor
import com.example.boostar_uaal.data.repository.ProductRepositoryImpl
import com.example.core.entities.Product


@Composable
fun ItemCarrousel(name: String, products: List<Product>, iconTint: Color = primaryButtonColor, navigateTo: () -> Unit, itemClickable: (Int) -> Unit){
    Row(
        modifier = Modifier
            .clickable(
                onClick = navigateTo
            )
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        InterText(
            text = name,
            textAlign = TextAlign.Left,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
            contentDescription = "Arrow Icon",
            tint = primaryButtonColor,
        )

    }
    LazyRow(
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products){ product ->
            ItemCard(product, {itemClickable(product.id)})
        }
    }
}