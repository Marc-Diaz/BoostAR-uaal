package com.example.boostar_uaal.core.entities

import kotlinx.serialization.Serializable

@Serializable
data class LikeStyle(
    val id: Int,
    val name: String,
    val value: Int
)
