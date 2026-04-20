package com.example.boostar_uaal.data.repository

import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.entities.License
import com.example.boostar_uaal.core.repository.LicenseRepository

class LicenseRepositoryImpl(): LicenseRepository {
    override suspend fun getLicenses(): List<License> {
        val licenses = listOf(
            License(
                id = 1,
                backgroundImage = R.drawable.colab_3,
                name = "PULL&BEAR",
                resLogo = R.drawable.logo_stranger
            ),
            License(
                id = 2,
                backgroundImage = R.drawable.colab_2,
                name = "BALENCIAGA",
                resLogo = R.drawable.logo_puma
            ),
            License(
                id = 3,
                backgroundImage = R.drawable.colab_1,
                name = "ADIDAS",
                resLogo = R.drawable.logo_puma
            )
        )
        return licenses
    }

}