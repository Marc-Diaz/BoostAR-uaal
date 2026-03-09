package com.example.boostar_uaal.data.repository

import android.util.Log
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.core.repository.ProductRepository
import com.example.boostar_uaal.ui.screen.onboardingChooseScreen.components.OnboardingStep
import com.example.core.entities.Product
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.rpc
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put


class ProductRepositoryImpl(private val postgrest: Postgrest): ProductRepository  {
    override suspend fun getProducts(): List<Product> {
        val response: List<Product> = postgrest["Producto_View"].select().decodeList<Product>()

        return response
    }

    override suspend fun getProductById(id: Int): ProductDetail {
        val raw = postgrest.rpc(
            function = "get_product_detail",
            parameters = buildJsonObject {
                put("p_product_id", id)
            }
        )

        Log.d("RAW", "$id ${raw.data}")

        val response: ProductDetail = postgrest.rpc(
            function = "get_product_detail",
            parameters = mapOf("p_product_id" to id)
        ).decodeSingle()

        return response
    }
    override suspend fun getOnboardingSteps(): List<OnboardingStep> {
        val response: List<Product> = postgrest["Producto_View"].select().decodeList<Product>()


        return listOf(
            OnboardingStep(
                id = "tops",
                title = "Choose the Tops you like.",
                options = response
            ),
            OnboardingStep(
                id = "bottoms",
                title = "Choose the Bottoms you like.",
                options = response
            ),
            OnboardingStep(
                id = "outerwear",
                title = "Choose the Outerwear you like.",
                options = response
            )
        )
    }
}