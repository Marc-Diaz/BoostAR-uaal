package com.example.boostar_uaal.core.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//Entidad que conjunto con collabData, HeroBannerData habia que ver donde moverlas

@Serializable
data class Partner(
    val id: String,
    val name: String,
    val logoUrl: String,

    )