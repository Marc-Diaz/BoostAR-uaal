package com.example.boostar_uaal.core.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//Entidad que conjunto con collabData, HeroBannerData habia que ver donde moverlas

@Serializable
data class PartnerData(
    @SerialName("id_empresa") val id: String,
    @SerialName("nombre") val name: String,
    @SerialName("foto_perfil") val logoUrl: String,

    )