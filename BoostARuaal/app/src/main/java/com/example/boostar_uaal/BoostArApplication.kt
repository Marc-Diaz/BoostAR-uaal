package com.example.boostar_uaal

import android.app.Application
import com.example.boostar_uaal.core.repository.AuthRepository
import com.example.boostar_uaal.core.repository.PartnerRepository
import com.example.boostar_uaal.core.repository.LikeRepository
import com.example.boostar_uaal.core.repository.ProductRepository
import com.example.boostar_uaal.core.repository.UserRepository
import com.example.boostar_uaal.core.utils.UnityActivityHandler
import com.example.boostar_uaal.data.datasource.SupabaseClientProvider
import com.example.boostar_uaal.data.repository.AuthRepositoryImpl
import com.example.boostar_uaal.data.repository.PartnersRepositoryImpl
import com.example.boostar_uaal.data.repository.LikeRepositoryImpl
import com.example.boostar_uaal.data.repository.ProductRepositoryImpl
import com.example.boostar_uaal.data.repository.UserRepositoryImpl
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.postgrest.postgrest

class BoostArApplication: Application() {
    private val supabaseUrl = BuildConfig.SUPABASE_URL
    private val supabaseKey = BuildConfig.SUPABASE_KEY
    private lateinit var supabaseClient: SupabaseClient

    companion object{
        lateinit var authRepository: AuthRepository
        lateinit var productRepository: ProductRepository
        lateinit var likeRepository: LikeRepository
        lateinit var partnerRepository: PartnerRepository

        lateinit var userRepository: UserRepository
        lateinit var composeAuth: ComposeAuth
        lateinit var unityHandler: UnityActivityHandler
    }

    override fun onCreate() {
        super.onCreate()
        supabaseClient = SupabaseClientProvider(
            supabaseUrl = supabaseUrl,
            supabaseKey = supabaseKey
        ).client

        authRepository = AuthRepositoryImpl(supabaseClient.auth)
        productRepository = ProductRepositoryImpl(supabaseClient.postgrest)
        likeRepository = LikeRepositoryImpl(supabaseClient.postgrest)
        partnerRepository = PartnersRepositoryImpl(supabaseClient.postgrest)
        userRepository = UserRepositoryImpl(supabaseClient.postgrest)

        composeAuth = supabaseClient.composeAuth
        unityHandler = UnityActivityHandler()
    }
}