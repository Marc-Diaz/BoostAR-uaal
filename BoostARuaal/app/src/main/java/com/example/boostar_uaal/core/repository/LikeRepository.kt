package com.example.boostar_uaal.core.repository

interface LikeRepository {
    suspend fun toggleLike(id: Int): Boolean
}