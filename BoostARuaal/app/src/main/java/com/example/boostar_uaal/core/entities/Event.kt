package com.example.boostar_uaal.core.entities

data class Event(
    val id: Int,
    val isMain: Boolean,
    val logo: String,
    val title: String,
    val bannerName: String,
    val bannerDescription: String,
    val bannerMedia: Multimedia,
    val model: String,
    val productImage: String,
    val productDescription: String,

)