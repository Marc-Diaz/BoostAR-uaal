package com.example.core.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id_producto") val id: Int,
    @SerialName("nombre") val name: String,
    @SerialName("precio") val price: Double,
    @SerialName("precio_oferta") val discountPrice: Double? = null,
    @SerialName("img_portada") val coverImage: String,
    @SerialName("num_likes") var numLikes: Long,
    @SerialName("partner") val partner: String,
    @SerialName("url_logo") val partnerLogo: String,
    @SerialName("gustado") var isLiked: Boolean
)