package com.example.boostar_uaal.core.entities
import com.example.boostar_uaal.core.utils.MultimediaTypeSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class Multimedia(
    val id: Int,
    val multimediaURL: String,
    @Serializable(with = MultimediaTypeSerializer::class)
    val type: TypeMultimedia
)



@Serializable
enum class TypeMultimedia(val value: String){
    IMAGE("IMAGEN"),
    VIDEO("VIDEO"),
    MODEL("MODELO");

    companion object {
        fun fromString(value: String) = entries.first { it.value == value }
    }
}