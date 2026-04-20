package com.example.boostar_uaal.core.entities

import kotlinx.serialization.Serializable

@Serializable
enum class TypeMultimedia(val value: String){
    IMAGE("IMAGEN"),
    VIDEO("VIDEO"),
    MODEL("MODELO");

    companion object {
        fun fromString(value: String) = entries.first { it.value == value }
    }
}