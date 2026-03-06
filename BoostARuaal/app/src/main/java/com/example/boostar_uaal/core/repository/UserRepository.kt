package com.example.boostar_uaal.core.repository

import com.example.boostar_uaal.core.entities.User

interface UserRepository {
    suspend fun getUserProfile(): User
}