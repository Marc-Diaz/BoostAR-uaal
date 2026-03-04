package com.example.boostar_uaal.data.repository

import com.example.boostar_uaal.core.entities.Brand
import com.example.boostar_uaal.core.repository.PartnersRepository
import io.github.jan.supabase.postgrest.Postgrest

class PartnersRepositoryImpl(private val postgrest: Postgrest): PartnersRepository {
    override suspend fun getBrands(): List<Brand> {
        val response : List<Brand> = postgrest["Marca"].select().decodeList<Brand>()
        return response
    }

}