package com.example.boostar_uaal.ui.screen.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.BoostArApplication
import com.example.boostar_uaal.R
import com.example.boostar_uaal.data.repository.MockProductRepositoryImpl
import com.example.boostar_uaal.ui.screen.homeScreen.components.HeroBannerData
import com.example.core.entities.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel: ViewModel() {

    private val productRepository = BoostArApplication.productRepository
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()
    private val _banners = MutableStateFlow<List<HeroBannerData>>(emptyList())
    val banners: StateFlow<List<HeroBannerData>> = _banners.asStateFlow()

    init {
        getProducts()
        loadBanners()
    }
    fun getProducts(){
        viewModelScope.launch {
            _products.value = productRepository.getProducts()
            Log.d("HomeScreenViewModel", "getProducts: ${_products.value}")
        }
    }

    private fun loadBanners() {
        // De momento cargamos datos "Mock" (falsos/locales).
        // En un futuro, esto podría venir de Supabase igual que los productos.
        _banners.value = listOf(
            HeroBannerData(
                imageRes = R.drawable.home_hero,
                label = "LOOK DESTACADO",
                title = "El minimalismo\nurbano vuelve.",
                subtitle = "Descubre el poder del estilo sencillo."
            ),
            HeroBannerData(
                imageRes = R.drawable.carrusel_auth_2,
                label = "NUEVA COLECCIÓN",
                title = "Colores vibrantes\npara primavera.",
                subtitle = "Atrévete con lo nuevo de temporada."
            ),
            HeroBannerData(
                imageRes = R.drawable.home_hero,
                label = "EXCLUSIVIDAD",
                title = "Realidad Aumentada\nen tus manos.",
                subtitle = "Pruébate la ropa sin salir de casa."
            )
        )
    }

    fun onTryArClick() {
        Log.d("HomeScreenViewModel", "El usuario quiere probar la cámara AR")

    }

    fun toggleLike(productId: Int) {
        val currentProducts = _products.value

        val updatedProducts = currentProducts.map { product ->
            if (product.id == productId) {
                product.copy(isLiked = !product.isLiked)
            } else {
                product
            }
        }
        _products.value = updatedProducts
    }
}


