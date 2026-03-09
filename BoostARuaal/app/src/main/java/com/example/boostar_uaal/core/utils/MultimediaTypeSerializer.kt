package com.example.boostar_uaal.core.utils

import com.example.boostar_uaal.core.entities.TypeMultimedia
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class MultimediaTypeSerializer : KSerializer<TypeMultimedia> {
    override val descriptor = PrimitiveSerialDescriptor("TypeMultimedia", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: TypeMultimedia) = encoder.encodeString(value.value)
    override fun deserialize(decoder: Decoder) = TypeMultimedia.fromString(decoder.decodeString())
}
