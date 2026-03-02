package com.example.boostar_uaal.data.models
import com.example.boostar_uaal.core.utils.MultimediaTypeSerializer
import kotlinx.serialization.Serializable

@Serializable
data class Multimedia(
    val id: Int,
    val multimediaURL: String,
    val isPrincipal: Boolean = false,
    @Serializable(with = MultimediaTypeSerializer::class)
    val type: TypeMultimedia
)



@Serializable
enum class TypeMultimedia(val value: Int){
    IMAGE(1),
    VIDEO(2),
    MODEL3D(3);

    companion object {
        fun fromInt(value: Int) = entries.first { it.value == value }
    }
}