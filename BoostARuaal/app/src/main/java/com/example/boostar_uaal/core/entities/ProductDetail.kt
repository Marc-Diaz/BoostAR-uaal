package com.example.boostar_uaal.core.entities

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetail(
    val id : Int,
    val name: String,
    val price: Double,
    val discountPrice: Double? = null,
    val brand: Brand,
    val style: Style,
    var numLikes: Long,
    var isLiked: Boolean,
    val coverImage: String,
    val multimedia: List<Multimedia>,
    val sizes: List<ClothingSize>,
    val colors: List<ProductColor>,
    val model: String,
    val partner: Partner

    )
