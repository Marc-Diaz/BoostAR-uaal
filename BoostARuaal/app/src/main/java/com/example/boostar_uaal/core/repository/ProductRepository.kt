package com.example.boostar_uaal.core.repository

import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.ui.screen.onboardingChooseScreen.components.OnboardingStep
import com.example.core.entities.Product

interface ProductRepository {

    suspend fun getProducts(): List<Product>

    suspend fun getProductById(id: Int): ProductDetail

    suspend fun getOnboardingSteps(): List<OnboardingStep>


    suspend fun getFeedProducts(
        sortMode: String,
        refId: Int? = null,
        direction: String,
    ): List<ProductDetail>

    suspend fun getProducts(
        sortMode: String,
        refId: Int? = null,
    ): List<Product>
}