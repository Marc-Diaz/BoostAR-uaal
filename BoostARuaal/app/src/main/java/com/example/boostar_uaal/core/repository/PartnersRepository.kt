package com.example.boostar_uaal.core.repository

import com.example.boostar_uaal.core.entities.Brand

interface PartnersRepository {
    suspend fun getBrands(): List<Brand>

}