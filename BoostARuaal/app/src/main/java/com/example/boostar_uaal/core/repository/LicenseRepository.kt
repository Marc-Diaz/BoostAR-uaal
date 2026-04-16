package com.example.boostar_uaal.core.repository

import com.example.boostar_uaal.core.entities.License

interface LicenseRepository {
    suspend fun getLicenses(): List<License>
}