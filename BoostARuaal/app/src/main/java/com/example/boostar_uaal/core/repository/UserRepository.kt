package com.example.boostar_uaal.core.repository

import com.example.boostar_uaal.core.entities.User

interface UserRepository {
    suspend fun getUserProfile(): User

    suspend fun setUserRole(isCompany: Boolean)

    suspend fun hasUserRole(): Boolean

    suspend fun isCompanyUser(): Boolean
    suspend fun updateOnboardingPreferences(preferences: Map<String, List<Int>>)
}