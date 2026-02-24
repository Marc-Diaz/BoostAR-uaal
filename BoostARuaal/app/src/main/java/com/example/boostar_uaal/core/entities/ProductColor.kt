package com.example.boostar_uaal.core.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductColor(
    val id: Int,
    val name: String,
    @SerialName("hex")val hexColor: String
)