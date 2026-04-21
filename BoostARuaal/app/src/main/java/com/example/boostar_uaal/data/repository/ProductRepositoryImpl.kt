package com.example.boostar_uaal.data.repository

import android.util.Log
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.core.repository.ProductRepository
import com.example.boostar_uaal.data.models.ProductFilters
import com.example.boostar_uaal.ui.screen.onboardingChooseScreen.components.OnboardingStep
import com.example.core.entities.Product
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put


class ProductRepositoryImpl(private val postgrest: Postgrest): ProductRepository  {

    private val feedPageSize = 2
    private val carrouselPageSize = 10

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
        filters: ProductFilters,
        refId: Int?,
        limit: Int?
    ): List<Product> {
        return try {
            val pageSize = limit ?: carrouselPageSize
            val response = postgrest.rpc(
                function = "get_products",
                parameters = buildJsonObject {
                    put("p_sort_mode", sortMode)
                    if (refId != null) put("p_ref_id", refId) else put("p_ref_id", JsonNull)
                    put("p_only_discounts", filters.discount)
                    if (filters.partnerId != null) put("p_partner_id", filters.partnerId) else put("p_partner_id", JsonNull)
                    put("p_page_size", pageSize)
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
        filters: ProductFilters,
        refId: Int?,
        direction: String,
    ): List<ProductDetail> {
        return try {
            val response = postgrest.rpc(
                function = "get_feed_products",
                parameters = buildJsonObject {
                    put("p_sort_mode", sortMode)
                    put("p_only_discounts", filters.discount)
                    if (refId != null) put("p_ref_id", refId) else put("p_ref_id", JsonNull)
                    put("p_direction", direction)
                    if (filters.partnerId != null) put("p_partner_id", filters.partnerId) else put("p_partner_id", JsonNull)
                    put("p_page_size", feedPageSize)
                }
            ).decodeList<ProductDetail>()
            response
        } catch (e: Exception) {
            emptyList()
        }
    }


    override suspend fun getOnboardingSteps(): List<OnboardingStep> {
        val response: List<Product> = postgrest.rpc(
            function = "get_onboarding_steps"
        ).decodeList<Product>()

        var steps = response
        steps = steps + steps
        return listOf(
            OnboardingStep(
                id = "tops",
                title = "Escoge las opciones que te gusten.",
                options = steps.subList(0,4)
            ),
            OnboardingStep(
                id = "bottoms",
                title = "Escoge las opciones que te gusten.",
                options = steps.subList(4,8)
            ),
            OnboardingStep(
                id = "outerwear",
                title = "Escoge las opciones que te gusten.",
                options = steps.subList(8,12)
            )
        )
    }

}