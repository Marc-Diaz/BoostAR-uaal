package com.example.boostar_uaal.data.repository

import com.example.boostar_uaal.core.entities.User
import com.example.boostar_uaal.core.repository.UserRepository
import io.github.jan.supabase.postgrest.Postgrest

class UserRepositoryImpl(private val postgrest: Postgrest): UserRepository {
    override suspend fun getUserProfile(): User {
        val response = postgrest.rpc("get_user_profile").decodeSingle<User>()
        return response
    }
}