package com.example.boostar_uaal.core.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PartnerData(
    @SerialName("id_empresa") val id: String,
    @SerialName("nombre") val name: String,
    @SerialName("foto_perfil") val logoUrl: String,

    )