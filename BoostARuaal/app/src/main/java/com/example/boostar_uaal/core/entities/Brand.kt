package com.example.boostar_uaal.core.entities

import kotlinx.serialization.Serializable

@Serializable
data class Brand(
    val id: Int,
    val name: String,
    val logoImage: String
)
