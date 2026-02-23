package com.example.boostar_uaal.core.entities


data class ProductDetail(
    val id : Int,
    val name: String,
    val price: Double,
    val discountPrice: Double? = null,
    val brand: Brand,
    val style: Style,
    val numLikes: Int,
    val modelURL: String,
    val coverImage: String,
    val multimedia: List<Multimedia>,
    val tallas: List<ClothingSize>,
    val colors: List<ProductColor>,

)
