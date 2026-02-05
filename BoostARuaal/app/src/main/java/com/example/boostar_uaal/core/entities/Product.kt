package com.example.core.entities

import com.example.boostar_uaal.core.entities.Brand
import com.example.boostar_uaal.core.entities.ClothingSize
import com.example.boostar_uaal.core.entities.Multimedia
import com.example.boostar_uaal.core.entities.ProductColor
import com.example.boostar_uaal.core.entities.Style

data class Product(
    val id : Int,
    val name: String,
    val price: Double,
    val discountPrice: Double? = null,
    val brand: Brand,
    val style: Style,
    val numLikes: Int,
    val modelURL: String,
    val multimedia: List<Multimedia>,
    val tallas: List<ClothingSize>,
    val colors: List<ProductColor>,
)