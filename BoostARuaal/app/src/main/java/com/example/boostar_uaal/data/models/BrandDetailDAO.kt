package com.example.boostar_uaal.data.models

import com.example.core.entities.Product
import kotlinx.serialization.Serializable

@Serializable
data class BrandDetailDAO(
    val id: Int,
    val name: String,
    val logoImage: String,
    val products: Product
)
