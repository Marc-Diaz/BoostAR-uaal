package com.example.boostar_uaal.core.repository

import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.data.models.ProductFilters
import com.example.boostar_uaal.ui.screen.onboardingChooseScreen.components.OnboardingStep
import com.example.core.entities.Product

interface ProductRepository {



    suspend fun getProductById(id: Int): ProductDetail

    suspend fun getOnboardingSteps(): List<OnboardingStep>


    suspend fun getFeedProducts(
        sortMode: String,
        filters: ProductFilters = ProductFilters(),
        refId: Int? = null,
        direction: String,
    ): List<ProductDetail>

    suspend fun getProducts(
        sortMode: String,
        filters: ProductFilters = ProductFilters(),
        refId: Int? = null,
        limit: Int? = null
    ): List<Product>
}