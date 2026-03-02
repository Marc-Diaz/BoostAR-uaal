package com.example.boostar_uaal.core.repository

import com.example.boostar_uaal.core.entities.Brand

interface BrandRepository {
    suspend fun getBrands(): List<Brand>

}