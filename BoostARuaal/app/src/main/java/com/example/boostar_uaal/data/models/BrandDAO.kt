package com.example.boostar_uaal.data.models

import kotlinx.serialization.Serializable

@Serializable
data class BrandDAO(
    val id: Int,
    val name: String,
    val logoImage: String
)
