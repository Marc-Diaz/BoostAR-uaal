package com.example.boostar_uaal.core.entities

import kotlinx.serialization.Serializable

@Serializable
data class Partner(
    val id: String,
    val name: String,
    val logoUrl: String,

    )