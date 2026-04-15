package com.example.boostar_uaal.data.repository

import android.util.Log
import com.example.boostar_uaal.core.entities.LikeStyle
import com.example.boostar_uaal.core.repository.LikeRepository
import com.example.core.entities.Product
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class LikeRepositoryImpl(private val postgrest: Postgrest): LikeRepository {
    private val _likeState = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    override val likeStateFlow: StateFlow<Map<Int, Boolean>> = _likeState.asStateFlow()

    override suspend fun toggleLike(id: Int): Boolean {
        val isLiked = postgrest.rpc("toggle_like", buildJsonObject {
            put("p_product_id", id)
        }).decodeAs<Boolean>()
        _likeState.value += (id to isLiked)
        return isLiked
    }

    override suspend fun getLikeList(): List<Product> {
        val respones = postgrest["LikeList_View"].select().decodeList<Product>()
        Log.d("LikeList", "$respones")
        return respones
    }

    override suspend fun getTotalLikesByStyle(): List<LikeStyle> {
        val response = postgrest.rpc("get_total_likes_by_style").decodeList<LikeStyle>()
        return response
    }
}