package com.example.boostar_uaal

import android.app.Application
import com.example.boostar_uaal.core.repository.AuthRepository
import com.example.boostar_uaal.core.repository.ProductRepository
import com.example.boostar_uaal.data.datasource.SupabaseClientProvider
import com.example.boostar_uaal.data.repository.AuthRepositoryImpl
import com.example.boostar_uaal.data.repository.MockProductRepositoryImpl
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth

class BoostAr: Application() {
    private val supabaseUrl = BuildConfig.SUPABASE_URL
    private val supabaseKey = BuildConfig.SUPABASE_KEY
    private lateinit var _supabaseClient: SupabaseClient

    companion object{
        lateinit var authRepositoryImpl: AuthRepository
        lateinit var productRepository: ProductRepository

        lateinit var supabaseClient: SupabaseClient
    }

    override fun onCreate() {
        super.onCreate()
        _supabaseClient = SupabaseClientProvider(
            supabaseUrl = supabaseUrl,
            supabaseKey = supabaseKey
        ).client

        authRepositoryImpl = AuthRepositoryImpl(_supabaseClient)
        productRepository = MockProductRepositoryImpl()
        supabaseClient = _supabaseClient
    }
}