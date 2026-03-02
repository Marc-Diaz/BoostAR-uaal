package com.example.boostar_uaal.data.repository

import com.example.boostar_uaal.core.repository.LikeRepository
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class LikeRepositoryImpl(private val postgrest: Postgrest): LikeRepository {
    override suspend fun toggleLike(id: Int): Boolean {
        val response = postgrest.rpc("toggle_like", buildJsonObject {
            put("p_product_id", id)
        }).decodeAs<Boolean>()

        return response
    }
}