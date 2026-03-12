package com.example.boostar_uaal.data.repository

import android.util.Log
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.core.repository.ProductRepository
import com.example.boostar_uaal.ui.screen.onboardingChooseScreen.components.OnboardingStep
import com.example.core.entities.Product
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put


class ProductRepositoryImpl(private val postgrest: Postgrest): ProductRepository  {

    private val feedPageSize = 2
    private val carrouselPageSize = 10
    private val listPageSize = 20
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
        ).decodeAs<ProductDetail>()

        return response
    }
    override suspend fun getProducts(
        sortMode: String,
        refId: Int?,
    ): List<Product> {
        return try {
            val response = postgrest.rpc(
                function = "get_products",
                parameters = buildJsonObject {
                    put("p_sort_mode", sortMode)
                    if (refId != null) put("p_ref_id", refId) else put("p_ref_id", JsonNull)
                    put("p_page_size", carrouselPageSize)
                }
            ).decodeList<Product>()

            response
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error fetching feed products: ${e.message}")
            emptyList()
        }
    }
    override suspend fun getFeedProducts(
        sortMode: String,
        refId: Int?,
        direction: String,
    ): List<ProductDetail> {
        return try {
            val response = postgrest.rpc(
                function = "get_feed_products",
                parameters = buildJsonObject {
                    put("p_sort_mode", sortMode)
                    if (refId != null) put("p_ref_id", refId) else put("p_ref_id", JsonNull)
                    put("p_direction", direction)
                    put("p_limit", feedPageSize)
                }
            ).decodeList<ProductDetail>()

            response
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error fetching feed products: ${e.message}")
            emptyList()
        }
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