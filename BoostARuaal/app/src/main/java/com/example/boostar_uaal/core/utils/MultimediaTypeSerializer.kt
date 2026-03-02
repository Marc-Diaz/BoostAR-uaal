package com.example.boostar_uaal.core.utils

import com.example.boostar_uaal.core.entities.TypeMultimedia
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class MultimediaTypeSerializer : KSerializer<TypeMultimedia> {
    override val descriptor = PrimitiveSerialDescriptor("TypeMultimedia", PrimitiveKind.INT)
    override fun serialize(encoder: Encoder, value: TypeMultimedia) = encoder.encodeInt(value.value)
    override fun deserialize(decoder: Decoder) = TypeMultimedia.fromInt(decoder.decodeInt())
}
