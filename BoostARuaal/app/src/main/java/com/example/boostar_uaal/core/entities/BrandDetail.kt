package com.example.boostar_uaal.core.entities

import com.example.core.entities.Product
import kotlinx.serialization.Serializable

@Serializable
data class BrandDetail(
    val id: Int,
    val name: String,
    val logoImage: String,
    val products: Product
)
