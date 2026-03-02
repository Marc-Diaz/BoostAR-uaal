package com.example.boostar_uaal.data.repository

import android.util.Log
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.core.repository.LikeRepository
import com.example.core.entities.Product
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

    override suspend fun getLikeList(): List<Product> {
        val respones = postgrest["LikeList_View"].select().decodeList<Product>()
        Log.d("LikeList", "$respones")
        return respones
    }
}