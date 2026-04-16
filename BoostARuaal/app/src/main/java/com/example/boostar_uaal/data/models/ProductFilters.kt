package com.example.boostar_uaal.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ProductFilters(
    var discount: Boolean = false,
    var partnerId: String? = null,
    var licenseId: Int? = null
)