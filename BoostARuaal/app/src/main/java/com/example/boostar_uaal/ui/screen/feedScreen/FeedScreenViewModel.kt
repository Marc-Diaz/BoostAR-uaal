package com.example.boostar_uaal.ui.screen.feedScreen


import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.BoostArApplication
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.core.repository.LikeRepository
import com.example.boostar_uaal.core.repository.ProductRepository
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FeedScreenViewModel : ViewModel() {
//define el viewModel para el feed screen
private val productRepository: ProductRepository = BoostArApplication.productRepository
    private val likeRepository: LikeRepository = BoostArApplication.likeRepository
    // Esta será la lista que observará tu Feed (Jetpack Compose o XML)
    private val _products = MutableStateFlow<List<ProductDetail>>(emptyList())
    val products: StateFlow<List<ProductDetail>> = _products.asStateFlow()

    private var isNextLoading = false
    private var isPrevLoading = false
    private var isInitialized = false // Evita recargar si giramos la pantalla

    // Función para arrancar el feed. Lámala desde tu UI cuando se abra la pantalla.
    fun initializeFeed(initialProductId: Int? = null) {
        if (isInitialized) return
        isInitialized = true

        viewModelScope.launch {
            if (initialProductId != null) {
                val initialProduct = productRepository.getProductById(initialProductId)
                _products.value = listOf(initialProduct)
                loadNextPage()
            } else {
                loadNextPage()
            }
        }
    }

    fun loadNextPage() {
        if (isNextLoading) return
        viewModelScope.launch {
            isNextLoading = true
            try {
                val lastId = _products.value.lastOrNull()?.id
                // Llamamos a la función de tu repositorio (asegúrate de haberla actualizado con la v2)
                val newItems = productRepository.getProductDetailBatch(refId = lastId, limit = 10, direction = "next")

                _products.value = (_products.value + newItems).distinctBy { it.id }
            } catch (e: Exception) {
                Log.e("FeedScreenViewModel", "Error cargando siguientes: ${e.message}")
            } finally {
                isNextLoading = false
            }
        }
        Log.d("PRODUCTS", "$products")
    }

    fun loadPrevPage() {
        if (isPrevLoading) return
        viewModelScope.launch {
            isPrevLoading = true
            try {
                val firstId = _products.value.firstOrNull()?.id ?: return@launch
                if (firstId <= 1) return@launch // Asumiendo que 1 es el ID más bajo en tu BD

                val newItems = productRepository.getProductDetailBatch(refId = firstId, limit = 10, direction = "prev")

                _products.value = (newItems + _products.value).distinctBy { it.id }
            } catch (e: Exception) {
                Log.e("FeedScreenViewModel", "Error cargando anteriores: ${e.message}")
            } finally {
                isPrevLoading = false
            }
        }
    }

    fun toggleLike(productId: Int) {
        viewModelScope.launch {
            val isLiked = likeRepository.toggleLike(productId)
            val currentProducts = _products.value
            val addLike = if (isLiked) 1 else -1
            val updatedProducts = currentProducts.map { product ->
                if (product.id == productId) {
                    product.copy(isLiked = isLiked, numLikes = product.numLikes + addLike)
                } else {
                    product
                }
            }
            _products.value = updatedProducts
        }
    }

    fun onTryArClick(context: Context, currentProduct: ProductDetail) {
        Log.d("FeedScreenViewModel", "El usuario quiere probar la cámara AR para: ${currentProduct.id}")
        // BoostArApplication.unityHandler.sendClothingToUnity(context, "${currentProduct}", "${currentProduct}" )
    }

}
