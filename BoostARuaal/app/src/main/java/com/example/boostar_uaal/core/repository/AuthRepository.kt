package com.example.boostar_uaal.core.repository

interface AuthRepository {
    suspend fun signInWithGoogle()
}