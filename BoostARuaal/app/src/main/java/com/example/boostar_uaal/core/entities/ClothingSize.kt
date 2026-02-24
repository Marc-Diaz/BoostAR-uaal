package com.example.boostar_uaal.core.entities
import kotlinx.serialization.Serializable

@Serializable
data class ClothingSize (
    val id: Int,
    val name: String,
    val standard: Standard
)