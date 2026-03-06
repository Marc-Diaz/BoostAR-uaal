package com.example.boostar_uaal.data.repository

import android.util.Log
import com.example.boostar_uaal.core.entities.PartnerData
import com.example.boostar_uaal.core.repository.PartnerRepository
import io.github.jan.supabase.postgrest.Postgrest

class PartnersRepositoryImpl(private val postgrest: Postgrest): PartnerRepository {
    override suspend fun getPartners(): List<PartnerData> {
        val response : List<PartnerData> = postgrest["Partner_View"].select().decodeList<PartnerData>()
        Log.d("PARTNERS", "$response")
        return response
    }

}