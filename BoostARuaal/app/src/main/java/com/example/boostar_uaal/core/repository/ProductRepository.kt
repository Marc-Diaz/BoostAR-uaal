package com.example.boostar_uaal.core.repository

import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.ui.screen.onboardingChooseScreen.components.OnboardingStep
import com.example.core.entities.Product

interface ProductRepository {

    suspend fun getProducts(): List<Product>

    suspend fun getProductById(id: Int): ProductDetail

    suspend fun getOnboardingSteps(): List<OnboardingStep>

    suspend fun getProductDetailBatch(refId: Int?, limit: Int, direction: String): List<ProductDetail>

    suspend fun getFeedProducts_V2(
        sortMode: String,
        refId: Int? = null,
        direction: String,
    ): List<ProductDetail>

    suspend fun getProducts_V2(
        sortMode: String,
        refId: Int? = null,
    ): List<Product>
}