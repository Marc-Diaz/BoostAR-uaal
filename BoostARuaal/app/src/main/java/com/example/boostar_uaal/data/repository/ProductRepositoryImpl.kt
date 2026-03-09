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
    private var currentIndex = 0
    private var _products = mutableListOf<ProductDetail>()
    private var pageSize = 10
    private var isLoading = false

    override suspend fun getProducts(): List<Product> {
        val response: List<Product> = postgrest["Producto_View"].select().decodeList<Product>()

        return response
    }

    override suspend fun getProductById(id: Int): ProductDetail {
        val response = postgrest.rpc(
            function = "get_product_detail",
            parameters = buildJsonObject {
                put("p_product_id", id)
            }
        ).decodeSingle<ProductDetail>()

        return response
    }
    private suspend fun loadMoreProducts(){
        if (isLoading) return
        isLoading = true
        val response: List<ProductDetail> = postgrest["Producto_View"]
            .select {
                range(_products.size.toLong(), (_products.size + pageSize - 1).toLong())
            }
            .decodeList()
        isLoading = false
        _products.addAll(response)
    }

    override suspend fun getNextProduct(): ProductDetail {
        if (currentIndex + 1 >= _products.size) {
            loadMoreProducts()
        }
        currentIndex++
        return _products[currentIndex]
    }

    override suspend fun getPreviousProduct(): ProductDetail {
        if (currentIndex > 0) currentIndex--
        return _products[currentIndex]
    }

    override suspend fun getOnboardingSteps(): List<OnboardingStep> {
        val response: List<Product> = postgrest.rpc(
            function = "get_onboarding_steps"
        ).decodeList<Product>()

        var steps = response + response + response + response
        steps = steps.shuffled()
        return listOf(
            OnboardingStep(
                id = "tops",
                title = "Choose the Tops you like.",
                options = steps.subList(0,4)
            ),
            OnboardingStep(
                id = "bottoms",
                title = "Choose the Bottoms you like.",
                options = steps.subList(4,8)
            ),
            OnboardingStep(
                id = "outerwear",
                title = "Choose the Outerwear you like.",
                options = steps.subList(8,12)
            )
        )
    }

}