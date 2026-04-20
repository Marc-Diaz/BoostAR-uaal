package com.example.boostar_uaal.ui.screen.homeScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.boostar_uaal.core.components.LicenseCard
import com.example.boostar_uaal.core.entities.License

@Composable
fun LicensesCarousel(
    licenses: List<License>,
    onItemClick: (Int) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(licenses) { license ->
            LicenseCard(
                license = license,
                onClick = { onItemClick(license.id) }
            )
        }
    }
}