package com.example.boostar_uaal.core.repository

import com.example.boostar_uaal.core.entities.LikeStyle
import com.example.core.entities.Product
import kotlinx.coroutines.flow.StateFlow

interface LikeRepository {
    val likeStateFlow: StateFlow<Map<Int, Boolean>>
    suspend fun toggleLike(id: Int): Boolean
    suspend fun getLikeList(): List<Product>

    suspend fun getTotalLikesByStyle(): List<LikeStyle>
}