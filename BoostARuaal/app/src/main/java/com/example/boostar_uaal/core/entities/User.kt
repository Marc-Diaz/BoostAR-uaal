package com.example.boostar_uaal.core.entities

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val mail: String,
    val picture: String,
    val numLikes: Int,
    val numNotifications: Int,
    val numBuys: Int,
    val numRefunds: Int
)