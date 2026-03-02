package com.example.boostar_uaal.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductColorDAO(
    val id: Int,
    val name: String,
    @SerialName("hex")val hexColor: String
)