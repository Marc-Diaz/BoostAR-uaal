package com.example.boostar_uaal.core.entities

data class Multimedia(
    val id: Int,
    val multimediaURL: String,
    val isPrincipal: Boolean = false,
    val type: TypeMultimedia
)

enum class TypeMultimedia{
    IMAGE, VIDEO, MODEL3D
}