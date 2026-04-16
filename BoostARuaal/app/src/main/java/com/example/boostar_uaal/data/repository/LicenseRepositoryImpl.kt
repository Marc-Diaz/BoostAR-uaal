package com.example.boostar_uaal.data.repository

import com.example.boostar_uaal.core.entities.License
import com.example.boostar_uaal.core.repository.LicenseRepository

class LicenseRepositoryImpl(): LicenseRepository {
    override suspend fun getLicenses(): List<License> {
        val licenses = listOf(
            License(
                id = 1,
                name = "Boostar",
                urlLogo = "https://moygfqmmtuwvpeatrvhw.supabase.co/storage/v1/object/sign/imagenes/boostar_logo.png?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9hMWNkOTIxYi1kMGNiLTQyN2ItOTFlMC1lZTI2OGZlOGNmZWIiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJpbWFnZW5lcy9ib29zdGFyX2xvZ28ucG5nIiwiaWF0IjoxNzc2MzM0NzgwLCJleHAiOjE4MDc4NzA3ODB9.DTWS3MYJxlrRtN3oOp1oJpFVxsbXbjzpGsRVJ-33rJs"
            ),
            License(
                id = 2,
                name = "Stranger Things",
                urlLogo = "https://moygfqmmtuwvpeatrvhw.supabase.co/storage/v1/object/sign/imagenes/boostar_logo.png?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9hMWNkOTIxYi1kMGNiLTQyN2ItOTFlMC1lZTI2OGZlOGNmZWIiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJpbWFnZW5lcy9ib29zdGFyX2xvZ28ucG5nIiwiaWF0IjoxNzc2MzM0NzgwLCJleHAiOjE4MDc4NzA3ODB9.DTWS3MYJxlrRtN3oOp1oJpFVxsbXbjzpGsRVJ-33rJs"
            ),
            License(
                id = 3,
                name = "Solo Leveling",
                urlLogo = "https://moygfqmmtuwvpeatrvhw.supabase.co/storage/v1/object/sign/imagenes/boostar_logo.png?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9hMWNkOTIxYi1kMGNiLTQyN2ItOTFlMC1lZTI2OGZlOGNmZWIiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJpbWFnZW5lcy9ib29zdGFyX2xvZ28ucG5nIiwiaWF0IjoxNzc2MzM0NzgwLCJleHAiOjE4MDc4NzA3ODB9.DTWS3MYJxlrRtN3oOp1oJpFVxsbXbjzpGsRVJ-33rJs"
            )
        )
        return licenses
    }

}