package com.example.boostar_uaal.core.repository

import com.example.boostar_uaal.core.entities.PartnerData

interface PartnerRepository {
    suspend fun getPartners(): List<PartnerData>

}