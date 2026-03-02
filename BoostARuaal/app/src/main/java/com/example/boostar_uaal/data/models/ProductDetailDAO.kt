package com.example.boostar_uaal.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailDAO(
    val id : Int,
    val name: String,
    val price: Double,
    val discountPrice: Double? = null,
    val brand: BrandDAO,
    val style: StyleDAO,
    val numLikes: Int,
    val modelURL: String? = null,
    val coverImage: String,
    val multimedia: List<Multimedia>,
    val tallas: List<ClothingSizeDAO>,
    val colors: List<ProductColorDAO>,

    )
