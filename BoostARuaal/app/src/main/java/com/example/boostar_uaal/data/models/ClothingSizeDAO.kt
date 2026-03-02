package com.example.boostar_uaal.data.models
import kotlinx.serialization.Serializable

@Serializable
data class ClothingSizeDAO (
    val id: Int,
    val name: String,
    val standard: StandardDAO
)